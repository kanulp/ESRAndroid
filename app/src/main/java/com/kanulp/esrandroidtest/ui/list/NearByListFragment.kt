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
import com.kanulp.esrandroidtest.R
import com.kanulp.esrandroidtest.util.Resource
import dagger.hilt.android.AndroidEntryPoint

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
        Log.d("MAIN","Calling SetupObserver")
        viewModel?.getNearByData()
        viewModel?.res?.observe(requireActivity(), {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.d("MAIN", "GOT SUCCESS with array size : ")
                    val adapter = NearByAdapter(
                        it.data?.payload?.items,
                        requireContext(),
                        object : NearByAdapter.OnItemClickListener {
                            override fun onItemClick(position: Int) {
                                //loadDetailPage(view, it.data!![position].id)
                                Log.d("MAIN", "Clicked item ${it.data?.payload?.items}")
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
//    private fun loadDetailPage(view:View?,id: Int) {
//
//        val action = FriendsFragmentDirections.actionFriendsFragmentToDetailFragment(id=id.toString())
//        view?.let { Navigation.findNavController(it).navigate(action) }
//    }
}