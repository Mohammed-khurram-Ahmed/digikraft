package com.mohammed.khurram.digikraft.uiviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohammed.khurram.digikraft.R
import com.mohammed.khurram.digikraft.databinding.FragmentListBinding
import com.mohammed.khurram.digikraft.models.Feature
import com.mohammed.khurram.digikraft.models.NetworkResult
import com.mohammed.khurram.digikraft.uiviews.adapter.StationAdapter
import com.mohammed.khurram.digikraft.uiviews.viewmodel.ListScreenViewModel
import com.mohammed.khurram.digikraft.utils.AppConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListScreenFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    lateinit var viewModel: ListScreenViewModel

    /**
     * Android fragment lifecycle  callbacks
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Android fragment lifecycle  callbacks
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
         * View Model Setup
         */
        viewModel = ViewModelProvider(this)[ListScreenViewModel::class.java]

        /*
         * RecyclerView setup
         */
        val adapter = StationAdapter(this)
        binding.rvStations.layoutManager = LinearLayoutManager(context)
        binding.rvStations.adapter = adapter

        /*
         * Observer call back upon remote data receive
         */
        viewModel.immutableLiveData.observe(viewLifecycleOwner) { it ->
            /*
            * Update recycle view with new data.
            */
            when (it) {
                is NetworkResult.Success<*> ->  adapter.updateItems(it.data as List<Feature>)
                is NetworkResult.Error<*> -> Toast.makeText(context,getString(R.string.internet_error),Toast.LENGTH_LONG).show()
                is NetworkResult.Loading<*> -> Toast.makeText(context,getString(R.string.loading_data_message),Toast.LENGTH_SHORT).show()
            }

        }
        /*
         * Reload button to get data again
         */
        binding.fabRefreshBtn.setOnClickListener {
            viewModel.getData()
        }
    }

    /**
     * Navigation method , takes Features object,  pass to details screen fragment
     */
    fun navigateToDetails(feature: Feature) {
        findNavController().navigate(
            R.id.action_FirstFragment_to_SecondFragment,
            Bundle().apply { putParcelable(AppConstants.DATA, feature) }
        )
    }
}