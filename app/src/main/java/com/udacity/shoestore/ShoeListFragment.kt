package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.squareup.picasso.Picasso
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        startObserving()
        binding.addNewShowButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_shoeListFragment_to_newShoeDetailFragment))
    }

    private fun setToolbar() {
        val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.shoe_list)
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun startObserving() {
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { list ->
            binding.shoesList.removeAllViews()
            for (shoe in list) {
                addShoe(shoe)
            }
        })
    }

    private fun addShoe(shoe: Shoe) {
        val view = layoutInflater.inflate(R.layout.shoe_itemview, null, false)
        val image: ImageView = view.findViewById(R.id.shoeImage)
        val name: TextView = view.findViewById(R.id.shoeName)
        val description: TextView = view.findViewById(R.id.shoeDescription)
        val size: TextView = view.findViewById(R.id.shoeSize)
        val company: TextView = view.findViewById(R.id.company)

        name.text = shoe.name
        description.text = shoe.description
        size.text = getString(R.string.size, shoe.size.toString())
        company.text = shoe.company

        try {
            Picasso.get()
                .load(shoe.images[0])
                .placeholder(R.drawable.ic_basic_image_alt)
                .noFade().into(image);

        } catch (e: Exception) {
            Timber.e("Glide Exception4: ${e.localizedMessage}")
        }

        binding.shoesList.addView(view)
    }

}