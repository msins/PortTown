package edu.fer.porttown.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import edu.fer.porttown.game.GameLoop
import edu.fer.porttown.model.Resource
import edu.fer.porttown.network.NetResource
import edu.fer.porttown.network.building.BuildingRepository
import edu.fer.porttown.network.items.ItemRepository
import edu.fer.porttown.network.resources.ResourceRepository
import edu.fer.porttown.session.GameSession
import edu.fer.porttown.session.SessionManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GameViewModel constructor(
    private val sessionManager: SessionManager,
    private val gameSession: GameSession,
    private val resourceRepository: ResourceRepository,
    private val buildingRepository: BuildingRepository,
    private val itemRepository: ItemRepository
) : ViewModel() {
    private var gameLoop: GameLoop = GameLoop(viewModelScope)
    val resources: LiveData<NetResource<List<Resource>>> get() = gameSession.getResourcesData()

    fun startGameLoop() {
        viewModelScope.launch {
            fetchResources()
        }
        viewModelScope.launch {
            gameLoop.ticker.collect {
                resources.value?.data?.forEach {
                    it.setCount(it.getCount() + addProductionRate(it))
                }
                gameSession.updateResourcesData()
            }
        }
        gameLoop.start()
    }

    private fun fetchResources() {
        viewModelScope.launch {
            val source = resourceRepository.fetchResources(
                sessionManager.getTownId()!!,
                sessionManager.getAuthToken()!!
            )
            gameSession.setResourcesData(source.asLiveData())
        }
    }

    private fun addProductionRate(resource: Resource): Long {
        return gameSession.getProductionRate().getOrDefault(resource.getType(), 0L)
    }

    //TODO: not implemented in backend
    fun syncToCloud() {}
}