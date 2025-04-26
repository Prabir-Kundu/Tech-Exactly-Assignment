package com.prabirkundu.techexactlyassignment.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.prabirkundu.techexactlyassignment.MainActivity
import com.prabirkundu.techexactlyassignment.R
import com.prabirkundu.techexactlyassignment.Util.Resource
import com.prabirkundu.techexactlyassignment.ViewModel.MainViewModel
import com.prabirkundu.techexactlyassignment.adapter.AppListAdapter
import com.prabirkundu.techexactlyassignment.databinding.FragmentApplicationsBinding

class ApplicationsFragment : Fragment() {
    private val TAG = "ApplicationsFragment"
    private var _binding:FragmentApplicationsBinding?=null
    private val binding get() = _binding!!
    lateinit var viewModel: MainViewModel
    var appAdapter:AppListAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentApplicationsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observer()
    }

    private fun initView() {
        binding.rvAppList.layoutManager = LinearLayoutManager(requireActivity())
        viewModel = (activity as MainActivity).viewModel
        viewModel.getAppLIst("378")

        binding.editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                appAdapter?.getFilter()?.filter(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun observer() {
        viewModel.appList.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                    //binding.llLoading.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    var appList = it.data
                    if (appList != null) {
                        for (item in appList) {
                            Log.e(TAG, "observer: "+item.app_name )
                        }
                        appAdapter = AppListAdapter(requireActivity(),appList)
                        binding.rvAppList.adapter = appAdapter
                        binding.rvAppList.visibility = View.VISIBLE


                    }
                    binding.clMailItem.visibility = View.VISIBLE
                    binding.llLoading.visibility = View.GONE
                }

                is Resource.Error -> {
                    //Toast.makeText(requireActivity(),it.message,Toast.LENGTH_SHORT).show()
                    binding.llLoading.visibility = View.GONE
                    binding.llErrorMessage.visibility = View.VISIBLE
                    binding.txtErrorMessage.text = it.message
                }
            }
        })
    }
}