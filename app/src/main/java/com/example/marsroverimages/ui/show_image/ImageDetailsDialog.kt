package com.example.marsroverimages.ui.show_image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.marsroverimages.R
import com.example.marsroverimages.databinding.DialogImageDetailsBinding


class ImageDetailsDialog : DialogFragment() {

    private val sharedViewModel: ShowImageViewModel by activityViewModels()
    private lateinit var viewDataBinding: DialogImageDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DialogImageDetailsBinding.inflate(inflater, container, false).apply {
            vm = sharedViewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.attributes?.windowAnimations = R.style.ImageDetailAnimation
    }
}