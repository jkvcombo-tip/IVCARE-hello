package com.example.ivcare.updatePatient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ivcare.patientDatabase.PatientRepository


class UpdatePatientViewModelFactory(private val patientId: Int , private val repository: PatientRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdatePatientViewModel::class.java)) {
            return UpdatePatientViewModel(patientId, repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}