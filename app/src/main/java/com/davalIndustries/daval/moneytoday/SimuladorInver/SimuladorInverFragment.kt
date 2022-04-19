package com.davalIndustries.daval.moneytoday.SimuladorInver

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.davalIndustries.daval.moneytoday.MTUtilities
import com.davalIndustries.daval.moneytoday.R
import com.davalIndustries.daval.moneytoday.databinding.FragmentSimuladorInversionesBinding


class SimuladorInverFragment : Fragment() {


    private lateinit var binding: FragmentSimuladorInversionesBinding
    private var credito:Double = 0.0
    private var interes:Double = 0.0
    private var plazo:Int = 0
    private var utilities : MTUtilities = MTUtilities()
    private lateinit var viewModel: SimuladorInverViewModel
    private lateinit var viewModelFactory: SimuladorInverViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_simulador_inversiones, null, false)


        binding.btnSimuladorInver.setOnClickListener {
            var Strcredito = binding.simCredito.text.toString()
            var Strinteres = binding.simInteres.text.toString()
            var Strplazo = binding.simPlazo.text.toString()
            if (Strcredito.isNotEmpty() || Strinteres.isNotEmpty() || Strplazo.isNotEmpty()) {
                credito = Strcredito.toDouble()
                interes = Strinteres.toDouble()
                plazo = Strplazo.toInt()

            }

            viewModelFactory = SimuladorInverViewModelFactory(credito,interes, plazo)
            viewModel = ViewModelProvider(this, viewModelFactory).get(SimuladorInverViewModel::class.java)
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
                context?.resources?.let { it1 -> binding.simCreditResIntTotal.setTextColor(it1.getColor(R.color.colorInversiones)) }
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