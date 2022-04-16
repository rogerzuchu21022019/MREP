package nam.zuchu.asm.adapters.types

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import nam.zuchu.asm.R
import nam.zuchu.asm.databinding.ItemRvTypesOfNameBinding
import nam.zuchu.asm.models.types.TypesItem

class TypesOfNameAdapter : RecyclerView.Adapter<TypesOfNameAdapter.TypesOfNameViewHolder>() {
    var listTypesOfName: List<TypesItem> = emptyList()
    lateinit var layoutInflater: LayoutInflater
    lateinit var itemRvTypesOfNameBinding: ItemRvTypesOfNameBinding
     lateinit var onClickItemInRecyclerView: OnClickItemInRecyclerView

    class TypesOfNameViewHolder(
        itemRvTypesOfNameBinding: ItemRvTypesOfNameBinding,
         onClickItemInRecyclerView: OnClickItemInRecyclerView
    ) :
        RecyclerView.ViewHolder(itemRvTypesOfNameBinding.root){
        init {
            itemRvTypesOfNameBinding.root.setOnClickListener {
                onClickItemInRecyclerView.onItemClick(absoluteAdapterPosition,it)
            }
            itemRvTypesOfNameBinding.ivUpdate.setOnClickListener {
                onClickItemInRecyclerView.onItemClick(absoluteAdapterPosition,it)

            }
            onClickItemInRecyclerView.onItemClick(absoluteAdapterPosition,itemRvTypesOfNameBinding.root)

        }


    }

    fun setOnClickItem(onClickItemInRecyclerView: OnClickItemInRecyclerView) {
        this.onClickItemInRecyclerView = onClickItemInRecyclerView
    }

    fun setDataForAdapter(listTypesOfName: List<TypesItem>) {
        this.listTypesOfName = listTypesOfName
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesOfNameViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        itemRvTypesOfNameBinding = ItemRvTypesOfNameBinding.inflate(layoutInflater, parent, false)
        return TypesOfNameViewHolder(
            itemRvTypesOfNameBinding = itemRvTypesOfNameBinding,
            onClickItemInRecyclerView = onClickItemInRecyclerView
        )
    }

    override fun onBindViewHolder(holder: TypesOfNameViewHolder, position: Int) {
        val currentTypesOfName = listTypesOfName[position]
        itemRvTypesOfNameBinding.types = currentTypesOfName


    }

    override fun getItemCount(): Int {
        return listTypesOfName.size
    }

    open interface OnClickItemInRecyclerView {
        fun onItemClick(position: Int,view:View)
    }
}