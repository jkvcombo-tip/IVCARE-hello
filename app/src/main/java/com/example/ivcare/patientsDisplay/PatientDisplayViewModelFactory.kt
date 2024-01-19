package com.example.ivcare.patientsDisplay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ivcare.patientDatabase.PatientRepository


class PatientDisplayViewModelFactory(private val repository: PatientRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientDisplayViewModel::class.java)) {
            return PatientDisplayViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}