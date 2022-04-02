package nam.zuchu.asm.models.types

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class TypesItem(

    @Expose
    val idType: Int,
    @Expose
    val status: String,

    @Expose
    val typeOfName: String,
) : Parcelable