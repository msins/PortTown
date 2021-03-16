package edu.fer.porttown.model.buildings

import edu.fer.porttown.model.AbstractBuilding
import edu.fer.porttown.model.Building

abstract class ProducingBuilding(level: Int, private var rps: Long) : AbstractBuilding(level) {
    override fun getUsage(): Building.Usage = Building.Usage.PRODUCTION
    fun getProductionPerSecond(): Long = rps

}