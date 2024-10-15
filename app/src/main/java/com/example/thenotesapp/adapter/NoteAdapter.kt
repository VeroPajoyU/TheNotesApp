package com.example.thenotesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thenotesapp.databinding.NoteLayoutBinding
import com.example.thenotesapp.fragments.HomeFragmentDirections
import com.example.thenotesapp.model.Note

// El adaptador es el encargado de manejar la lista de notas y cómo se muestra en el RecyclerView
class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    // ViewHolder se encarga de contener las vistas de cada nota
    class NoteViewHolder(val itemBinding: NoteLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    // DiffUtil se utiliza para comparar elementos en la lista y solo actualizar las diferencias
    private val differCallback = object : DiffUtil.ItemCallback<Note>(){

        // Esta función compara si dos notas son las mismas verificando el ID, título y descripción
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteDesc == newItem.noteDesc &&
                    oldItem.noteTitle == newItem.noteTitle
        }

        // Esta función verifica si los contenidos de las notas son idénticos
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem // Comprueba si el objeto completo es igual
        }
    }

    // AsyncListDiffer maneja las diferencias entre la lista antigua y la nueva para actualizar el RecyclerView
    val differ = AsyncListDiffer(this, differCallback)

    // Crea un nuevo ViewHolder cuando se necesita uno nuevo para una nota
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            // Infla el diseño de la nota (note_layout.xml) y lo enlaza con el ViewHolder
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    // Devuelve la cantidad total de notas en la lista
    override fun getItemCount(): Int {
        return differ.currentList.size // Retorna el tamaño de la lista de notas
    }

    // Vincula (muestra) una nota específica en el ViewHolder correspondiente
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position] // Obtiene la nota actual según la posición

        // Asigna el título y la descripción de la nota a las vistas correspondientes
        holder.itemBinding.noteTitle.text = currentNote.noteTitle
        holder.itemBinding.noteDesc.text = currentNote.noteDesc

        // Configura un click listener para navegar al fragmento de edición cuando se selecciona una nota
        holder.itemView.setOnClickListener{
            // Usa SafeArgs para navegar al fragmento de edición de notas con la nota seleccionada
            val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(direction) // Navega al fragmento de edición
        }
    }
}
/*
* Este adaptador maneja la lista de notas, actualiza la pantalla cuando la lista cambia
* y gestiona lo que pasa cuando seleccionas una nota.
* */