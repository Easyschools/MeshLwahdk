package com.ivestment.doctorna.ui.auth.registerStep3;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ivestment.doctorna.R;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegisterStep3Activity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "Register";
    private String mAlbumFiles;
    private ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
    private MultipartBody.Part image;
    private ConstraintLayout clUpdateScansImages, clScansImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step3);

        clUpdateScansImages = findViewById(R.id.cl_register_updateScansImages);
        clUpdateScansImages.setOnClickListener(this);

        clScansImages = findViewById(R.id.cl_register_scansImages);
        clScansImages.setOnClickListener(this);

        ImageView ivScansImage1 = findViewById(R.id.iv_register_scansImage1);
        imageViewArrayList.add(ivScansImage1);
        ImageView ivScansImage2 = findViewById(R.id.iv_register_scansImage2);
        imageViewArrayList.add(ivScansImage2);
        ImageView ivScansImage3 = findViewById(R.id.iv_register_scansImage3);
        imageViewArrayList.add(ivScansImage3);
        ImageView ivScansImage4 = findViewById(R.id.iv_register_scansImage4);
        imageViewArrayList.add(ivScansImage4);


        ConstraintLayout clUpdateLabResultsImages = findViewById(R.id.cl_register_updateLabResultsImages);
        clUpdateLabResultsImages.setOnClickListener(this);

        ConstraintLayout clLabResultsImages = findViewById(R.id.cl_register_labResultsImages);
        clLabResultsImages.setOnClickListener(this);

        ImageView ivLabResultsImage1 = findViewById(R.id.iv_register_labResultsImage1);
        ImageView ivLabResultsImage2 = findViewById(R.id.iv_register_labResultsImage2);
        ImageView ivLabResultsImage3 = findViewById(R.id.iv_register_labResultsImage3);
        ImageView ivLabResultsImage4 = findViewById(R.id.iv_register_labResultsImage4);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cl_register_updateScansImages:
            case R.id.cl_register_scansImages:
//                selectIdentityCardImage("xrays");
                break;
            case R.id.cl_register_updateLabResultsImages:
            case R.id.cl_register_labResultsImages:
                break;
        }
    }


    //==============================================================================================
    //This Method From "yanzhenjie library" to open Gallery/Camera
    //MultiSelectionImages "Only" + Camera Intent
//    private void selectIdentityCardImage(String key) {
//        Album.image(this)
//                .multipleChoice()
//                .camera(true)
//                .columnCount(2)
//                //Max images count = 10
//                .selectCount(4)
//                .checkedList(mAlbumFiles)
//                .onResult(result -> {
//                    mAlbumFiles = result;
//                    for (int i = 0; i < mAlbumFiles.size(); i++) {
//                        Album.getAlbumConfig().
//                                getAlbumLoader().
//                                load(imageViewArrayList.get(i), mAlbumFiles.get(i));
//                    }
//                    setIdentityCardImages(result, key);
//                })
//                .onCancel(result -> Log.v(TAG, result))
//                .start();
//    }
    //==============================================================================================


    //==============================================================================================
    //This Method to handle retrofit multipart + key
    private void setIdentityCardImages(String result, String key) {
        clUpdateScansImages.setVisibility(View.GONE);
        clScansImages.setVisibility(View.VISIBLE);
            File file = new File(result);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            // "images[]" is the image key in api request
            try {
                image = MultipartBody.Part.createFormData("image", URLEncoder.encode(file.getName(), "utf-8"), requestBody);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

}