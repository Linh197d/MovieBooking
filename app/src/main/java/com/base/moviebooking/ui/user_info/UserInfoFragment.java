package com.base.moviebooking.ui.user_info;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.UserInfoFragmentBinding;
import com.base.moviebooking.entity.Account;
import com.base.moviebooking.entity.RegisterResponse;
import com.base.moviebooking.entity.UserUpdate;
import com.base.moviebooking.ui.change_pass.ChangePassFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class UserInfoFragment extends BaseFragment<UserInfoFragmentBinding> {

    private UserInfoViewModel mViewModel;
    private UserUpdate updateUser;
    final Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    Uri selectedImageUri;

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
        mViewModel = new ViewModelProvider(this).get(UserInfoViewModel.class);
        mViewModel.getInfo();
        mViewModel.dataUser.observe(getViewLifecycleOwner(), new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                Account account = accounts.get(0);
                binding.nameUser.setText(account.getFullName().toString());
//                binding.phoneuser.setText(account.get().toString());
                String s = account.getDateOfBirth().toString().substring(0,10);
                SimpleDateFormat stringformat = new SimpleDateFormat("yyyy-MM-dd");

                Date date = null;
                try {
                    date = stringformat.parse(s);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                binding.birthdayUser.setText(dateFormat.format(date));
                binding.gmailUser.setText(account.getEmail().toString());
                binding.address.setText(account.getAddress().toString());
                if(account.getGender()== 1){
                    binding.nam.setChecked(true);
                }else {
                    binding.nu.setChecked(true);

                }
            }
        });
        binding.btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.addFragment(ChangePassFragment.class, null);
            }
        });
        binding.backTt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                mViewController.backFromAddFragment(null);
            }
        });
        mViewModel.update.observe(getViewLifecycleOwner(), new Observer<RegisterResponse>() {
            @Override
            public void onChanged(RegisterResponse response) {
                if(response.isSuccess()){
                    mViewController.backFromAddFragment(null);
                    getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void initData() {
        binding.birthdayUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        binding.birthdayUser.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        binding.btnCapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat stringformat = new SimpleDateFormat("dd/MM/yyyy");

                Date date = null;
                try {
                    date = stringformat.parse(binding.birthdayUser.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                int gender =0;
                if(binding.nam.isChecked()){
                    gender=1;

                }
                if(binding.nu.isChecked()){
                    gender=0;

                }
                updateUser = new UserUpdate(binding.nameUser.getText().toString(),binding.address.getText().toString(), dateFormat.format(date),gender);
                mViewModel.updateUser(updateUser);

            }
        });
        binding.pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasImagePermission()) {
                    openImagePicker();
                } else {
                    requestImagePermission();
                }
            }
        });
    }
    private static final int REQUEST_IMAGE_PERMISSION = 1001;

    private boolean hasImagePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_MEDIA_IMAGES)
                    == PackageManager.PERMISSION_GRANTED;
        } else {
            return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestImagePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_IMAGE_PERMISSION);
        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_IMAGE_PERMISSION);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_IMAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResult.launch(intent);
    }
    private final ActivityResultLauncher<Intent> mActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()== RESULT_OK){
                        Intent intent = result.getData();
                         selectedImageUri = intent.getData();// gallery
                        binding.imgUser.setImageURI(selectedImageUri);
                    }
                }
            });


    @NonNull
    @Override
    public UserInfoFragmentBinding getViewBinding() {
        return UserInfoFragmentBinding.inflate(getLayoutInflater());
    }
}
