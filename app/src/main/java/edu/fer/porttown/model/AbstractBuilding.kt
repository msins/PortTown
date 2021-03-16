package edu.fer.porttown.model

abstract class AbstractBuilding(private var level: Int) : Building {
    override fun getLevel(): Int = level
}