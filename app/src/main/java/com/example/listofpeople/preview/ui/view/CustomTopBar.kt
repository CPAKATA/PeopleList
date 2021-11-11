package com.example.listofpeople.preview.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.example.listofpeople.databinding.TopBarBinding

class CustomTopBar(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {
    private var viewBinding: TopBarBinding = TopBarBinding.inflate(LayoutInflater.from(context), this,false)

    init {
        addView(viewBinding.root)
    }

    fun hideButton(){
        viewBinding.btnBack.visibility = View.GONE
    }

    fun showButton(){
        viewBinding.btnBack.visibility = View.VISIBLE
    }

    fun setAction(action: (View) -> Unit){
        viewBinding.btnBack.setOnClickListener(action)
    }

}