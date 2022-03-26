package com.example.daval.moneytoday.SimuladorCredito

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.daval.moneytoday.Adapter.CustomAdapter
import com.example.daval.moneytoday.R
import com.example.daval.moneytoday.dataMoneyToday.objSimuladorCredito
import com.example.daval.moneytoday.databinding.FragmentSimuladorUIBinding


class SimuladorFragmentUI : Fragment() {
    private lateinit var binding: FragmentSimuladorUIBinding
    private lateinit var adapter: CustomAdapter
    private var credito:Double = 0.0
    private var interes:Double = 0.0
    private var plazo:Int = 0
    private var cuota:Double = 0.0
    private var coutaNumero: Int = 0
    private var abonoCapital: Double = 0.0
    private var intereses: Double = 0.0
    private var saldo:Double = 0.0
    private var matrizCredito: ArrayList<objSimuladorCredito> = ArrayList()

    private lateinit var viewModel: SimuladorViewModel
    private lateinit var viewModelFactory: SimuladorViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_simulador_u_i, null, false)


        binding.btnSimulador.setOnClickListener {
            var Strcredito = binding.simCredito.text.toString()
            var Strinteres = binding.simInteres.text.toString()
            var Strplazo = binding.simPlazo.text.toString()
            if (Strcredito.isNotEmpty() || Strinteres.isNotEmpty() || Strplazo.isNotEmpty()) {
                credito = Strcredito.toDouble()
                interes = Strinteres.toDouble()
                plazo = Strplazo.toInt()

            }
            viewModelFactory = SimuladorViewModelFactory(credito,interes, plazo)
            viewModel = ViewModelProvider(this, viewModelFactory).get(SimuladorViewModel::class.java)
            viewModel.credito.observe(viewLifecycleOwner, Observer { newCredito ->
                binding.simCreditResMonto.text = newCredito.toString()
            })
            viewModel.interes.observe(viewLifecycleOwner, Observer { newInteres ->
                binding.simCreditResTasaInt.text = newInteres.toString()
            })
            viewModel.plazo.observe(viewLifecycleOwner, Observer { newPlazo ->
                binding.simCreditResPlazo.text = newPlazo.toString()
            })
            viewModel.cuota.observe(viewLifecycleOwner, Observer { newCuota ->
                binding.simCreditResCuota.text = newCuota.toString()
            })
            viewModel.intereses.observe(viewLifecycleOwner, Observer { newInteresesTot ->
                binding.simCreditResIntTotal.text = newInteresesTot.toString()
            })

            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().applicationWindowToken, 0)
        }
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }
}