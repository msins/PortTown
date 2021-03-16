package edu.fer.porttown.model.buildings

import edu.fer.porttown.model.AbstractBuilding
import edu.fer.porttown.model.Building
import edu.fer.porttown.model.Resource

class Palace(level: Int) : AbstractBuilding(level) {
    override fun getType(): Building.Type = Building.Type.PALACE
    override fun getResourceType(): Resource.Type? = null
}