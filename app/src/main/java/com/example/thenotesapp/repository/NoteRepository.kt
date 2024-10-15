package com.example.thenotesapp.repository

import com.example.thenotesapp.database.NoteDatabase
import com.example.thenotesapp.model.Note

class NoteRepository(private val db: NoteDatabase) {

    // Inserta/Elimina/Actualiza una nota en la base de datos utilizando el DAO.
    // La función es suspendida, por lo que debe ejecutarse en una corrutina (operación asincrónica).
    // Al ser suspendida, permite ejecutar estas operaciones sin bloquear el hilo principal de la aplicación, mejorando el rendimiento y la fluidez.
    suspend fun insertNote(note: Note) = db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)

    // Recupera todas las notas almacenadas en la base de datos.
    // No es suspendida porque puede devolver datos en tiempo real o en un flujo (LiveData/Flow).
    fun getAllNote() = db.getNoteDao().getAllNotes()

    // Busca notas en la base de datos basándose en un criterio de búsqueda.
    // No es suspendida y retorna los resultados directamente.
    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)
}

/*
* Este repositorio es parte del patrón Repository en Android,
* que ayuda a separar las preocupaciones de la fuente de datos del resto de la aplicación.
* Esto hace que el código sea más fácil de mantener y probar.
* */