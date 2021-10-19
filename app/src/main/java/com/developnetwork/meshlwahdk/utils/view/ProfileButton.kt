package com.developnetwork.meshlwahdk.utils.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import com.developnetwork.meshlwahdk.R
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.profile_button.view.*

class ProfileButton(context: Context, attrs: AttributeSet?) : MaterialCardView(context, attrs) {
    constructor(context: Context) : this(context, null)

    init {
        inflate(context, R.layout.profile_button, this)
        setBackgroundColor(Color.TRANSPARENT)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.profilebuttonview)
        icon.setImageDrawable(attributes.getDrawable(R.styleable.profilebuttonview_buttonIcon))
        icon.imageTintList = ColorStateList.valueOf(
            attributes.getColor(
                R.styleable.profilebuttonview_buttonIconTint,
                context.resources.getColor(R.color.black)
            )
        )
        name.text = attributes.getString(R.styleable.profilebuttonview_buttonName)
        attributes.recycle()
    }

}