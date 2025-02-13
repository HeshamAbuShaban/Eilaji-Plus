package dev.training.eilaji_plus.di

import android.content.Context
import android.content.SharedPreferences
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.training.eilaji_plus.R
import dev.training.eilaji_plus.core.Keys
import dev.training.eilaji_plus.core.PreferencesManager
import dev.training.eilaji_plus.utils.location.LocationController
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*
        @Singleton
        @Provides
        fun provideRunningDatabase(
            @ApplicationContext applicationContext: Context,
        ) = Room.databaseBuilder(
            context = applicationContext,
            klass = RunningDatabase::class.java,
            name = RUNNING_DATABASE_NAME
        ).build()


        @Singleton
        @Provides
        fun provideRunningDao(db: RunningDatabase) = db.getRunDao()
    */

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext
        context: Context,
    ) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_visibility_off)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences =
        context.getSharedPreferences(Keys.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providePreferencesManager(
        sharedPreferences: SharedPreferences
    ) = PreferencesManager(sharedPreferences)


    @Singleton
    @Provides
    fun provideLocationController(
        @ApplicationContext context: Context,
    ): LocationController = LocationController(context)

}