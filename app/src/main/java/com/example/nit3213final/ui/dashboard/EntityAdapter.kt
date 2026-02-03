package com.example.nit3213final.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213final.data.model.Entity
import com.example.nit3213final.databinding.ItemEntityBinding

class EntityAdapter(
    private val onClick: (Entity) -> Unit
) : RecyclerView.Adapter<EntityAdapter.VH>() {

    private val items = mutableListOf<Entity>()

    fun submit(list: List<Entity>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class VH(val binding: ItemEntityBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemEntityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val entity = items[position]

        // ✅ Dashboard must NOT show description
        val entries = entity.fields.entries.toList()

        val primary = entity.id?.let { "ID: $it" }
            ?: entries.getOrNull(0)?.let { "${it.key}: ${it.value}" }
            ?: "Entity"

        val secondary = entries.getOrNull(1)?.let { "${it.key}: ${it.value}" } ?: ""

        holder.binding.tvPrimary.text = primary
        holder.binding.tvSecondary.text = secondary

        holder.binding.root.setOnClickListener { onClick(entity) }
    }

    override fun getItemCount(): Int = items.size
}