package com.example.listofpeople.preview.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.listofpeople.R
import com.example.listofpeople.databinding.FragmentPeopleInfoBinding
import com.example.listofpeople.preview.viewmodel.PeopleInfoViewModel

class PeopleInfoFragment : Fragment() {

    private val options = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .priority(Priority.HIGH)

    private val viewModel: PeopleInfoViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }

        ViewModelProvider(this, PeopleInfoViewModel.Factory(activity.application))
            .get(PeopleInfoViewModel::class.java)
    }

    private val args by navArgs<PeopleInfoFragmentArgs>()

    private var _binding: FragmentPeopleInfoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleInfoBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val info = args.currentUser

        Glide.with(requireContext())
            .load(info.avatar)
            .apply(options)
            .into(binding.imgAvatar)

        binding.tvName.text = info.name
        binding.tvSurname.text = info.surname
        binding.tvEmail.text = info.email
        binding.btnDelete.setOnClickListener {
            viewModel.deleteUser(info)
            findNavController().navigate(R.id.peopleListFragment)
        }
        binding.btnUpdate.setOnClickListener {
            findNavController().navigate(PeopleInfoFragmentDirections.actionPeopleInfoFragmentToUpdateFragment(info))
        }
    }
}