package com.base.moviebooking.ui.thanhtoan;

import static com.base.moviebooking.adapter.HomeAdapter.parseBase64;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.ThanhtoanFragmentBinding;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.PriceSeat;
import com.base.moviebooking.entity.ProductThanhToan;
import com.base.moviebooking.entity.RegisterResponse;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.entity.ThanhToan;
import com.base.moviebooking.ui.thongtin_Thanhtoan.ThongTinThanhToanFragment;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ThanhToanFragment extends BaseFragment<ThanhtoanFragmentBinding> {

    final Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    private ThanhToanViewModel mViewModel;
    private Schedule schedule;
    private Movie movie;
    private PriceSeat priceSeat;
    private Integer amount;
    private ThanhToan thanhToan;
    private ArrayList<ProductThanhToan> listProduct = new ArrayList<>();
    private ArrayList<Integer> arrayListGhe = new ArrayList<>();

    public static String removeDot(String str) {
        return str.replace(".", "");
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        mViewController.backFromAddFragment(null);
        return false;
    }

    @Override
    public void initView() {

        mViewModel = new ViewModelProvider(this).get(ThanhToanViewModel.class);
        Bundle bundle = getArguments();
        if (bundle != null && !bundle.isEmpty()) {
//            Log.d("fat", "bundle chair okay");
            schedule = (Schedule) bundle.getSerializable("schedule");
            movie = (Movie) bundle.getSerializable("movie");
            priceSeat = (PriceSeat) bundle.getSerializable("priceSeat");
            arrayListGhe = priceSeat.getListIdGhe();
            String nameCinema = bundle.getString("nameCinema");
            String base64Image = movie.getImage();
//            Log.d("mmm","base64"+base64Image,null);
            byte[] imageBytes = Base64.decode(parseBase64(base64Image), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            binding.imgPhim.setImageBitmap(bitmap);
            if (priceSeat.getGheThuong() == null || priceSeat.getGheThuong().toString().equals("") || priceSeat.getSlgheThuong() == 0) {
                binding.lnGheThuong.setVisibility(View.GONE);
            } else {
                binding.tvtGheThuong.setText(priceSeat.getSlgheThuong() + "x Ghế Thường - " + priceSeat.getGheThuong().toString().replace("null", ""));
                binding.tvtGiagheThuong.setText(formatNumber(priceSeat.getTienGheThuong().toString()) + "VNĐ");

            }
            if (priceSeat.getGheVip() == null || priceSeat.getGheVip().toString().equals("") || priceSeat.getSlgheVip() == 0) {
                binding.lnGheVip.setVisibility(View.GONE);
            } else {
                binding.tvtGheVip.setText(priceSeat.getSlgheVip() + "x Ghế Vip - " + priceSeat.getGheVip().toString().replace("null", ""));
                binding.tvtGiagheVip.setText(formatNumber(priceSeat.getTienGheVip().toString()) + "VNĐ");
            }
            if (priceSeat.getSlBong() == null) {
                binding.lnBong.setVisibility(View.GONE);
            } else {
                listProduct.add(new ProductThanhToan(2, Integer.parseInt(priceSeat.getSlBong().toString())));
                binding.tvtBong.setText(priceSeat.getSlBong() + "x Bỏng Ngô");
                binding.tvtGiabong.setText(priceSeat.getTienBong().toString());
            }
            if (priceSeat.getSlNuoc() == null) {
                binding.lnNuoc.setVisibility(View.GONE);
            } else {
                listProduct.add(new ProductThanhToan(3, Integer.parseInt(priceSeat.getSlNuoc().toString())));
                binding.tvtNuoc.setText(priceSeat.getSlNuoc() + "x Nước");
                binding.tvtGiaNuoc.setText(priceSeat.getTienNuoc().toString());
            }
            if (priceSeat.getSlCombo() == null) {
                binding.lnCombo.setVisibility(View.GONE);
            } else {
                listProduct.add(new ProductThanhToan(4, Integer.parseInt(priceSeat.getSlCombo().toString())));
                binding.tvtCombo.setText(priceSeat.getSlCombo() + "x Combo");
                binding.tvtGiaCombo.setText(priceSeat.getTienCombo().toString());
            }
            binding.tvtTenphim.setText(movie.getName());
            binding.tvtRap.setText(nameCinema + " - Room " + schedule.getRoom_name().toString());

//            amount = Integer.valueOf(priceSeat.getTongTien());
            //tong tien
            binding.tvtTongtien.setText(priceSeat.getTongTien().toString());
            // Xử lý dữ liệu trong bundle
        }
    }

    @Override
    public void initData() {
        mViewModel.dataThanhToan.observe(getViewLifecycleOwner(), new Observer<RegisterResponse>() {
            @Override
            public void onChanged(RegisterResponse response) {
                if (response.isSuccess()) {
                    binding.webviewTT.loadUrl(response.getData().getUrl());
                    binding.webviewTT.setVisibility(View.VISIBLE);
//                    Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
//                    mViewController.replaceFragment(ThongTinThanhToanFragment.class, null);
//                    getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getContext(), "Thanh toán thất bại", Toast.LENGTH_SHORT).show();

                }
            }
        });
        binding.webviewTT.setWebViewClient(new WebViewClient() {

            @Override

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.equals("http://192.168.1.26:8080/stripe/payment/success")) {

                    // Xử lý ở đây khi thanh toán thành công
                    Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                    mViewController.replaceFragment(ThongTinThanhToanFragment.class, null);
                    getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                    return true;

                }


                return false; // Cho phép WebView xử lý URL

            }

        });
        binding.backTt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.backFromAddFragment(null);
            }
        });
        binding.btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               if(binding.edtMathe.getText().toString().equals("")||binding.edtNgay.getText().toString().equals("")
//               ||binding.edtSothe.getText().toString().equals("")||binding.edtTen.getText().toString().equals("")){
//                   Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
//               }else if(binding.spinner.getSelectedItemPosition()==0){
//                   Toast.makeText(getContext(), "Vui lòng chọn ngân hàng", Toast.LENGTH_SHORT).show();
//
//               }else {
                binding.webviewTT.getSettings().setJavaScriptEnabled(true);
                thanhToan = new ThanhToan(schedule.getId() + "", arrayListGhe, deleteVND(priceSeat.getTongTien().toString()), listProduct);
                mViewModel.getThanhToan(thanhToan);
//               }
            }
        });


//        binding.edtNgay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        month = month + 1;
//                        String date = dayOfMonth + "/" + month + "/" + year;
//                        binding.edtNgay.setText(date);
//                    }
//                }, year, month, day);
//                datePickerDialog.show();
//            }
//        });
        String s = schedule.getPremiere().toString().substring(11, 16);
        s = changeTimeZone(s);
        String s1 = schedule.getPremiere().toString().substring(0, 10);
        SimpleDateFormat stringformat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = stringformat.parse(s1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binding.tvtTime.setText(s + " - " + dateFormat.format(date));
        //set data spinner
//        List<String> itemSpin = new ArrayList<>();
//        itemSpin.add("Chọn ngân hàng");
//        itemSpin.add("Vietcombank");
//        itemSpin.add("VPBank");
//        itemSpin.add("MBBank");
//        itemSpin.add("TPBank");
//        itemSpin.add("Techcombank");
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, itemSpin);
////
////// Specify the layout to use for the dropdown list
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.spinner.setAdapter(adapter2);
    }

    public int deleteVND(String s) {
        if (!s.equals("")) {
            s = removeDot(s);
            s = s.replaceAll("[^0-9]+", "");
            return Integer.parseInt(s);
        }
        return 0;
    }

    public String changeTimeZone(String s) {
        String s1 = s.substring(0, 2);
        String s2 = s.substring(3, 5);
        int i = Integer.parseInt(s1) + 7;
        if (i >= 24) {
            i = i - 24;
        }
        s = i + ":" + s2;
        return s;
    }

    public String formatNumber(String numberString) {
        try {
            int number = Integer.parseInt(numberString);
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            return decimalFormat.format(number);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
            return null;
        }
    }

    @NonNull
    @Override
    public ThanhtoanFragmentBinding getViewBinding() {
        return ThanhtoanFragmentBinding.inflate(getLayoutInflater());
    }
}
