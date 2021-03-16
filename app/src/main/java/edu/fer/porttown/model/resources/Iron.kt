package edu.fer.porttown.model.resources

import edu.fer.porttown.model.Resource

class Iron(count: Long) : AbstractResource(count) {
    override fun getImageResource(): Int = getType().getImageResource()
    override fun getNameResource(): Int = getType().getNameResource()
    override fun getType(): Resource.Type = Resource.Type.IRON
}