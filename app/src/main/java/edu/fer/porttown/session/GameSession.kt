package edu.fer.porttown.session

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import edu.fer.porttown.model.Building
import edu.fer.porttown.model.Item
import edu.fer.porttown.model.Resource
import edu.fer.porttown.network.NetResource
import edu.fer.porttown.network.building.BuildingRepository
import edu.fer.porttown.network.items.ItemRepository
import edu.fer.porttown.network.resources.ResourceRepository

class GameSession(
    private var resourceRepository: ResourceRepository,
    private var buildingRepository: BuildingRepository,
    private var itemRepository: ItemRepository
) {
    private var _resourcesData = MediatorLiveData<NetResource<List<Resource>>>()
    private var _buildingData = MediatorLiveData<NetResource<List<Building>>>()
    private var _itemData = MediatorLiveData<NetResource<List<Item>>>()

    fun getResourcesData(): LiveData<NetResource<List<Resource>>> {
        return _resourcesData
    }

    fun setResourcesData(source: LiveData<NetResource<List<Resource>>>) {
        if (_resourcesData.value != null) {
            _resourcesData.apply {
                addSource(source, Observer {
                    this.value = it
                })
            }
        }
    }

    fun updateResourcesData() {
        if (_resourcesData.value != null) {
            _resourcesData.postValue(_resourcesData.value)
        }
    }

    fun getProductionRate(): Map<Resource.Type, Long> {
        //TODO: not implemented in backend
        return mapOf()
    }

    fun getItemsData() {}

    fun getBuildingData() {}
}