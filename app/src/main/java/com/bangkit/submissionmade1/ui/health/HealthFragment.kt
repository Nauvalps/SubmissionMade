package com.bangkit.submissionmade1.ui.health

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.submissionmade1.R
import com.bangkit.submissionmade1.core.adapter.NewsAdapter
import com.bangkit.submissionmade1.core.data.source.Resource
import com.bangkit.submissionmade1.databinding.FragmentHealthBinding
import com.bangkit.submissionmade1.ui.detail.DetailNewsActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HealthFragment : Fragment(){

    private val healthViewModel: HealthViewModel by viewModel()

    private var _binding: FragmentHealthBinding? = null
    private val _bind get() = _binding


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHealthBinding.inflate(inflater, container, false)
        return _bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null) {

            val healthAdapter = NewsAdapter()
            healthAdapter.onItemClick = {
                val intent = Intent(activity, DetailNewsActivity::class.java)
                intent.putExtra(DetailNewsActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            healthViewModel.healthNews.observe(viewLifecycleOwner, {
                if (it != null) {
                    when(it) {
                        is Resource.Loading -> _bind?.progressBar?.visibility = View.VISIBLE
                        is Resource.Success -> {
                            _bind?.progressBar?.visibility = View.GONE
                            healthAdapter.setData(it.data)
                        }
                        is Resource.Error -> {
                            _bind?.progressBar?.visibility = View.GONE
                            _bind?.viewError?.root?.visibility = View.VISIBLE
                            _bind?.viewError?.tvError?.text = it.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })
            setupAdapter(healthAdapter)
        }
    }

    private fun setupAdapter(healthAdapter: NewsAdapter) {
        _bind?.rvUsers?.layoutManager = LinearLayoutManager(context)
        _bind?.rvUsers?.setHasFixedSize(true)
        _bind?.rvUsers?.adapter = healthAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}