package com.kanulp.esrandroidtest.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kanulp.esrandroidtest.R
import com.kanulp.esrandroidtest.data.model.Data
import com.kanulp.esrandroidtest.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Type

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class NearByListFragment : Fragment() {

    var recyclerView: RecyclerView? = null
    private var viewModel: NearByListViewModel? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_nearbylist, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(NearByListViewModel::class.java)
        setupObserver()

    }
    fun setupObserver(){
        viewModel?.getNearByData()
        viewModel?.res?.observe(requireActivity(), {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    val adapter = NearByAdapter(
                        it.data?.payload?.items,
                        requireContext(),
                        object : NearByAdapter.OnItemClickListener {
                            override fun onItemClick(position: Int) {
                                Log.d("MAIN", "Clicked item ${it.data?.payload?.items}")
                                //converting to string to pass in to next screen.
                                val gson = Gson()
                                val output = gson.toJson(it.data?.payload?.items!![position])
                                loadDetailPage(view,output)
                            }
                        })
                    val layoutManager = LinearLayoutManager(requireContext())
                    recyclerView?.layoutManager = layoutManager
                    recyclerView?.adapter = adapter
                }
                Resource.Status.ERROR -> {
                    Log.d("MAIN", "Error in calling api ${it.data} ${it.status} ")
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

            }
        })
    }
    private fun loadDetailPage(view:View?,data: String) {

        val action = NearByListFragmentDirections.actionFirstFragmentToSecondFragment(id=data)
        view?.let { Navigation.findNavController(it).navigate(action) }
    }

}