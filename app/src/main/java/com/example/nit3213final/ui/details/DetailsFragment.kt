package com.example.nit3213final.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.nit3213final.R
import com.example.nit3213final.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        val entity = args.entity

        val sb = StringBuilder()
        entity.id?.let { sb.append("id: ").append(it).append("\n") }
        entity.fields.forEach { (k, v) ->
            sb.append(k).append(": ").append(v).append("\n")
        }

        binding.tvFields.text = sb.toString().trim()

        // ✅ Details MUST include description
        binding.tvDescription.text = entity.description ?: "(none)"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}