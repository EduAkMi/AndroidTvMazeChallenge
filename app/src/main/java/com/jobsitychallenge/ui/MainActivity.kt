package com.jobsitychallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.jobsitychallenge.R
import com.jobsitychallenge.ui.fragments.FragmentHome

class MainActivity : AppCompatActivity() {
    private val mFM: FragmentManager by lazy { supportFragmentManager }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFM.beginTransaction().add(R.id.containerMain, FragmentHome(), null).commit()
    }

    fun replaceFragmentWithBackstack(fragment: Fragment) {
        mFM.beginTransaction().replace(R.id.containerMain, fragment, null).addToBackStack(null)
            .commit()
    }

    fun popBackStack() {
        mFM.popBackStack()
    }
}