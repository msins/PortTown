package edu.fer.porttown.model.helpers

import edu.fer.porttown.model.Building
import edu.fer.porttown.model.buildings.CoalMine
import edu.fer.porttown.model.buildings.Farm
import edu.fer.porttown.model.buildings.FoodSilo
import edu.fer.porttown.model.buildings.GoldMine
import edu.fer.porttown.model.buildings.IronMine
import edu.fer.porttown.model.buildings.Palace
import edu.fer.porttown.model.buildings.Quarry
import edu.fer.porttown.model.buildings.SawMill
import edu.fer.porttown.model.buildings.Storage
import edu.fer.porttown.model.buildings.Workshop

object Buildings {
    //TODO: all 0 because there's no api i can fetch production per second and storage amount so it's just 0
    fun createAllBuildings(): List<Building> {
        return listOf(
            CoalMine(0, 0),
            GoldMine(0, 0),
            IronMine(0, 0),
            Quarry(0, 0),
            SawMill(0, 0),
            Farm(0, 0),
            FoodSilo(0),
            Storage(0),
            Workshop(0),
            Palace(0)
        )
    }
}