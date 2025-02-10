
package di

import org.koin.core.module.Module
import database.DriverFactory
import org.koin.dsl.module
import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import database.AppDatabase

lateinit var appContext: Context

fun initializeAppContext(context: Context) {
    appContext = context
}

actual val platformCoreModule: Module = module {
    single { appContext }
    single<SqlDriver> {
        AndroidDriverFactory(get()).createDriver()
    }
}

class AndroidDriverFactory(private val context: Context): DriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "test.db")
    }
}