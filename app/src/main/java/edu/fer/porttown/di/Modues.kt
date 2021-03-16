package edu.fer.porttown.di

import edu.fer.porttown.network.auth.AuthRepository
import edu.fer.porttown.network.auth.AuthService
import edu.fer.porttown.network.building.BuildingRepository
import edu.fer.porttown.network.building.BuildingService
import edu.fer.porttown.network.items.ItemRepository
import edu.fer.porttown.network.items.ItemService
import edu.fer.porttown.network.resources.ResourceRepository
import edu.fer.porttown.network.resources.ResourceService
import edu.fer.porttown.session.GameSession
import edu.fer.porttown.session.SessionManager
import edu.fer.porttown.util.Api
import edu.fer.porttown.viewmodels.AuthViewModel
import edu.fer.porttown.viewmodels.GameViewModel
import edu.fer.porttown.viewmodels.MarketViewModel
import edu.fer.porttown.viewmodels.TownViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
val appModule = module {
    factory { GameSession(get(), get(), get()) }
    single { SessionManager() }

    viewModel { AuthViewModel(sessionManager = get(), authRepository = get()) }
    viewModel { MarketViewModel(sessionManager = get()) }
    viewModel { TownViewModel(sessionManager = get()) }
    viewModel {
        GameViewModel(
            sessionManager = get(),
            gameSession = get(),
            resourceRepository = get(),
            buildingRepository = get(),
            itemRepository = get()
        )
    }
}

val netModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { provideAuthApi(retrofit = get()) }
    factory { provideBuildingApi(retrofit = get()) }
    factory { provideResourceApi(retrofit = get()) }
    factory { provideItemApi(retrofit = get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideAuthApi(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)
fun provideResourceApi(retrofit: Retrofit): ResourceService =
    retrofit.create(ResourceService::class.java)

fun provideBuildingApi(retrofit: Retrofit): BuildingService =
    retrofit.create(BuildingService::class.java)

fun provideItemApi(retrofit: Retrofit): ItemService = retrofit.create(ItemService::class.java)

@ExperimentalCoroutinesApi
val repositoryModule = module {
    single { AuthRepository(authService = get()) }
    single { ResourceRepository(resourceService = get()) }
    single { BuildingRepository(buildingService = get()) }
    single { ItemRepository(itemService = get()) }
}