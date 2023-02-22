package com.joerakhimov.niceweather.forecast

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joerakhimov.niceweather.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_forecast.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ForecastFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ForecastFragment : Fragment() {

    @Inject
    lateinit var api: ForecastApi

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    var recyclerForecast: RecyclerView? = null
    var buttonLocation: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        getForecast()
        val view = inflater.inflate(R.layout.fragment_forecast, container, false)
        recyclerForecast = view.findViewById(R.id.recycler)
        buttonLocation = view.findViewById(R.id.button)
        buttonLocation?.setOnClickListener {

            // open destination
//            val navController = it.findNavController()
//            navController.navigate(R.id.actionLocation)

            // pass data to screen
//            val action = ForecastFragmentDirections.actionLocation("Tashkent")
//            it.findNavController().navigate(action)

//            val globalAction = ForecastFragmentDirections.actionGlobalLocation("Tashkent")
//            it.findNavController().navigate(globalAction)

            it.findNavController().navigate(Uri.parse("niceweather://home/location/Tashkent"))

        }
        getForecast()
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ForecastFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ForecastFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun getForecast() {
        runBlocking {
            withContext(Dispatchers.IO) {
                val forecast = api.getForecast()
                forecast.daily?.let { showForecast(forecast) }
            }
        }
    }

    private fun showForecast(forecast: ForecastResponse) {
//        activity?.title = forecast.location?.name
        if(forecast.daily!=null){
            recyclerForecast?.layoutManager = LinearLayoutManager(context)
            recyclerForecast?.adapter = ForecastAdapter(forecast.daily)
        }
    }

}