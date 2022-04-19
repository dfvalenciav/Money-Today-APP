package com.davalIndustries.daval.moneytoday

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.davalIndustries.daval.moneytoday.databinding.FragmentHomePageBinding


class HomePage : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomePageBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home_page, null, false)
        binding.simuladorButton.setOnClickListener { view:View ->
            Navigation.findNavController(view).navigate(R.id.action_homePage_to_simuladorFragmentUI)
        }
        binding.simuladorButton2.setOnClickListener { view:View ->
            Navigation.findNavController(view).navigate(R.id.action_homePage_to_simuladorInverFragment)
        }

        return binding.root
    }


}