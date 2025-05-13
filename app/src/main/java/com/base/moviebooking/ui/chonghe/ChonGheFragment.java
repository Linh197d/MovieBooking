package com.base.moviebooking.ui.chonghe;

import static com.base.moviebooking.adapter.HomeAdapter.parseBase64;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.base.moviebooking.R;
import com.base.moviebooking.adapter.SeatAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.ChongheFragmentBinding;
import com.base.moviebooking.entity.Amount;
import com.base.moviebooking.entity.Chair;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.PriceSeat;
import com.base.moviebooking.entity.Product;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.entity.Seat;
import com.base.moviebooking.listener.OnChooseSeat;
import com.base.moviebooking.ui.thanhtoan.ThanhToanFragment;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChonGheFragment extends BaseFragment<ChongheFragmentBinding> {
    public final String TAG = "fat";
    private Movie movie;
    private String nameCinema;
    private Schedule schedule;
    private PriceSeat priceSeat = new PriceSeat();
    private String gheThuong, gheVip;
    private int slgheThuong = 0;
    private ArrayList<Integer> listIdGhe = new ArrayList<>();
    private int slGheChoose = 0;
    private int slgheVip = 0;
    private ChonGheViewModel mViewModel;
    private SeatAdapter seatAdapter;
    private List<Seat> seatList = new ArrayList<>();
    private List<Chair> chairList = new ArrayList<>();

    public static String removeDot(String str) {
        return str.replace(",", "");
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        mViewController.backFromAddFragment(null);
        return false;
    }

    private String truncateText(String text, int maxCharacters) {
        if (text.length() <= maxCharacters) {
            return text;
        } else {
            int colonIndex = text.indexOf(":");
            if (colonIndex != -1 && colonIndex < maxCharacters) {
                return text.substring(0, colonIndex + 1) + "...";
            } else {
                String truncatedText = text.substring(0, maxCharacters);
                int lastSpaceIndex = truncatedText.lastIndexOf(" ");
                if (lastSpaceIndex != -1) {
                    truncatedText = truncatedText.substring(0, lastSpaceIndex);
                }
                return truncatedText + "...";
            }
        }
    }

    @Override
    public void initView() {
        mViewModel = new ViewModelProvider(this).get(ChonGheViewModel.class);
        Bundle bundle = getArguments();
        if (bundle != null && !bundle.isEmpty()) {
            schedule = (Schedule) bundle.getSerializable("schedule");
            movie = (Movie) bundle.getSerializable("movie");
            nameCinema = bundle.getString("cinema");
            mViewModel.getChaired(schedule.getId());

            Calendar calendar = Calendar.getInstance(); // Lấy ngày hiện tại
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của chuỗi ngày
            try {
                Date premiereDate = dateFormat.parse(schedule.getPremiere()); // Chuyển đổi chuỗi thành đối tượng Date
                calendar.setTime(premiereDate); // Thiết lập ngày đã chuyển đổi cho calendar
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            String format = movie.getFormat();
            if (format.equals("2D")) {
                if (dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY) {
                    mViewModel.getAmount(1, 1, 1);
                } else if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                    mViewModel.getAmount(1, 2, 1);
                }
            } else {
                if (dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY) {
                    mViewModel.getAmount(2, 1, 3);
                } else if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                    mViewModel.getAmount(2, 2, 3);
                }
            }

            // Xử lý dữ liệu trong bundle
        }
        mViewModel.getProduct();
        mViewModel.dataProduct.observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                //data products
                String base64Image = products.get(0).getImage();
//            Log.d("mmm","base64"+base64Image,null);
                byte[] imageBytes = Base64.decode(parseBase64(base64Image), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                String base64Image1 = products.get(1).getImage();
//            Log.d("mmm","base64"+base64Image,null);
                byte[] imageBytes1 = Base64.decode(parseBase64(base64Image), Base64.DEFAULT);
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                String base64Image2 = products.get(2).getImage();
//            Log.d("mmm","base64"+base64Image,null);
                byte[] imageBytes2 = Base64.decode(parseBase64(base64Image), Base64.DEFAULT);
                Bitmap bitmap2 = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                binding.tvtBong.setText(products.get(0).getName());
                binding.tvtNuoc.setText(products.get(1).getName());
            }
        });
        //set duwx lieu movie
        binding.nameMovie.setText(truncateText(movie.getName(), 15));
        binding.format.setText(movie.getFormat() + " PHỤ ĐỀ");
        binding.tvtAgeLimit.setText("C" + movie.getAgeLimit());
        TextView t = getActivity().findViewById(R.id.tvt_headerphim);
        t.setText("Đặt ghế");
        mViewModel.chaired.observe(getViewLifecycleOwner(), new Observer<List<Chair>>() {
            @Override
            public void onChanged(List<Chair> list) {
//                Log.d("fat","chon ghe: "+list.get(0).getChair_id());
                chairList.addAll(list);
            }
        });
        mViewModel.dataAmount.observe(getViewLifecycleOwner(), new Observer<List<Amount>>() {
            @Override
            public void onChanged(List<Amount> list) {
                binding.amountThuong.setText(formatNumber(list.get(0).getAmount()) + "VNĐ");
                binding.amountVip.setText(formatNumber(list.get(0).getAmount_vip()) + "VNĐ");
            }
        });
        binding.rcvSeat.setLayoutManager(new GridLayoutManager(getContext(), 11));
        seatAdapter = new SeatAdapter(getContext(), false, getContext(), new OnChooseSeat() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSeat(Seat seat) {
                if (seat.getChoosed()) {
                    //nếu chưa chọn ghế nào
                    listIdGhe.add(seat.getId());
                    slGheChoose++;
                    if (binding.tvtTongtien.getText().toString().equals("")) {//|| binding.tvtTongtien.getText().toString()==null
                        if (seat.getStatus() == 1) {
                            binding.tvtTongtien.setText(binding.amountVip.getText() + "");
                            slgheVip++;
                            gheVip += seat.getxPosition() + seat.getyPosition() + " ";

                        } else {
                            binding.tvtTongtien.setText(binding.amountThuong.getText() + "");
                            slgheThuong++;
                            gheThuong += seat.getxPosition() + seat.getyPosition() + " ";
                        }

                        binding.tvtListghe.setText(seat.getxPosition() + seat.getyPosition() + " ");

                    } else {// nếu tổng tiền khác null, ý là chọn 1 thằng trc r chọn tiếp
                        binding.tvtListghe.setText(binding.tvtListghe.getText() + seat.getxPosition() + seat.getyPosition() + " ");
                        Log.d(TAG, "tvt" + binding.tvtTongtien.getText().toString());
                        int a = deleteVND(binding.tvtTongtien.getText().toString());
                        int i;
                        if (seat.getStatus() == 1) {
                            i = a + deleteVND(binding.amountVip.getText().toString());
                            slgheVip++;
                            gheVip += seat.getxPosition() + seat.getyPosition() + " ";
                        } else {
                            i = a + deleteVND(binding.amountThuong.getText().toString());
                            slgheThuong++;
                            gheThuong += seat.getxPosition() + seat.getyPosition() + " ";
                        }
//                        Log.d(TAG, "a" + a);
//                        Log.d(TAG, "i" + i);
                        binding.tvtTongtien.setText("" + formatNumber(i) + "VNĐ");

                    }


                    //nếu có ghế được chọn r thì có thể click để sang màn Thanh toán
                    binding.btnDatve.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //gửi dữ liệu sang màn thanh toán
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("movie", movie);
                            hashMap.put("schedule", schedule);
                            priceSeat.setListIdGhe(listIdGhe);
                            priceSeat.setGheThuong(gheThuong);
                            priceSeat.setGheVip(gheVip);
                            priceSeat.setSlgheThuong(slgheThuong);
                            priceSeat.setSlgheVip(slgheVip);
                            priceSeat.setTienGheThuong(slgheThuong * deleteVND(binding.amountThuong.getText().toString()) + "");
                            priceSeat.setTienGheVip(slgheVip * deleteVND(binding.amountVip.getText().toString()) + "");
                            priceSeat.setTongTien(binding.tvtTongtien.getText().toString());
                            if (binding.cbBong.isChecked()) {
                                priceSeat.setSlBong(binding.slBong.getText().toString());
                                priceSeat.setTienBong(binding.giaBong.getText().toString());
                            }
                            if (binding.cbNuoc.isChecked()) {
                                priceSeat.setTienNuoc(binding.giaNuoc.getText().toString());
                                priceSeat.setSlNuoc(binding.slNuoc.getText().toString());
                            }
                            if (binding.cbCombo.isChecked()) {
                                priceSeat.setTienCombo(binding.giaCombo.getText().toString());
                                priceSeat.setSlCombo(binding.slCombo.getText().toString());
                            }
                            hashMap.put("priceSeat", priceSeat);
                            hashMap.put("nameCinema", nameCinema);
                            mViewController.addFragment(ThanhToanFragment.class, hashMap);
                        }
                    });
                } else {// nếu ghế đã bấm r mà click lần 2 thì sẽ xoá khỏi tổng tiền và list ghế
                    slGheChoose--;
                    listIdGhe.remove(slGheChoose);
                    String s1 = binding.tvtListghe.getText().toString();
                    String s2 = seat.getxPosition() + seat.getyPosition();
                    String s1WithoutS2 = s1.replaceAll(s2 + "\\b", "");//"\\b" +
                    binding.tvtListghe.setText(s1WithoutS2);
                    String trimmedInput = s1WithoutS2.trim();


                    // Kiểm tra độ dài chuỗi sau khi đã loại bỏ khoảng trắng
                    if (trimmedInput.length() == 0) {
                        binding.tvtListghe.setText(""); // Gán giá trị rỗng cho chuỗi
                    } else {
                        binding.tvtListghe.setText(trimmedInput + " ");
                    }
                    int a = deleteVND(binding.tvtTongtien.getText().toString());
                    int i;
                    if (seat.getStatus() == 1) {
                        i = a - deleteVND(binding.amountVip.getText().toString());
                        slgheVip--;
                        gheVip = gheVip.replaceAll(s2 + "\\b", "");
                        ;
                    } else {
                        i = a - deleteVND(binding.amountThuong.getText().toString());
                        slgheThuong--;
                        gheThuong = gheThuong.replaceAll(s2 + "\\b", "");
                        ;
                    }
                    binding.tvtTongtien.setText("" + formatNumber(i) + "VNĐ");
                }

            }
        }, chairList);
        if (slGheChoose == 0) {
            binding.btnDatve.setEnabled(false);
        } else {
            binding.btnDatve.setEnabled(true);

        }
        mViewModel.getChair();
        binding.rcvSeat.setAdapter(seatAdapter);
        mViewModel.chair.observe(getViewLifecycleOwner(), new Observer<List<Seat>>() {
            @Override
            public void onChanged(List<Seat> list) {
                seatAdapter.addModels(list, false);
            }
        });

        //check xem đã chọn ghế nào chưa. chọn rồi mới được sang màn thanhtoán
        if (binding.tvtTongtien.getText().toString().equals("0VNĐ")) {
            binding.btnDatve.setEnabled(false);
            Toast.makeText(getContext(), "Bạn chưa chọn ghế nào cả !", Toast.LENGTH_SHORT).show();
        } else {
            binding.btnDatve.setEnabled(true);
        }

    }

    public int deleteVND(String s) {
        if (!s.equals("")) {
            s = removeDot(s);
            s = s.replaceAll("[^0-9]+", "");
            return Integer.parseInt(s);
        }
        return 0;
    }

    @Override
    public void initData() {
        getActivity().findViewById(R.id.img_headerphim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.backFromAddFragment(null);
            }
        });

        tinhTienDo();


    }

    @SuppressLint("SetTextI18n")
    private void tinhTienDo() {
        //Check Combo
        binding.cbCombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.congCombo.setEnabled(binding.cbCombo.isChecked());
                if (binding.slCombo.getText().toString().equals("1"))
                    binding.truCombo.setEnabled(false);
                else
                    binding.truCombo.setEnabled(binding.cbCombo.isChecked());

                if (binding.cbCombo.isChecked())
                    binding.tvtTongtien.setText(formatNumber(deleteVND(binding.tvtTongtien.getText().toString()) + deleteVND(binding.giaCombo.getText().toString())) + "VNĐ");
                else
                    binding.tvtTongtien.setText(formatNumber(deleteVND(binding.tvtTongtien.getText().toString()) - deleteVND(binding.giaCombo.getText().toString())) + "VNĐ");

            }
        });
        //Check Bong
        binding.cbBong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.congBong.setEnabled(binding.cbBong.isChecked());
                if (binding.slBong.getText().toString().equals("1"))
                    binding.truBong.setEnabled(false);
                else
                    binding.truBong.setEnabled(binding.cbBong.isChecked());
                if (binding.cbBong.isChecked())
                    binding.tvtTongtien.setText(formatNumber(deleteVND(binding.tvtTongtien.getText().toString()) + deleteVND(binding.giaBong.getText().toString())) + "VNĐ");
                else
                    binding.tvtTongtien.setText(formatNumber(deleteVND(binding.tvtTongtien.getText().toString()) - deleteVND(binding.giaBong.getText().toString())) + "VNĐ");

            }
        });
        binding.cbNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.congNuoc.setEnabled(binding.cbNuoc.isChecked());
                if (binding.slNuoc.getText().toString().equals("1"))
                    binding.truNuoc.setEnabled(false);
                else
                    binding.truNuoc.setEnabled(binding.cbNuoc.isChecked());
                if (binding.cbNuoc.isChecked())
                    binding.tvtTongtien.setText(formatNumber(deleteVND(binding.tvtTongtien.getText().toString()) + deleteVND(binding.giaNuoc.getText().toString())) + "VNĐ");
                else
                    binding.tvtTongtien.setText(formatNumber(deleteVND(binding.tvtTongtien.getText().toString()) - deleteVND(binding.giaNuoc.getText().toString())) + "VNĐ");

            }
        });
        // cong so luong
        binding.congCombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(binding.slCombo.getText().toString());
                if (i == 1) {
                    binding.truCombo.setEnabled(true);
                }
                binding.slCombo.setText(i + 1 + "");
                binding.giaCombo.setText(formatNumber((i + 1) * 70000) + "VNĐ");
                binding.tvtTongtien.setText(formatNumber(deleteVND(binding.tvtTongtien.getText().toString()) + 70000) + "VNĐ");
            }
        });

        binding.congBong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(binding.slBong.getText().toString());
                if (i == 1) {
                    binding.truBong.setEnabled(true);
                }
                binding.slBong.setText(i + 1 + "");
                binding.giaBong.setText(formatNumber((i + 1) * 50000) + "VNĐ");
                binding.tvtTongtien.setText(formatNumber(deleteVND(binding.tvtTongtien.getText().toString()) + 50000) + "VNĐ");

            }
        });
        binding.congNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(binding.slNuoc.getText().toString());
                if (i == 1) {
                    binding.truNuoc.setEnabled(true);
                }
                binding.slNuoc.setText(i + 1 + "");
                binding.giaNuoc.setText(formatNumber((i + 1) * 30000) + "VNĐ");
                binding.tvtTongtien.setText(formatNumber(deleteVND(binding.tvtTongtien.getText().toString()) + 30000) + "VNĐ");

            }
        });
        // tru so luong
        binding.truCombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(binding.slCombo.getText().toString());
                if (i == 2) {
                    binding.truCombo.setEnabled(false);
                }
                binding.slCombo.setText(i - 1 + "");
                binding.giaCombo.setText(formatNumber(deleteVND(binding.giaCombo.getText().toString()) - 70000) + "VNĐ");
                binding.tvtTongtien.setText(formatNumber(deleteVND(binding.tvtTongtien.getText().toString()) - 70000) + "VNĐ");
            }
        });
        binding.truBong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(binding.slBong.getText().toString());
                if (i == 2) {
                    binding.truBong.setEnabled(false);
                }
                binding.slBong.setText(i - 1 + "");
                binding.giaBong.setText(formatNumber(deleteVND(binding.giaBong.getText().toString()) - 50000) + "VNĐ");
                binding.tvtTongtien.setText(formatNumber(deleteVND(binding.tvtTongtien.getText().toString()) - 50000) + "VNĐ");

            }
        });
        binding.truNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(binding.slNuoc.getText().toString());
                if (i == 2) {
                    binding.truNuoc.setEnabled(false);
                }
                binding.slNuoc.setText(i - 1 + "");
                binding.giaNuoc.setText(formatNumber(deleteVND(binding.giaNuoc.getText().toString()) - 30000) + "VNĐ");
                binding.tvtTongtien.setText(formatNumber(deleteVND(binding.tvtTongtien.getText().toString()) - 30000) + "VNĐ");

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public String formatNumber(int number) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            return decimalFormat.format(number);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
            return null;
        }
    }

    @NonNull
    @Override
    public ChongheFragmentBinding getViewBinding() {
        return ChongheFragmentBinding.inflate(getLayoutInflater());
    }
}
