package com.bangkit.submissionmade1.ui.sports

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.submissionmade1.R
import com.bangkit.submissionmade1.core.adapter.NewsAdapter
import com.bangkit.submissionmade1.core.data.source.Resource
import com.bangkit.submissionmade1.databinding.FragmentSportsBinding
import com.bangkit.submissionmade1.ui.detail.DetailNewsActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SportsFragment : Fragment() {

    private val sportsViewModel: SportsViewModel by viewModel()

    private var _binding: FragmentSportsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentSportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val sportsAdapter = NewsAdapter()
            sportsAdapter.onItemClick = {
                val intent = Intent(activity, DetailNewsActivity::class.java)
                intent.putExtra(DetailNewsActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            sportsViewModel.sportsNews.observe(viewLifecycleOwner, {
                if (it != null) {
                    when(it) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            sportsAdapter.setData(it.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = it.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })
            setupAdapter(sportsAdapter)
        }
    }

    private fun setupAdapter(sportsAdapter: NewsAdapter) {
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.adapter = sportsAdapter
    }

}