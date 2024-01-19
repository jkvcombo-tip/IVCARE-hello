package com.example.ivcare.updatePatient

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment

import com.example.ivcare.R
import com.example.ivcare.databinding.FragmentUpdatePatientBinding
import com.example.ivcare.patientDatabase.Patient
import com.example.ivcare.patientDatabase.PatientDatabase
import com.example.ivcare.patientDatabase.PatientRepository




class UpdatePatientFragment : Fragment() {

    private lateinit var updatePatientViewModel: UpdatePatientViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentUpdatePatientBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_update_patient,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val patientId = requireArguments().getInt("patient_id")
        val dao = PatientDatabase.getInstance(application).patientDao
        val repository = PatientRepository(dao)
        val factory = UpdatePatientViewModelFactory(patientId, repository)
        updatePatientViewModel = ViewModelProvider(this, factory)[UpdatePatientViewModel::class.java]

        binding.updatePatientViewModel = updatePatientViewModel


        updatePatientViewModel.navigateToHome.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                //val action = UpdatePatientFragmentDirections.actionUpdatePatientFragmentToPatientDisplayFragment()
                val action = UpdatePatientFragmentDirections.actionUpdatePatientFragmentToPatientDisplayFragment()
                NavHostFragment.findNavController(this).navigate(action)
                updatePatientViewModel.doneNavigating()
            }
        })



//        updatePatientViewModel.getPatient().observe(viewLifecycleOwner, Observer {
//            Toast.makeText(activity, "Patient ID: ${it.patientId}", Toast.LENGTH_SHORT).show()
//        })

        fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

        updatePatientViewModel.getPatient().observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "Patient ID: ${it.patientId}", Toast.LENGTH_SHORT).show()
        })


        binding.lifecycleOwner = this
        return binding.root
    }

}