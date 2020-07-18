package com.innoventesmovistv.myapplication

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.innoventesmovistv.myapplication.factory.MoviesViewModelFactory
import com.innoventesmovistv.myapplication.network.MoviesApis
import com.innoventesmovistv.myapplication.network.NetworkConnectionInterceptor
import com.innoventesmovistv.myapplication.repositories.MoviesRepositories
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MoviesApplication : MultiDexApplication(), KodeinAware {

    private var singleton: MoviesApplication? = null

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        configureFresco()
    }

    fun getInstance(): MoviesApplication {
        return singleton!!
    }

    private fun configureFresco() {
        val config = ImagePipelineConfig.newBuilder(this)
            .setDownsampleEnabled(true)
            .build()
        Fresco.initialize(this, config)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MoviesApplication))


        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from provider { MoviesApis(instance()) }



        /**
         * REPOSITORIES
         * =============================================
         * Bind all the repositories that your create here
         * */
        bind() from provider { MoviesRepositories(instance()) }




        /**
         * VIEWMODEL FACTORIES
         * =============================================
         * Bind all the ViewModel Factories that your create here
         * */

        bind() from provider { MoviesViewModelFactory(instance()) }


    }
}