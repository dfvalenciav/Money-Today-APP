package com.davalIndustries.daval.moneytoday.listaDeseos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.davalIndustries.daval.moneytoday.R

class ListaDeseosFragment : Fragment() {

    companion object {
        fun newInstance() = ListaDeseosFragment()
    }

    private lateinit var viewModel: ListaDeseosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_deseos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListaDeseosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}