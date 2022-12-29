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
import uz.gita.mobile_banking.data.remote.api.AuthApi
import uz.gita.mobile_banking.data.remote.api.CardApi
import uz.gita.mobile_banking.data.remote.api.TransferApi
import uz.gita.mobile_banking.data.remote.api.UserApi
import uz.gita.mobile_banking.data.remote.authenticator.TokenAuthenticator
import java.util.concurrent.TimeUnit
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
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }.authenticator(TokenAuthenticator(retrofit.create(AuthApi::class.java),mySharedPrefs,gson))
        .build()


    @[Provides Singleton]
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

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


}