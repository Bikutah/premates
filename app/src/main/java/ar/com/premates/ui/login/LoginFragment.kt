package ar.com.premates.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ar.com.premates.R
import ar.com.premates.databinding.FragmentLoginBinding
import ar.com.premates.viewmodel.login.LoginUiState
import ar.com.premates.viewmodel.login.LoginViewModel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnIngresar.setOnClickListener {
            val usuario = binding.etUsuario.text.toString()
            val password = binding.etPassword.text.toString()
            loginViewModel.login(usuario, password)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            loginViewModel.uiState.collect { state ->
                when (state) {
                    is LoginUiState.Compleado -> {
                        findNavController().navigate(R.id.action_login_to_home)
                    }
                    is LoginUiState.Error -> {
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text = state.mensaje
                    }
                    is LoginUiState.Iniciado -> {
                        binding.tvError.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}