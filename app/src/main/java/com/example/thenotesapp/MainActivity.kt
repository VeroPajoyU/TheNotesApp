package com.example.thenotesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.thenotesapp.database.NoteDatabase
import com.example.thenotesapp.repository.NoteRepository
import com.example.thenotesapp.viewmodel.NoteViewModel
import com.example.thenotesapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity(){

    //Inicialización --> Variable para el ViewModel de notas
    lateinit var noteViewModel: NoteViewModel // Se inicializará más tarde

    /*
    override fun onCreate(savedInstanceState: Bundle?) { // Método que se llama al crear la actividad
        super.onCreate(savedInstanceState) // Llama al método de la clase padre
        setContentView(R.layout.activity_main) // Se obtiene el diseño creado de activity_main.xls
    }
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel() // Asegúrate de llamar a este método aquí
    }

    // Método para configurar el ViewModel
    private fun setupViewModel() {
        // Crea una instancia del repositorio de notas usando la base de datos
        val noteRepository = NoteRepository(NoteDatabase(this))

        // Crea una fábrica para el ViewModel pasando la aplicación y el repositorio
        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepository)

        // Obtiene el ViewModel usando la fábrica y lo asigna a la variable
        noteViewModel = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]
    }
}

/**
 * Este código define la MainActivity de una aplicación de notas.
 * Inicializa la actividad y configura un ViewModel que se encargará de gestionar las notas
 * a través de un repositorio que se conecta a la base de datos.
 * La finalidad de este código es establecer la base para que la actividad maneje
 * las operaciones relacionadas con las notas utilizando el patrón de arquitectura MVVM (Modelo-Vista-ViewModel).
 * */