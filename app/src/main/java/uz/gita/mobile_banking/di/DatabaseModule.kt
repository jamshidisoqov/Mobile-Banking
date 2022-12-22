package uz.gita.mobile_banking.di

import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.mobile_banking.BuildConfig
import uz.gita.mobile_banking.data.remote.authenticator.TokenAuthenticator
import javax.inject.Singleton

// Created by Jamshid Isoqov on 12/22/2022
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val SHARED_NAME: String = "app_data"
    private const val SHARED_MODE: Int = Context.MODE_PRIVATE


    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext ctx: Context): SharedPreferences {
        return ctx.getSharedPreferences(SHARED_NAME, SHARED_MODE)
    }

    @[Provides Singleton]
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @[Provides Singleton]
    fun provideClient(@ApplicationContext ctx: Context): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(ctx).build())
        .authenticator(TokenAuthenticator())
        .build()


    @[Provides Singleton]
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}