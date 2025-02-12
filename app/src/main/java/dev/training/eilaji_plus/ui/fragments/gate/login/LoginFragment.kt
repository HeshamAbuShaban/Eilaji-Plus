package dev.training.eilaji_plus.ui.fragments.gate.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.training.eilaji_plus.databinding.FragmentLoginBinding
import dev.training.eilaji_plus.vms.EntranceViewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var entranceViewModel: EntranceViewModel

    // Remember to save token

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        entranceViewModel = ViewModelProvider(requireActivity())[EntranceViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            buSignUp.setOnClickListener {
                entranceViewModel.toSignUp()
            }
            buForgotPassword.setOnClickListener {
                entranceViewModel.toForgetPassword()
            }
            buLogin.setOnClickListener {
                performLogin()
            }
        }
    }

    private fun performLogin() {
        entranceViewModel.toHome()
    }
}