package com.example.thenotesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thenotesapp.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    // Método abstracto que expone el DAO para interactuar con la db
    abstract fun getNoteDao(): NoteDao

    companion object {
        // La anotación @Volatile garantiza que los cambios realizados en 'instance'
        // por un subproceso sean visibles inmediatamente para otros subprocesos.
        @Volatile
        private var instance: NoteDatabase? = null

        // LOCK se usa para sincronizar la creación de la instancia de la base de datos
        private val LOCK = Any()

        // Esta función invoca o devuelve la instancia de la db.
        // Si la instancia es nula, se sincroniza el bloque para garantizar que un
        // solo subproceso la cree.
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it // Almacena la instancia creada para usos posteriores
            }
        }

        // Crea la base de datos usando Room.databaseBuilder
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, // Usa el contexto de la aplicación para evitar fugas de memoria
                NoteDatabase::class.java, // Especifica la clase de base de datos
                "note_db" // Nombre de la base de datos
            ).build()
    }
}
