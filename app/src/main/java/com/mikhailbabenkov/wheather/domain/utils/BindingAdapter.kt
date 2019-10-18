package com.mikhailbabenkov.wheather.domain.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

private val crossfadeFactory: DrawableCrossFadeFactory by lazy {
    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
}

@BindingAdapter("image", "emptyImage", "errorImage", requireAll = false)
fun bindImage(view: ImageView, imageUrl: String?, emptyImage: Drawable?, errorImage: Drawable?) {
    Glide.with(view).load(imageUrl).run {
        emptyImage?.let { placeholder(it) }
        errorImage?.let { error(it) }
        this
    }.transition(DrawableTransitionOptions.withCrossFade(crossfadeFactory)).into(view)
}