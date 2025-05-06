package com.base.moviebooking.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.base.moviebooking.utils.Define
import com.base.moviebooking.utils.DialogUtil
import java.io.Serializable


abstract class BaseFragment<T : ViewBinding> : Fragment() {
    protected lateinit var binding: T

    abstract fun getViewBinding(): T

    private var lastClickTime: Long = 0

    protected lateinit var mViewController: ViewController
    abstract fun initView()
    abstract fun initData()
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    fun setViewController(viewController: ViewController) {
        this.mViewController = viewController
    }
    protected fun setupBackPressDispatcher(onBack: () -> Unit) {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBack()
                }
            }
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }
    fun aVoidDoubleClick(): Boolean {
        if (SystemClock.elapsedRealtime() - lastClickTime < 500) {
            return true
        }
        lastClickTime = SystemClock.elapsedRealtime()
        return false
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (inputMethodManager.isAcceptingText) {
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken,
                0
            )
        }
    }
    fun setData(data: HashMap<String?, Any>?) {
        if (data == null || data.isEmpty()) {
            arguments = Bundle()
            return
        }
        val bundle = Bundle()
        for ((key, value) in data) {
            if (value is String) {
                bundle.putString(key, value as String)
            } else if (value is Double) {
                bundle.putDouble(key, (value as Double))
            } else if (value is Int) {
                bundle.putInt(key, value as Int)
            } else if (value is Float) {
                bundle.putFloat(key, (value as Float))
            } else if (value is Boolean) {
                bundle.putBoolean(key, (value as Boolean))
            } else if (value is Parcelable) {
                bundle.putParcelable(key, (value as Parcelable))
            } else if (value is Array<*> && value.isArrayOf<String>()) {
                bundle.putStringArray(key, value as Array<String?>)
            } else if (value is ArrayList<*>) {
                if ((value as ArrayList<*>).size > 0 && (value as ArrayList<*>)[0] is String) {
                    bundle.putStringArrayList(key, value as ArrayList<String?>)
                } else if ((value as ArrayList<*>).size > 0 && (value as ArrayList<*>)[0] is Parcelable) {
                    bundle.putParcelableArrayList(key, value as ArrayList<out Parcelable?>)
                }
            } else if (value is Serializable) {
                bundle.putSerializable(key, value as Serializable)
            }
        }
        arguments = bundle
    }
    protected fun handleListResponse(response: ListResponse<*>) {
        when (response.type) {
            Define.ResponseStatus.LOADING -> DialogUtil.getInstance(context).show()
            Define.ResponseStatus.SUCCESS -> {
                getListResponse(response.data)
                DialogUtil.getInstance(context).hidden()
            }

            Define.ResponseStatus.ERROR -> {
                handleNetworkError(response.error, true)
                DialogUtil.getInstance(context).hidden()
            }
        }
    }

    protected fun handleLoadMoreResponse(
        response: ListResponse<*>,
        isRefresh: Boolean,
        canLoadmore: Boolean
    ) {
        when (response.type) {
            Define.ResponseStatus.LOADING -> DialogUtil.getInstance(context).show()
            Define.ResponseStatus.SUCCESS -> {
                getListResponse(response.data, isRefresh, canLoadmore)
                DialogUtil.getInstance(context).hidden()
            }

            Define.ResponseStatus.ERROR -> {
                handleNetworkError(response.error, true)
                DialogUtil.getInstance(context).hidden()
            }
        }
    }

    protected fun <U> handleObjectResponse(response: ObjectResponse<U>) {
        when (response.status) {
            Define.ResponseStatus.LOADING -> DialogUtil.getInstance(context).show()
            Define.ResponseStatus.SUCCESS -> {
                getObjectResponse(response.data)
                DialogUtil.getInstance(context).hidden()
            }

            Define.ResponseStatus.ERROR -> {
                handleNetworkError(response.error, true)
                DialogUtil.getInstance(context).hidden()
            }
        }
    }

    protected fun getListResponse(data: List<*>?) {
    }

    protected fun getListResponse(data: List<*>?, isRefresh: Boolean, canLoadmore: Boolean) {
    }

    protected fun <U> getObjectResponse(data: U) {
    }

    protected fun handleNetworkError(throwable: Throwable?, isShowDialog: Boolean) {
        if (activity != null && activity is BaseActivity<*>) {
            throwable?.let { (activity as BaseActivity<*>).handleNetworkError(it, isShowDialog) }
        }
    }

    protected fun avoidDuplicateClick(): Boolean {
        if (activity != null && activity is BaseActivity<*>) {
            return (activity as BaseActivity<*>).avoidDuplicateClick()
        }

        return false
    }

    abstract fun backFromAddFragment()

    abstract fun backPressed(): Boolean

    override fun onStop() {
        super.onStop()
    }
}