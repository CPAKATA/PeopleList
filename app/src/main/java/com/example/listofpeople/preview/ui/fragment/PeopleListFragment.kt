package com.example.listofpeople.preview.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listofpeople.R
import com.example.listofpeople.databinding.FragmentPeopleListBinding
import com.example.listofpeople.preview.adapter.RecyclerAdapter
import com.example.listofpeople.preview.viewmodel.PeopleListViewModel


class PeopleListFragment : Fragment() {

    private val viewModel: PeopleListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }

        ViewModelProvider(this, PeopleListViewModel.Factory(activity.application))
            .get(PeopleListViewModel::class.java)
    }

    private var _binding: FragmentPeopleListBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: RecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerAdapter(requireContext())
        val recyclerView = binding.rcvList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.swipeRefresh.setOnRefreshListener {
            loadData()
        }

        if (checkDataShared()){
            viewModel.readAll()
            viewModel.readAll.observe(viewLifecycleOwner,{
                adapter.setData(it)
            })
        }
        else{
            saveDataShared()
        }
    }


    private fun loadData() {
        viewModel.loadUsers()
        viewModel.isUserLoaded.observe(viewLifecycleOwner,{
            viewModel.readAll()
            viewModel.readAll.observe(viewLifecycleOwner,{
                adapter.setData(it)
                binding.swipeRefresh.isRefreshing = false
            })
        })
    }

    private fun checkDataShared(): Boolean{
        val sharedPreferences = this.requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("BOOLEAN_KEY", false)
    }

    private fun saveDataShared(){
        val sharedPreferences = this.requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().apply{
            putBoolean("BOOLEAN_KEY", true)
        }.apply()
        viewModel.loadUsers()
        viewModel.isUserLoaded.observe(viewLifecycleOwner,{
            viewModel.readAll()
            viewModel.readAll.observe(viewLifecycleOwner,{
                adapter.setData(it)
            })
        })
    }
}