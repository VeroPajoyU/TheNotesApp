package com.example.thenotesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.thenotesapp.model.Note
import com.example.thenotesapp.repository.NoteRepository
import kotlinx.coroutines.launch

// El ViewModel se encarga de manejar los datos para la interfaz de usuario y mantener la lógica de negocio separada
class NoteViewModel(app: Application, private val noteRepository: NoteRepository): AndroidViewModel(app) {

    // Función para agregar/eliminar/actualizar una nueva nota, se ejecuta en un hilo separado usando coroutines (lanzada en viewModelScope)
    // Llama al método de inserción/eliminar/actualizar una nota en el repositorio

    fun addNote(note: Note) =
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    fun deleteNote(note: Note) =
        viewModelScope.launch {
            noteRepository.deleteNote(note) // Llama al método para eliminar la nota en el repositorio
        }
    fun updateNote(note: Note) =
        viewModelScope.launch {
            noteRepository.updateNote(note) // Llama al método para actualizar la nota en el repositorio
        }

    // Función para obtener todas las notas, no necesita correr en un hilo separado porque devuelve un `LiveData`
    fun getAllNotes() = noteRepository.getAllNote() // Recupera todas las notas desde el repositorio

    // Función para buscar una nota a través de una consulta de texto, devuelve las notas que coincidan con la búsqueda
    fun searchNote(query: String?) =
        noteRepository.searchNote(query) // Llama al método de búsqueda de notas en el repositorio
}

/*
* Este código define un ViewModel llamado NoteViewModel, que maneja todas las operaciones relacionadas
* con las notas en una aplicación, como agregar, eliminar, actualizar, obtener todas las notas y buscarlas.
* Utiliza un repositorio (NoteRepository) para interactuar con la base de datos
* y ejecutar estas operaciones en segundo plano usando coroutines,
* lo que evita que la interfaz de usuario se bloquee mientras se realizan estas tareas.
*
* En resumen, este código organiza cómo se gestionan los datos de las notas
* y se asegura de que las operaciones se realicen de manera eficiente y sin interrumpir la app.
* */