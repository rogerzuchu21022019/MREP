package nam.zuchu.asm.adapters.types

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import nam.zuchu.asm.R
import nam.zuchu.asm.models.userswithtypes.UsersWithTypesItem

class BindingLoadImageAdapter {
    companion object{
        @JvmStatic
        @BindingAdapter("android:loadImage")
        fun loadImage(imageView: ImageView,Url:String){
            Glide.with(imageView).load(Url).placeholder(R.drawable.girl).error(R.drawable.girl).into(imageView)
        }

    }
}