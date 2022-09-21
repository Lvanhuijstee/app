package com.example.accesment


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.accesment.databinding.ItemTodoBinding
import com.squareup.picasso.Picasso


class NasaAdapter : RecyclerView.Adapter<NasaAdapter.NasaViewHolder>() {

    inner class NasaViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<NasaApiX>() {
        override fun areContentsTheSame(oldItem: NasaApiX, newItem: NasaApiX): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: NasaApiX, newItem: NasaApiX): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var todos: List<NasaApiX>
            get() = differ.currentList
            set(value){differ.submitList(value) }

    override fun getItemCount() = todos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaViewHolder {
        return  NasaViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: NasaViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            picTitle.text = todo.title
            Date.text = todo.date
            Picasso.get().load(todo.hdurl).into(imageView)
        }
    }
}