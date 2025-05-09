package com.base.moviebooking.ui.account

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.base.moviebooking.DataLocalManager
import com.base.moviebooking.base.BaseFragment
import com.base.moviebooking.databinding.TaikhoanFragmentBinding
import com.base.moviebooking.ui.giaodich.GiaoDichFragment
import com.base.moviebooking.ui.sign_in.SignInFragment
import com.base.moviebooking.ui.sign_up.SignUpFragment
import com.base.moviebooking.ui.user_info.UserInfoFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AccountFragment : BaseFragment<TaikhoanFragmentBinding>() {

    private val TAG = "fat"
    private lateinit var mViewModel: AccountViewModel
    override fun getViewBinding(): TaikhoanFragmentBinding {
        return TaikhoanFragmentBinding.inflate(layoutInflater)
    }

    override fun initView() {
        mViewModel = ViewModelProvider(this).get<AccountViewModel>(
            AccountViewModel::class.java
        )
        mViewModel.getInfo()
        mViewModel.dataUser.observe(viewLifecycleOwner) { accountList ->
            if (!accountList.isNullOrEmpty()) {
                val account = accountList[0]
                Log.d("mmm", "dataUser: ${account.fullName}")
                binding.tvtName.text = account.fullName
                Log.d(TAG, "name: ${account.fullName}")
            }
        }
    }

    override fun initData() {
        setupClickListeners()
    }

    override fun backFromAddFragment() {

    }

    override fun backPressed(): Boolean {
        return true
    }

    private fun setupClickListeners() {
        binding.btnDangKy.setOnClickListener {
            mViewController?.addFragment(SignUpFragment::class.java, null)
        }

        binding.btnDangNhap.setOnClickListener {
            mViewController?.addFragment(SignInFragment::class.java, null)
        }

        binding.dangxuat.setOnClickListener {
            binding.lnUser.visibility = View.GONE
            binding.lnNoUser.visibility = View.VISIBLE
            binding.btnDangNhap.visibility = View.VISIBLE
            binding.btnDangKy.visibility = View.VISIBLE
            DataLocalManager.setBooleanValue(false)
            Toast.makeText(requireContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show()
        }

        binding.lnThongtin.setOnClickListener {
            mViewController?.addFragment(UserInfoFragment::class.java, null)
        }

        binding.giaodich.setOnClickListener {
            mViewController?.addFragment(GiaoDichFragment::class.java, null)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume account")

        if (DataLocalManager.getBooleanValue()) {
            binding.lnUser.visibility = View.VISIBLE
            binding.lnNoUser.visibility = View.GONE
            binding.btnDangNhap.visibility = View.GONE
            binding.btnDangKy.visibility = View.GONE
            mViewModel.getInfo()
        } else {
            Log.d("mmm", "accessTokenFail")
            binding.lnUser.visibility = View.GONE
            binding.lnNoUser.visibility = View.VISIBLE
            binding.btnDangNhap.visibility = View.VISIBLE
            binding.btnDangKy.visibility = View.VISIBLE
        }
    }
}
