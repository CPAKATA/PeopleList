package com.example.listofpeople.preview.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.listofpeople.R
import com.example.listofpeople.data.User
import com.example.listofpeople.databinding.FragmentPeopleListBinding
import com.example.listofpeople.databinding.FragmentUpdateBinding
import com.example.listofpeople.preview.utils.Utils
import com.example.listofpeople.preview.viewmodel.PeopleListViewModel
import com.example.listofpeople.preview.viewmodel.UpdateViewModel

class UpdateFragment : Fragment() {

    private val options = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .priority(Priority.HIGH)

    private val viewModel: UpdateViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }

        ViewModelProvider(this, UpdateViewModel.Factory(activity.application))
            .get(UpdateViewModel::class.java)
    }

    private val args by navArgs<UpdateFragmentArgs>()

    private var _binding: FragmentUpdateBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(
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

        binding.etName.setText(info.name)
        binding.etSurname.setText(info.surname)
        binding.etEmail.setText(info.email)
        binding.btnUpdate.setOnClickListener {
            val name: String = binding.etName.text.toString()
            val surname: String = binding.etSurname.text.toString()
            val email: String = binding.etEmail.text.toString()
            if(Utils.checkInput(name, surname, email)){
                viewModel.updateUser(User(info.id,name, surname, email, info.avatar))
                findNavController().navigate(R.id.peopleListFragment)
            }
            else{
                Toast.makeText(requireContext(), "Вы не ввели все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }
}