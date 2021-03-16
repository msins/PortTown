package edu.fer.porttown.ui.game

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import edu.fer.porttown.R
import edu.fer.porttown.model.Resource

class ResourceItem(val resource: Resource) : AbstractItem<ResourceItem.ViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.fragment_single_resource
    override val type: Int
        get() = R.id.resource_item_id

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    class ViewHolder(var view: View) : FastAdapter.ViewHolder<ResourceItem>(view) {
        var resourceImage: ImageView = itemView.findViewById(R.id.resource_image)
        var resourceName: TextView = itemView.findViewById(R.id.resource_name)
        var resourceCount: TextView = itemView.findViewById(R.id.resource_count)

        override fun bindView(item: ResourceItem, payloads: List<Any>) {
            val resource = item.resource
            resourceName.text = resourceName.resources.getString(resource.getNameResource())
            resourceImage.setImageResource(resource.getImageResource())
            resourceCount.text = resource.getCount().toString()
        }

        override fun unbindView(item: ResourceItem) {
            resourceName.text = null
            resourceImage.setImageDrawable(null)
        }

    }

}