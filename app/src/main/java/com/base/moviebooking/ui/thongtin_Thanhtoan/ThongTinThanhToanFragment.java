package com.base.moviebooking.ui.thongtin_Thanhtoan;

import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.ThongtinThanhtoanFragmentBinding;
import com.base.moviebooking.entity.Account;
import com.base.moviebooking.entity.ThongTinThanhToan;
import com.base.moviebooking.ui.home.HomeFragment;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ThongTinThanhToanFragment extends BaseFragment<ThongtinThanhtoanFragmentBinding> {

    private ThongTinThanhToanViewModel mViewModel;
    ThongTinThanhToan  thanhToan  ;
    private String code;
    private String gmail="thieuhaitam@gmail.com";
    private Bitmap bitmapImage;
    @Override
    protected int getLayoutId() {
        return R.layout.thongtin_thanhtoan_fragment;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {
        getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(ThongTinThanhToanViewModel.class);
        mViewModel.getThongTinThanhToan();
        mViewModel.getInfo();
        mViewModel.dataUser.observe(getViewLifecycleOwner(), new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                gmail = accounts.get(0).getEmail();
                Log.d("fat","gmail:"+gmail);
            }
        });
        mViewModel.dataThanhToan.observe(getViewLifecycleOwner(), new Observer<List<ThongTinThanhToan>>() {
            @Override
            public void onChanged(List<ThongTinThanhToan> list) {
                if(list!=null){
                    thanhToan = list.get(0);
                    code = thanhToan.getCode();
                    // send gmail
                    if(gmail!=null){
                        sendGmail(gmail,code);
                    }
                    String text = thanhToan.getCode().trim();
                    MultiFormatWriter writer = new MultiFormatWriter();
                    try
                    {
                        BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE,600,600);
                        BarcodeEncoder encoder = new BarcodeEncoder();
                        bitmapImage = encoder.createBitmap(matrix);
                        binding.imgQr.setImageBitmap(bitmapImage);

                    } catch (WriterException e)
                    {
                        e.printStackTrace();
                    }
                }else
                    Log.e("listThanhToan","list thông tin thanh toán null ");

            }
        });
    }

    private void sendGmail(String mail,String codeee) {
            JavaMailAPI javaMailAPI = new JavaMailAPI(getContext(), mail, "Mã đặt vé", "Mã đặt vé xem phim của bạn là:"+codeee+"\n Không chia sẻ mã này cho bất kì ai khác.");
            javaMailAPI.execute();
    }

    @Override
    public void initData() {
        binding.btnVehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.replaceFragment(HomeFragment.class,null);
            }
        });
        binding.btnLuuanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                requestPermissionGallery();
                if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                    saveToGallery(bitmapImage);
                }else {
                    askPermission();
                }
            }
        });
    }
    private void askPermission() {
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                saveToGallery(bitmapImage);
            }else {
                Toast.makeText(getActivity(),"Please provide the required permissions",Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    private void saveToGallery(Bitmap bitmap){
        File storageDir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String timeStamp = new SimpleDateFormat("yyyyMMđd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName= "IMG_"+timeStamp+".jpg";
        File imageFIle = new File(storageDir,fileName);
        try {
            FileOutputStream outputStream = new FileOutputStream(imageFIle);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            outputStream.flush();
            outputStream.close();
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(Uri.fromFile(imageFIle));
        getActivity().sendBroadcast(mediaScanIntent);
        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
     }


    @NonNull
    @Override
    public ThongtinThanhtoanFragmentBinding getViewBinding() {
        return ThongtinThanhtoanFragmentBinding.inflate(getLayoutInflater());
    }
}



