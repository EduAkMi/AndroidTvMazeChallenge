package com.jobsitychallenge.ui.fragments

import androidx.fragment.app.Fragment
import com.jobsitychallenge.ui.MainActivity

abstract class BaseFragment : Fragment(){

    fun changeFragment(fragment: Fragment){
        (activity as MainActivity).replaceFragmentWithBackstack(fragment)
    }

    fun popBackStack(){
        (activity as MainActivity).popBackStack()
    }
}