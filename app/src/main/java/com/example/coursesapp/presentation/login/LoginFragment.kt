package com.example.coursesapp.presentation.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.coursesapp.R
import com.example.coursesapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        setupInputListeners()
        setupButtons()
        observeFormState()
    }

    private fun setupInputListeners() {

        binding.etEmail.addTextChangedListener(
            object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {}

                override fun afterTextChanged(s: Editable?) {

                    val text = s.toString()

                    if (text.any { it in '\u0400'..'\u04FF' }) {

                        binding.etEmail.setText(
                            text.filter {
                                it !in '\u0400'..'\u04FF'
                            }
                        )

                        binding.etEmail.setSelection(
                            binding.etEmail.text?.length ?: 0
                        )

                        return
                    }

                    viewModel.onEmailChanged(text)
                }
            }
        )

        binding.etPassword.addTextChangedListener(
            object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {}

                override fun afterTextChanged(s: Editable?) {
                    viewModel.onPasswordChanged(s.toString())
                }
            }
        )
    }

    private fun setupButtons() {

        binding.btnLogin.setOnClickListener {

            findNavController().navigate(
                R.id.action_loginFragment_to_homeFragment
            )
        }

        binding.btnVk.setOnClickListener {

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://vk.com")
            )

            startActivity(intent)
        }

        binding.btnOk.setOnClickListener {

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://ok.ru")
            )

            startActivity(intent)
        }

        binding.tvRegister.setOnClickListener {

        }

        binding.tvForgot.setOnClickListener {

        }
    }

    private fun observeFormState() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                viewModel.isFormValid.collect { isValid ->

                    binding.btnLogin.isEnabled = isValid

                    binding.btnLogin.alpha =
                        if (isValid) 1f else 0.5f
                }
            }
        }
    }

    override fun onDestroyView() {

        super.onDestroyView()

        _binding = null
    }
}