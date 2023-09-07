package com.huseyinozkoc.bitcointicker.presentation.SignUpPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.huseyinozkoc.bitcointicker.R
import com.huseyinozkoc.bitcointicker.common.Resource
import com.huseyinozkoc.bitcointicker.common.viewBinding
import com.huseyinozkoc.bitcointicker.databinding.FragmentSignUpPageBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpPage : Fragment() {

    private val binding by viewBinding(FragmentSignUpPageBinding::bind)

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        checkData()
    }


    private fun initUI() {
        with(binding) {

            registerButton.setOnClickListener {

                val email = usernameTextField.editText?.text.toString()
                val password = passwordTextField.editText?.text.toString()

                signUpViewModel.signUp(email, password)


            }

            alreadyMember.setOnClickListener {

                findNavController().navigate(R.id.action_signUpPage_to_signInPage)
            }

        }
    }    //End of the initUI Function

    private fun checkData() {

        with(binding) {

            with(signUpViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    signUpFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> {}
                            is Resource.Success -> {

                                Toast.makeText(
                                    activity,
                                    "Başarıyla üye olundu",
                                    Toast.LENGTH_LONG
                                ).show()
                                findNavController().navigate(R.id.action_signUpPage_to_signInPage)
                            }

                            is Resource.Error -> {
                                Toast.makeText(
                                    activity,
                                    "Bir Hata ile karşılaşıldı {${result.throwable.message.toString() + result.throwable.cause.toString()}}",
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                        }
                    }
                }

            }


        }

    }  //End of the check Data Function


}