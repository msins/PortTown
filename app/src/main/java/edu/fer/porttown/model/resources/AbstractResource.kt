package edu.fer.porttown.model.resources

import edu.fer.porttown.model.Resource

abstract class AbstractResource(private var count: Long) : Resource {
    override fun getImageResource(): Int = getType().getImageResource()
    override fun getNameResource(): Int = getType().getNameResource()
    override fun getCount(): Long = count
    override fun setCount(count: Long) {
        this.count = count
    }
}