package com.bangkit.submissionmade1.ui.headlines

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.submissionmade1.R
import com.bangkit.submissionmade1.core.adapter.NewsAdapter
import com.bangkit.submissionmade1.core.data.source.Resource
import com.bangkit.submissionmade1.databinding.FragmentHeadlinesBinding
import com.bangkit.submissionmade1.ui.detail.DetailNewsActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HeadlinesFragment : Fragment() {

    private val headlinesViewModel: HeadlinesViewModel by viewModel()

    private var _binding: FragmentHeadlinesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHeadlinesBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null) {

            val newsAdapter = NewsAdapter()

            newsAdapter.onItemClick = {
                val intent = Intent(activity, DetailNewsActivity::class.java)
                intent.putExtra(DetailNewsActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            headlinesViewModel.news.observe(viewLifecycleOwner, {
                if (it != null) {
                    when(it) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            newsAdapter.setData(it.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = it.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            setupAdapter(newsAdapter)

        }
    }

    private fun setupAdapter(newsAdapter: NewsAdapter) {
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.adapter = newsAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.news_fav) {
            val uri = Uri.parse("bangkit://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}