package com.example.thenotesapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thenotesapp.repository.NoteRepository

class NoteViewModelFactory(val app: Application, private val noteRepository: NoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app, noteRepository) as T
    }
}

/*
* Este código crea una "fábrica" llamada NoteViewModelFactory que se encarga de hacer
* y entregar un NoteViewModel cuando se necesita.
* Como el NoteViewModel requiere información extra (como la aplicación y el repositorio),
* no se puede crear normalmente.
* Por eso, se usa esta fábrica que recibe esos datos y luego crea el NoteViewModel correctamente.
*
* En resumen, este código asegura que el NoteViewModel se cree con la información necesaria.
* */