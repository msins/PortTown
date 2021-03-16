package edu.fer.porttown.network

data class NetResource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {

    companion object {
        fun <T> error(data: T?, message: String): NetResource<T> = NetResource(Status.ERROR, data, message)
        fun <T> success(data: T?): NetResource<T> = NetResource(Status.SUCCESS, data, null)
        fun <T> loading(data: T?): NetResource<T> = NetResource(Status.LOADING, null, null)
    }
}