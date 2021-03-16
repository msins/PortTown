package edu.fer.porttown.model.helpers

import edu.fer.porttown.model.Resource
import edu.fer.porttown.model.resources.Coal
import edu.fer.porttown.model.resources.Food
import edu.fer.porttown.model.resources.Gold
import edu.fer.porttown.model.resources.Iron
import edu.fer.porttown.model.resources.Stone
import edu.fer.porttown.model.resources.Wood

object Resources {
    fun createAllEmpty() : List<Resource>{
        return listOf(
            Coal(0),
            Food(0),
            Gold(0),
            Stone(0),
            Wood(0),
            Iron(0)
        )
    }

}