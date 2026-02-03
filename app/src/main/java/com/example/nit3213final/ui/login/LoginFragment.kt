package com.example.nit3213final.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.nit3213final.R
import com.example.nit3213final.databinding.FragmentLoginBinding
import com.example.nit3213final.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            viewModel.login(username, password)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        binding.btnLogin.isEnabled = false
                    }

                    is UiState.Error -> {
                        binding.btnLogin.isEnabled = true
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                        // ✅ DO NOT close the app
                    }

                    is UiState.Success -> {
                        binding.btnLogin.isEnabled = true
                        val keypass = state.data

                        val action = LoginFragmentDirections
                            .actionLoginFragmentToDashboardFragment(keypass)

                        findNavController().navigate(action)
                    }

                    else -> Unit
                }
            }
        }
    }
}
