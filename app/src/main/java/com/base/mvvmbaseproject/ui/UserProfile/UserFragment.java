package com.base.mvvmbaseproject.ui.UserProfile;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.mvvmbaseproject.OnChooseBloodType;
import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.adapter.BloodTypeAdapter;
import com.base.mvvmbaseproject.base.BaseFragment;
import com.base.mvvmbaseproject.base.ObjectResponse;
import com.base.mvvmbaseproject.databinding.UserProfileBinding;
import com.base.mvvmbaseproject.dialog.DialogCamera;
import com.base.mvvmbaseproject.entity.BloodType;
import com.base.mvvmbaseproject.entity.DataUser;
import com.base.mvvmbaseproject.entity.DataUserSK;
import com.base.mvvmbaseproject.entity.LoginResponse2;
import com.base.mvvmbaseproject.entity.UpdateDataUser;
import com.base.mvvmbaseproject.entity.UpdateDataUserHealth;
import com.base.mvvmbaseproject.entity.UpdateRespone;
import com.base.mvvmbaseproject.ui.home.HomeFragment;
import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserFragment extends  BaseFragment<UserProfileBinding> {
    private UserViewModel mViewModel;
    private ArrayList<BloodType> mBlood ;
    private BloodTypeAdapter mBloodAdapter ;
    private UpdateDataUser updateDataUser;
    private UpdateDataUserHealth updateDataUserHealth;
    private Date date;
    @Override
    protected int getLayoutId() {
        return R.layout.user_profile;
    }

    @Override
    public void backFromAddFragment() {
    }

    @Override
    public boolean backPressed() {
        return true;
    }

    @Override
    public void initView() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);
        mViewModel.getUserProfile();
        mViewModel.getUserSucKhoe();
        mViewModel.userprofile.observe(getViewLifecycleOwner(), new Observer<ObjectResponse<DataUser>>() {
            @Override
            public void onChanged(ObjectResponse<DataUser> dataUserObjectResponse) {
                binding.edtName.setText(dataUserObjectResponse.getData().getName());
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                 date = null;
                try {
                    date = fmt.parse(dataUserObjectResponse.getData().getBirthday());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat fmtOut = new SimpleDateFormat("dd / MM / yyyy");
                binding.btnDate.setText(fmtOut.format(date));
                updateDataUser = new UpdateDataUser(dataUserObjectResponse.getData().getName(),dataUserObjectResponse.getData().getBirthday(),dataUserObjectResponse.getData().getAddress()
                        ,dataUserObjectResponse.getData().getGender(),dataUserObjectResponse.getData().getPhone());
                if(dataUserObjectResponse.getData().getGender() ==1 ){
                    binding.rdbtnMale.setChecked(true);
                }else {
                    binding.rdbtnFemale.setChecked(true);
                }
                binding.tvtPhone.setText(dataUserObjectResponse.getData().getPhone());
                binding.edtAdress.setText(dataUserObjectResponse.getData().getAddress());
                Glide.with(getContext())
                        .load("http://hsba-v2.beetechdev.vn:1680/storage/"+dataUserObjectResponse.getData().getAvatar())
                        .into(binding.btnCamera);
            }
        });
        mViewModel.userSK.observe(getViewLifecycleOwner(), new Observer<ObjectResponse<DataUserSK>>() {
            @Override
            public void onChanged(ObjectResponse<DataUserSK> dataUserObjectResponse) {
                updateDataUserHealth = new UpdateDataUserHealth(dataUserObjectResponse.getData().getUsers_weight(),dataUserObjectResponse.getData().getUsers_height(),dataUserObjectResponse.getData().getUsers_blood_group(),dataUserObjectResponse.getData().getUsers_judgment());
                binding.edtHeight.setText(Integer.toString(dataUserObjectResponse.getData().getUsers_height()));
                binding.edtWeight.setText(Integer.toString(dataUserObjectResponse.getData().getUsers_weight()));
                binding.tvtBloodtype.setText(dataUserObjectResponse.getData().getUsers_blood_group());
                binding.edtJudgment.setText(dataUserObjectResponse.getData().getUsers_judgment());
            }
        });
        mViewModel.update_user.observe(getViewLifecycleOwner(), new Observer<UpdateRespone>() {
            @Override
            public void onChanged(UpdateRespone loginResponse2) {
                Toast.makeText(getContext(), "Success Update User Info", Toast.LENGTH_SHORT).show();
            }
        });
        mViewModel.update_user_health.observe(getViewLifecycleOwner(), new Observer<LoginResponse2>() {
            @Override
            public void onChanged(LoginResponse2 loginResponse2) {
                Toast.makeText(getContext(), "Success Update User Health", Toast.LENGTH_SHORT).show();
            }
        });
        mBlood = new ArrayList<>();
        createBloodList();
        mBloodAdapter = new BloodTypeAdapter(getContext(), mBlood, new OnChooseBloodType() {
            @Override
            public void onChoose(BloodType bloodType) {
                binding.tvtBloodtype.setText(bloodType.getBloodtype());
                binding.layoutChooseBloodtype.setVisibility(View.GONE);
                binding.arrowDownRcvBlood.setImageResource(R.drawable.back);
            }
        });
        binding.layoutChooseBloodtype.setAdapter(mBloodAdapter);
        binding.layoutChooseBloodtype.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void createBloodList() {
        mBlood.add(new BloodType("A",false));
        mBlood.add(new BloodType("B",false));
        mBlood.add(new BloodType("O",false));
        mBlood.add(new BloodType("AB",false));
    }

    @Override
    public void initData() {
        binding.backUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.backFromAddFragment(null);
            }
        });
        binding.btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        binding.btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogDatePicker();
            }
        });
        binding.bottompicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("mmm","clicked");
                if(binding.layoutChooseBloodtype.getVisibility()== View.VISIBLE){
                    binding.layoutChooseBloodtype.setVisibility(View.GONE);
                    binding.arrowDownRcvBlood.setImageResource(R.drawable.back);

                }else {
                    binding.layoutChooseBloodtype.setVisibility(View.VISIBLE);
                    binding.arrowDownRcvBlood.setImageResource(R.drawable.down);
                }
            }
        });
        getActivity().findViewById(R.id.btnTrangChu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.addFragment(HomeFragment.class,null);
            }
        });
        binding.btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s =  binding.btnDate.getText().toString();
                SimpleDateFormat formatIn = new SimpleDateFormat("dd / MM / yyyy");
                Date date1 = null;
                try {
                    date1 = formatIn.parse(s);
                } catch (ParseException e) {
                    Log.d("fat","Error Format Date:"+e);
                }
                SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy-MM-dd");
                updateDataUser.setBirthday(fmtOut.format(date1));
                updateDataUser.setAddress(binding.edtAdress.getText().toString());
                updateDataUser.setName(binding.edtName.getText().toString());
                updateDataUser.setPhone(binding.tvtPhone.getText().toString());
                if(binding.rdbtnMale.isChecked()){
                    updateDataUser.setGender(1);
                }else {
                    updateDataUser.setGender(2);
                }
                mViewModel.updateUser(updateDataUser);
                updateDataUserHealth.setHeight(Integer.parseInt(String.valueOf(binding.edtHeight.getText())));
                updateDataUserHealth.setWeight(Integer.parseInt(String.valueOf(binding.edtWeight.getText())));
                updateDataUserHealth.setBlood_group(binding.tvtBloodtype.getText().toString());
                updateDataUserHealth.setJudgment(binding.edtJudgment.getText().toString());
                mViewModel.updateUserHealth(updateDataUserHealth);
            }
        });
    }
    public void openDialog(){
        DialogCamera dialog = new DialogCamera(getContext());
        dialog.setContentView(R.layout.layout_dialog_camera);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        LinearLayout btn_chupanh = dialog.findViewById(R.id.btn_chupanh);
        LinearLayout btn_chonanh = dialog.findViewById(R.id.btn_chonanh);
        btn_chupanh.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog.dismiss();
            requestPermissionCamera();
        }
    });
        btn_chonanh.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog.dismiss();
            requestPermissionGallery();
        }
    });
    }

    public void openDialogDatePicker(){
        DialogCamera dialog = new DialogCamera(getContext());
        dialog.setContentView(R.layout.layout_dialog_datpicker);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setCancelable(false);
        dialog.show();
        DatePicker datePicker = dialog.findViewById(R.id.datePicker);
        Button button_ok = dialog.findViewById(R.id.btn_datepicker_ok);
        Button button_thoat =dialog.findViewById(R.id.btn_datepicker_thoat);;
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                binding.btnDate.setText(datePicker.getDayOfMonth()+" / "+ (datePicker.getMonth()+1)+" / "+ datePicker.getYear());
            }
        });
        button_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    private void requestPermissionCamera() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                openImageCamera();
            }
            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
            Toast.makeText(getContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }
    };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service" +
                        "\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA)//Manifest.permission.WRITE_EXTERNAL_STORAGE
                .check();
    }
    private void requestPermissionGallery() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                openImageGallery();
            }
            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(getContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions( Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }
    final ActivityResultLauncher<Intent> mActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if( result.getResultCode()== RESULT_OK){
                        Intent intent = result.getData();
                        if(intent.getExtras()!=null){
                            Bitmap photo = (Bitmap) intent.getExtras().get("data");
                            binding.btnCamera.setImageBitmap(photo);
                        }else {
                            Uri selectedImageUri = intent.getData();// gallery
                            binding.btnCamera.setImageURI(selectedImageUri);
                        }
                    }
                }
            });
    public void openImageGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResult.launch(intent);//intent //gallery
    }
    private void openImageCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);// camera
        mActivityResult.launch(cameraIntent); //camera
    }



}
