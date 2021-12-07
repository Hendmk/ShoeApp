package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.udacity.shoestore.databinding.FragmentInstructionsBinding


class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        binding.buttonToShoes.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_instructionsFragment_to_shoeListFragment))

    }

    private fun setToolbar() {
        val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.instructions_title)
    }

}