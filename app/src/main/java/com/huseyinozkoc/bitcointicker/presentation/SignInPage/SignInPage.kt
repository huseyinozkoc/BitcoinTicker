package com.huseyinozkoc.bitcointicker.presentation.SignInPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.huseyinozkoc.bitcointicker.R
import com.huseyinozkoc.bitcointicker.common.Resource
import com.huseyinozkoc.bitcointicker.common.viewBinding
import com.huseyinozkoc.bitcointicker.databinding.FragmentSignInPageBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.huseyinozkoc.bitcointicker.utils.UserManagerDataStorage
import javax.inject.Inject

@AndroidEntryPoint
class SignInPage : Fragment() {

    private val binding by viewBinding(FragmentSignInPageBinding::bind)

    private val signInViewModel: SignInViewModel by viewModels()

    @Inject
    lateinit var analytics: FirebaseAnalytics

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        checkData()
    }

    private fun initUI() {
        with(binding) {

            buttonSignIn.setOnClickListener {

                val email = emailTextField.editText?.text.toString()
                val password = passwordTextField.editText?.text.toString()

                signInViewModel.signIn(email, password)


            }

            buttonSignUp.setOnClickListener { findNavController().navigate(R.id.action_signInPage_to_signUpPage) }

        }
    }    //End of the initUI Function


    private fun checkData() {

        with(binding) {

            with(signInViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    signInFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> {}
                            is Resource.Success -> {

                                android.widget.Toast.makeText(
                                    activity,
                                    "Başarıyla giriş yapıldı.",
                                    android.widget.Toast.LENGTH_LONG
                                ).show()

                                val bundle = Bundle()
                                bundle.putString(FirebaseAnalytics.Param.METHOD, "CUSTOM")
                                analytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)

                                findNavController().navigate(R.id.action_signInPage_to_home_graph)
                            }
                            is Resource.Error -> {
                                Snackbar.make(
                                    requireView(),
                                    "Girişte Bir Hata ile karşılaşıldı {${result.throwable.message.toString() + result.throwable.cause.toString()}}",
                                    Snackbar.LENGTH_SHORT
                                ).show()

                            }
                        }
                    }
                }

            }


        }

    }  //End of the check Data Function


}