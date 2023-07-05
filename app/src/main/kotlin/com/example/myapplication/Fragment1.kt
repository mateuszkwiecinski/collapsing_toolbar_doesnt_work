package com.example.myapplication

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import androidx.core.view.doOnLayout
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ItemContentBinding
import com.example.myapplication.databinding.ReproBinding
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.absoluteValue

class Fragment1: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ReproBinding.inflate(layoutInflater)

        List(10) {
            val item = ItemContentBinding.inflate(layoutInflater, binding.contentContainer, true)
            item.label.text = "Item ($it)"
        }

        val trigger = binding.contentContainer.children.drop(2).first().let(ItemContentBinding::bind)
        trigger.label.text = "Navigate forward and back to recreate view"
        trigger.root.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, Fragment2())
                .addToBackStack(null)
                .commit()
        }

        binding.appBar.applyInsetter {
            type(statusBars = true) {
                padding(top = true)
            }
        }
        binding.contentContainer.applyInsetter {
            type(WindowInsetsCompat.Type.systemBars()) {
                padding(bottom = true, horizontal = true)
            }
        }

        return binding.root
    }
}