package com.davalIndustries.daval.moneytoday.SimuladorCredito

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.davalIndustries.daval.moneytoday.MTUtilities
import com.davalIndustries.daval.moneytoday.R
import com.davalIndustries.daval.moneytoday.databinding.FragmentSimuladorUIBinding


class SimuladorFragmentUI : Fragment() {
    private lateinit var binding: FragmentSimuladorUIBinding
    private var credito:Double = 0.0
    private var interes:Double = 0.0
    private var plazo:Int = 0
    private var utilities : MTUtilities = MTUtilities()
    private lateinit var viewModel: SimuladorViewModel
    private lateinit var viewModelFactory: SimuladorViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_simulador_u_i, null, false)

        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnSimulador.setOnClickListener {
            val Strcredito = binding.simCredito.text.toString()
            val Strinteres = binding.simInteres.text.toString()
            val Strplazo = binding.simPlazo.text.toString()
            if(!Strcredito.isEmpty() && !Strinteres.isEmpty() && !Strplazo.isEmpty())
            /*credito = Strcredito.toDouble()
            interes = Strinteres.toDouble()
            plazo = Strplazo.toInt()*/
            calcularSimulacion(Strcredito.toDouble(), Strinteres.toDouble(), Strplazo.toInt())
            Log.e("hello",Strcredito)
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().applicationWindowToken, 0)
        }
    }

    private fun calcularSimulacion(credito: Double, interes: Double, plazo: Int) {
        viewModelFactory = SimuladorViewModelFactory(credito,interes, plazo)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SimuladorViewModel::class.java)
        viewModel.credito.observe(viewLifecycleOwner, Observer { newCredito ->

            binding.simCreditResMonto.text = utilities.formattingNumbersToMoney(newCredito)
        })
        viewModel.interes.observe(viewLifecycleOwner, Observer { newInteres ->
            binding.simCreditResTasaInt.text = utilities.formattingNumbersToPercentage(newInteres/100.0)
        })
        viewModel.plazo.observe(viewLifecycleOwner, Observer { newPlazo ->
            binding.simCreditResPlazo.text = newPlazo.toString()
        })
        viewModel.cuota.observe(viewLifecycleOwner, Observer { newCuota ->
            binding.simCreditResCuota.text = utilities.formattingNumbersToMoney(newCuota)
        })
        viewModel.intereses.observe(viewLifecycleOwner, Observer { newInteresesTot ->
            binding.simCreditResIntTotal.text = utilities.formattingNumbersToMoney(newInteresesTot)
            context?.resources?.let { it1 -> binding.simCreditResIntTotal.setTextColor(it1.getColor(R.color.colorRed)) }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }
}