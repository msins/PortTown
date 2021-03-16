package edu.fer.porttown.model.buildings

import edu.fer.porttown.model.AbstractBuilding
import edu.fer.porttown.model.Building
import edu.fer.porttown.model.Resource

class Workshop(level: Int) : AbstractBuilding(level) {
    override fun getType(): Building.Type = Building.Type.WORKSHOP
    override fun getResourceType(): Resource.Type? = null
}