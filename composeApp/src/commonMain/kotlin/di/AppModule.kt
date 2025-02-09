package di

import platform.LocationClient
import database.AppDatabase
import feature_home.homeModule
import feature_post.postModule
import feature_profile.profileModule
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import navigator.navigatorModule
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule : Module
    get() =  module {
        includes(
            platformCoreModule,
            commonCoreModule,
            navigatorModule,
            homeModule(LocationClient()),
            postModule,
            profileModule
        )
    }


val commonCoreModule: Module
    get() =  module {
        single<HttpClient> {
            HttpClient {
                install(ContentNegotiation) {
                    json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Application.Json)
                }
            }
        }
    }

