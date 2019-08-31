package cn.xin.lib.net

import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

import java.io.IOException
import java.net.Proxy
import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * @author DELL
 * @date 2018年8月28日 028
 * E-Mail:n.zjx@163.com
 * SimpleMvp
 * HttpManager: Http 网络请求管理
 */
object HttpManager {

    private var mRetrofit: Retrofit? = null
    private var mBaseUrl: String? = BASE_URL
    private var mOkHttpClient: OkHttpClient? = null
    private var debug: Boolean? = false

    /**
     * @param mBaseUrl 设置BaseUrl
     * 放在第一位设置
     */
    fun setBaseUrl(mBaseUrl: String): HttpManager {
        this.mBaseUrl = mBaseUrl
        return this
    }

    /**
     * 设置OkHttpClient
     */
    fun setOkHttpClient(okHttpClient: OkHttpClient): HttpManager {
        this.mOkHttpClient = okHttpClient
        return this
    }

    /**
     * @param retrofit 设置retrofit
     * 放在最后设置
     */
    fun setRetrofit(retrofit: Retrofit) {
        this.mRetrofit = retrofit
    }

    /**
     * debug
     */
    fun setDebug(debug: Boolean?): HttpManager {
        this.debug = debug
        return this
    }

    /**
     * @return mRetrofit.create(clazz)
     */
    fun <T> getApiService(clazz: Class<T>): T {
        if (mRetrofit == null) {
            mRetrofit = createRetrofit()
        }
        return mRetrofit!!.create(clazz)
    }

    /**
     * 自带创建retrofit
     */
    private fun createRetrofit(): Retrofit {
        if (mOkHttpClient == null) {
            mOkHttpClient = createDefaultClient()
        }

        val gson = GsonBuilder()
            .registerTypeAdapter(String::class.java, NullStringTypeGsonAdapter())
            .create()

        val builder = Retrofit.Builder()
            .baseUrl(mBaseUrl!!)
            .client(mOkHttpClient!!)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return builder.build()
    }

    /**
     * @return OkHttpclient
     */
    private fun createDefaultClient(): OkHttpClient {
        val timeOut = 10
        val builder = OkHttpClient.Builder()
            .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .proxy(Proxy.NO_PROXY)

        if (debug!!) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))//.addInterceptor(new StackInterceptor())
        }

        return builder.build()
    }

    /**
     * 字符串字段返回 null 解析为 "" 空串
     */
    private class NullStringTypeGsonAdapter : TypeAdapter<String>() {

        @Throws(IOException::class)
        override fun write(out: JsonWriter, value: String?) {
            if (value == null) {
                out.value("")
            } else {
                out.value(value)
            }
        }

        @Throws(IOException::class)
        override fun read(reader: JsonReader): String {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull()
                return ""
            }
            return reader.nextString()
        }
    }

}
