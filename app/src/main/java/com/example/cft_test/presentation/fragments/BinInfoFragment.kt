package com.example.cft_test.presentation.fragments

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cft_test.App
import com.example.cft_test.databinding.FragmentBinInfoBinding
import com.example.cft_test.domain.entity.BinInfo
import com.example.cft_test.domain.entity.DescriptionContent
import com.example.cft_test.presentation.adapters.BinInfoAdapter
import com.example.cft_test.presentation.descriptions.Description
import com.example.cft_test.presentation.viewModels.BinInfoViewModel
import com.example.cft_test.presentation.viewModels.InitialViewModel
import com.example.cft_test.presentation.viewModelsFactory.ViewModelsFactory
import javax.inject.Inject

class BinInfoFragment : Fragment() {

    private var binInfoParc: Parcelable? = null
    private var binInfo: BinInfo? = null
    private var binNumber: String? = null
    private lateinit var binding: FragmentBinInfoBinding
    private lateinit var adapter: BinInfoAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelsFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[BinInfoViewModel::class.java]
    }

    private val component by lazy {
        ((requireActivity().application) as App).component
    }

    override fun onAttach(context: Context) {
        component.injectBinInfoFragment(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            binInfoParc = it.getParcelable(BIN_INFO)
            binInfo = binInfoParc as BinInfo
            binNumber = it.getString(BIN_NUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBinInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareViews()
        prepareRecyclerView()
    }

    //set text of bin number
    private fun prepareViews() {
        binding.binNumber.text = binNumber
    }

    //settings recyclerView
    private fun prepareRecyclerView() {
        adapter = BinInfoAdapter()
        binding.infoRv.adapter = adapter
        binding.infoRv.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false)
        val list = viewModel.prepareListItems(binInfo ?: return)
        adapter.submitList(list)
        setupClickListenersRV(list)
    }

    //setupClickListeners
    private fun setupClickListenersRV(list: List<DescriptionContent>){
        adapter.clickListener = {
            when (it.description){
                Description.PHONE_BANK -> phoneCall(it.content)
                Description.COUNTRY_NAME, Description.LATITUDE, Description.LONGITUDE -> transformList(list)
                Description.URL_BANK -> goToUrl(it.content)
                else -> throw RuntimeException("unknown listener")
            }
        }
    }

    //opens an application that can open a web page
    private fun goToUrl(content: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        val parsedContent = if (!content.startsWith("http://") && !content.startsWith("https://")){
            "http://$content"
        }else content
        intent.data = Uri.parse(parsedContent)
        startActivity(Intent.createChooser(intent,"Choose browser"))
    }

    //all fields are processed to find the latitude and longitude
    private fun transformList(list: List<DescriptionContent>){
        var lat = "0"
        var long = "0"
        for (content in list){
            if (content.description == Description.LATITUDE){
                lat = content.content
            }
            if (content.description == Description.LONGITUDE){
                long = content.content
            }
        }
        openMap(lat, long)
    }

    //opens an application map
    private fun openMap(lat:String, long:String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$lat,$long"))
        startActivity(intent)
    }

    //it checks if there is permission to make calls, if not, then permission is requested. If yes, a call is made.
    private fun phoneCall(number: String){
        if (checkPermission()){
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
        }else{
            requestPermission()
        }
    }

    //check permission
    private fun checkPermission():Boolean{
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(){
        val permission: Array<String> = arrayOf(Manifest.permission.CALL_PHONE)
        ActivityCompat.requestPermissions(requireActivity(), permission, 100)
    }

    companion object {

        private const val BIN_INFO = "Bin Info"
        private const val BIN_NUMBER = "Bin Number"

        fun newInstance(binInfo: BinInfo, binNumber: String) =
            BinInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(BIN_INFO, binInfo)
                    putString(BIN_NUMBER, binNumber)
                }
            }
    }
}