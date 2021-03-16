package edu.fer.porttown.model.buildings

import edu.fer.porttown.model.Building
import edu.fer.porttown.model.Resource

class GoldMine(level: Int, rps: Long) : ProducingBuilding(level, rps) {
    override fun getType(): Building.Type = Building.Type.GOLD_MINE
    override fun getResourceType(): Resource.Type = Resource.Type.GOLD
}