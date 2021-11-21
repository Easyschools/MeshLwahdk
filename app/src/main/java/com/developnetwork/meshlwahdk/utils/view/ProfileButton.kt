package com.developnetwork.meshlwahdk.utils.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.developnetwork.meshlwahdk.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

//import kotlinx.android.synthetic.main.profile_button.view.*

class ProfileButton(context: Context, attrs: AttributeSet?) : MaterialCardView(context, attrs) {
    constructor(context: Context) : this(context, null)

    init {
        inflate(context, R.layout.profile_button, this)
        setBackgroundColor(Color.TRANSPARENT)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.profilebuttonview)

        val icon = findViewById<ShapeableImageView>(R.id.icon)
        val name = findViewById<MaterialTextView>(R.id.name)

        icon.setImageDrawable(attributes.getDrawable(R.styleable.profilebuttonview_buttonIcon))
        icon.imageTintList = ColorStateList.valueOf(
            attributes.getColor(
                R.styleable.profilebuttonview_buttonIconTint,
                ResourcesCompat.getColor(context.resources,R.color.black,null)
            )
        )
        name.text = attributes.getString(R.styleable.profilebuttonview_buttonName)
        attributes.recycle()
    }

}