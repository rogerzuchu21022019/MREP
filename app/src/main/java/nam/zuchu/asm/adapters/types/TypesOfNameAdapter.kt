package nam.zuchu.asm.adapters.types

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        RecyclerView.ViewHolder(itemRvTypesOfNameBinding.root) {
        init {
            itemRvTypesOfNameBinding.root.setOnClickListener {
                onClickItemInRecyclerView.onItemClick(adapterPosition)
            }
            onClickItemInRecyclerView.onItemClick(adapterPosition)
        }
    }
    fun setOnClickItem(onClickItemInRecyclerView: OnClickItemInRecyclerView){
        this.onClickItemInRecyclerView=onClickItemInRecyclerView
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
        var currentTypesOfName = listTypesOfName[position]
        itemRvTypesOfNameBinding.types = currentTypesOfName
    }

    override fun getItemCount(): Int {
        return listTypesOfName.size
    }

    interface OnClickItemInRecyclerView {
        fun onItemClick(position: Int)
    }
}