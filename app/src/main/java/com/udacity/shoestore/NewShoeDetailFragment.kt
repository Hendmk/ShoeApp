package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentNewShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class NewShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentNewShoeDetailBinding
    private  val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_new_shoe_detail, container, false)
//        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setListener()
    }

    private fun setListener() {
        binding.cancelButton.setOnClickListener { view ->
            view.findNavController().popBackStack()
        }



        binding.addButton.setOnClickListener { view ->
            viewModel.addShoe(
                Shoe(
                    binding.shoeNameEdit.text.toString(),
                    binding.shoeSizeEdit.text.toString().toDouble(),
                    binding.companyEdit.text.toString(),
                    binding.shoeDescriptionEdit.text.toString(),
                    mutableListOf("")
                )
            )
            
            view.findNavController().popBackStack()
        }
    }

    private fun setToolbar() {
        val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.add_new_shoe)
    }

}