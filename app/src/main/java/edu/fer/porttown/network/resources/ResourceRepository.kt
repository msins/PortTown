package edu.fer.porttown.network.resources

import edu.fer.porttown.model.helpers.Resources
import edu.fer.porttown.network.NetResource
import kotlinx.coroutines.flow.flow

class ResourceRepository(private val resourceService: ResourceService) {
    suspend fun fetchResources(townId: String, token: String) =
        flow {
            emit(NetResource.loading(null))
            try {
                val response = resourceService.getResources(townId, token)
                if (response.isSuccessful && response.body() != null) {
                    response.body()!!.resourceDtos.run {
                        val resources = Resources.createAllEmpty()
                        resources.forEach { resource ->
                            resource.setCount(
                                this.find { it.type == resource.getType() }!!.count
                            )
                        }
                        emit(NetResource.success(resources))
                    }
                }else {
                    emit(NetResource.error(null, "Failed to fetch resources."))
                }
            } catch (t: Throwable) {
                emit(NetResource.error(null, t.message!!))
            }
        }
}