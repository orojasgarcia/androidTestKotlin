package com.rojas.androidtestkotlin.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rojas.androidtestkotlin.AppDatabase
import com.rojas.androidtestkotlin.R
import com.rojas.androidtestkotlin.data.DataSource
import com.rojas.androidtestkotlin.data.model.Movie
import com.rojas.androidtestkotlin.data.model.MovieEntity
import com.rojas.androidtestkotlin.databinding.FragmentMainBinding
import com.rojas.androidtestkotlin.domain.DialogFragment
import com.rojas.androidtestkotlin.domain.RepoImpl
import com.rojas.androidtestkotlin.ui.viewmodel.MainViewModel
import com.rojas.androidtestkotlin.ui.viewmodel.VMFactory
import com.rojas.androidtestkotlin.vo.Resource

class MainFragment : Fragment(), MainAdapter.OnMovieClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            RepoImpl(
                DataSource(
                    AppDatabase.getDatabase(
                        requireActivity().applicationContext
                    )
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.fetchMovieList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvMovies.adapter = MainAdapter(requireContext(), result.data, this)

                    result.data.map {
                        viewModel.saveMovie(
                            MovieEntity(
                                it.id,
                                it.name,
                                it.image,
                                it.overview
                            )
                        )
                    }

                }
                is Resource.Failure -> {
                    viewModel.getAllMovies().observe(viewLifecycleOwner, Observer { resultRoom ->
                        when (resultRoom) {
                            is Resource.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Resource.Success -> {
                                val listMovies: List<Movie> = resultRoom.data.map {
                                    Movie(it.name, it.movieId, it.image, it.overview)
                                }
                                binding.progressBar.visibility = View.GONE
                                binding.rvMovies.adapter =
                                    MainAdapter(requireContext(), listMovies, this)

                                if(listMovies.isEmpty()){
                                    DialogFragment().show(childFragmentManager, DialogFragment.TAG)
                                }
                            }
                            is Resource.Failure -> {
                                binding.progressBar.visibility = View.GONE
                                DialogFragment().show(childFragmentManager, DialogFragment.TAG)
                            }
                        }
                    })
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onMovieClick(movie: Movie) {
        val bundle = Bundle()
        bundle.putParcelable("movie", movie)
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
    }
}