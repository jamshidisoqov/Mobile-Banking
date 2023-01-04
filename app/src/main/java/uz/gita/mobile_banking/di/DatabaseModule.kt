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
import uz.gita.mobile_banking.data.local.prefs.MySharedPrefs
import uz.gita.mobile_banking.data.remote.api.*
import uz.gita.mobile_banking.data.remote.authenticator.TokenAuthenticator
import uz.gita.mobile_banking.domain.source.PagingSourceData
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

// Created by Jamshid Isoqov on 12/22/2022
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val SHARED_NAME: String = "app_data"
    private const val SHARED_MODE: Int = Context.MODE_PRIVATE


    lateinit var retrofit: Retrofit

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext ctx: Context): SharedPreferences {
        return ctx.getSharedPreferences(SHARED_NAME, SHARED_MODE)
    }

    @[Provides Singleton]
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @[Provides Singleton]
    fun provideClient(
        @ApplicationContext ctx: Context,
        mySharedPrefs: MySharedPrefs,
        gson: Gson
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(5000L, TimeUnit.MILLISECONDS)
        .writeTimeout(5000L, TimeUnit.MILLISECONDS)
        .addInterceptor(ChuckerInterceptor.Builder(ctx).build())
        .addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            if (mySharedPrefs.accessToken.isNotEmpty())
                requestBuilder.addHeader("Authorization", "Bearer ${mySharedPrefs.accessToken}")
            val response = chain.proceed(requestBuilder.build())
            response
        }.also {
            if (!this::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
        }.authenticator(
            TokenAuthenticator(
                retrofit.create(AuthApi::class.java),
                mySharedPrefs,
                gson
            )
        )
        .build()


    @[Provides Singleton]
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @[Provides Singleton Named("exchange")]
    fun provideExchangeRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_EXCHANGE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @[Provides Singleton]
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @[Provides Singleton]
    fun provideCardApi(retrofit: Retrofit): CardApi = retrofit.create(CardApi::class.java)

    @[Provides Singleton]
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @[Provides Singleton]
    fun provideTransferApi(retrofit: Retrofit): TransferApi =
        retrofit.create(TransferApi::class.java)

    @[Provides Singleton]
    fun provideAuthenticator(
        authApi: AuthApi,
        mySharedPrefs: MySharedPrefs,
        gson: Gson
    ): TokenAuthenticator = TokenAuthenticator(authApi, mySharedPrefs, gson)

    @[Provides Singleton]
    fun provideExchangeApi(@Named("exchange") retrofit: Retrofit): ExchangeApi =
        retrofit.create(ExchangeApi::class.java)

    @[Provides Singleton]
    fun providePagingSource(api: TransferApi, gson: Gson): PagingSourceData =
        PagingSourceData(api, gson)


}