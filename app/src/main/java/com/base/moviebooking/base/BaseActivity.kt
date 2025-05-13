package com.base.moviebooking.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.base.moviebooking.network.NetworkCheckerInterceptor.NoConnectivityException
import com.base.moviebooking.network.model.ApiObjectResponse
import com.base.moviebooking.network.model.RequestError
import com.base.moviebooking.utils.Define
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import retrofit2.adapter.rxjava2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.Objects


abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: T

    var currentApiVersion = 0
    var lastClickTime: Long = System.currentTimeMillis()
    protected lateinit var mViewController: ViewController

    abstract fun getFragmentContainerId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        mViewController = ViewController(supportFragmentManager, getFragmentContainerId())
        currentApiVersion = Build.VERSION.SDK_INT
        val flags =
            (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = flags
            val decorView = window.decorView
            decorView
                .setOnSystemUiVisibilityChangeListener { visibility ->
                    if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                        decorView.systemUiVisibility = flags
                    }
                }
        }
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(getInflatedLayout(layoutInflater))
        initView()
        initEvent()
    }

    override fun onBackPressed() {
        if (mViewController != null && mViewController!!.currentFragment != null) {
            if (mViewController!!.currentFragment.backPressed()) {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    abstract fun setBinding(layoutInflater: LayoutInflater): T

    abstract fun initView()

    abstract fun initEvent()

    private fun getInflatedLayout(inflater: LayoutInflater): View {
        binding = setBinding(inflater)
        return binding.root
    }

    inline fun <reified T : Activity> showActivity(
        isClear: Boolean = false,
        isClearTop: Boolean = false,
        bundle: Bundle? = null,
        configureIntent: Intent.() -> Unit = {}
    ) {
        val intent = Intent(this, T::class.java)
        if (isClear) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        if (isClearTop) intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        if (bundle != null) intent.putExtras(bundle)
        intent.apply(configureIntent)
        startActivity(intent)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }


    @Nullable
    fun handleNetworkError(throwable: Throwable, isShowDialog: Boolean): RequestError? {
        var requestError = RequestError()

        if (throwable is NoConnectivityException) {
            requestError!!.errorCode = Define.Api.Error.NO_NETWORK_CONNECTION
            requestError!!.errorMessage = throwable.message
        } else if (throwable is HttpException) {
            val httpException: HttpException = throwable as HttpException
            val errorBody: String
            try {
                errorBody =
                    Objects.requireNonNull(httpException.response()?.errorBody())?.string() ?: ""
                val gson = GsonBuilder().create()
                val apiResponse = gson.fromJson(
                    errorBody,
                    ApiObjectResponse::class.java
                )
                if (apiResponse != null && apiResponse.requestError != null) {
                    requestError = apiResponse.requestError
                } else {
                    requestError!!.errorCode = java.lang.String.valueOf(httpException.code())
                }
            } catch (e: IOException) {
                requestError!!.errorCode = Define.Api.Error.TIME_OUT
            } catch (e: IllegalStateException) {
                requestError!!.errorCode = Define.Api.Error.TIME_OUT
            } catch (e: JsonSyntaxException) {
                requestError!!.errorCode = Define.Api.Error.TIME_OUT
            }
        } else if (throwable is ConnectException
            || throwable is SocketTimeoutException
            || throwable is UnknownHostException
            || throwable is IOException
        ) {
            requestError!!.errorCode = Define.Api.Error.TIME_OUT
        } else {
            requestError!!.errorCode = Define.Api.Error.UNKNOWN
        }

        if (isShowDialog && requestError != null) {
            Toast.makeText(this, requestError.errorMessage, Toast.LENGTH_LONG).show()
        }

        return requestError
    }

    fun avoidDuplicateClick(): Boolean {
        val now = System.currentTimeMillis()
        if (now - lastClickTime < Define.CLICK_TIME_INTERVAL) {
            return true
        }
        lastClickTime = now
        return false
    }

    override fun onStop() {
        super.onStop()
    }
}