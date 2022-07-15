package com.android.practiceconnectingwithgoogleapps;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.android.practiceconnectingwithgoogleapps.databinding.ActivityMainBinding;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 카메라 앱 연동. 되돌아왔을 때 사후 처리를 위해 ActivityResultLauncher 준비
        ActivityResultLauncher<Intent> cameraFileLauncher = registerForActivityResult(
                // 인턴트를 처리해 줄 수 있는 객체
                new ActivityResultContracts.StartActivityForResult(),
                // 되돌아 왔을 때 콜백
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // 되돌아 왔을 때 처리 내용을 이 함수에 담아주면 된다

                        // 화면에 이미지의 사이즈를 줄여서 출력하기 위한 사이즈 계산
                        // 밑에 정의되어 있는 calculateInSampleSize 콜하기
                       int calRatio = calculateInSampleSize(

                                result.getData().getData(),
                               // 혹은 파일 url값 - 카메라 앱에 공개한 우리의 파일에 저장된 데이터의 url
//                                Uri.fromFile(new File(filePath)),
                                // 화면에 찍고자 하는 사이즈
                                getResources().getDimensionPixelSize(R.dimen.imgSize),
                                getResources().getDimensionPixelSize(R.dimen.imgSize)
                        );

                       // calRatio 사이즈로 화면에 사진 출력하기
                        BitmapFactory.Options option = new BitmapFactory.Options();
                        option.inSampleSize = calRatio;
                        Bitmap bitmap = BitmapFactory.decodeFile(filePath, option);

                        if (bitmap != null) {
                            binding.resultImageView.setImageBitmap(bitmap);
                        }
                    }
                });

        // 카메라 버튼이 눌리면 카메라 앱 연동
        binding.cameraButton.setOnClickListener(view -> {

            try {
                // 파일 만들기. 시분초로 파일명 스트링 만들어서.
                // 그 스트링을 파일명으로 삼기
               String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                // 사진이 저장되는 외장 메모리 공간 지정(디렉토리 지정)
               File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                // 파일 만들기. JPEG_시간날짜_.jpg 이름으로 디렉터리(우리가 지정한 디렉토리)에 파일 생성
               File file = File.createTempFile(
                        "JPEG_" + timeStamp + "_", ".jpg", storageDir
                );
               // 파일 경로 지정
               filePath = file.getAbsolutePath();
               // 외부 앱에 공개. 파일 프로파이더 이용. 공개하기 위한 Uri 만들기
               // 이 파일을 지칭하는 Uri값
               Uri photoURI = FileProvider.getUriForFile(this, "com.android.practiceconnectingwithgoogleapps", file);

               Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
               cameraFileLauncher.launch(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        // 갤러리 앱 연동. 사후 처리를 위한 ActivityResultLauncher.
        ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int calRatio = calculateInSampleSize(
                                // 갤러리 앱에서 얻은 사진 데이터
                                result.getData().getData(),
                                getResources().getDimensionPixelSize(R.dimen.imgSize),
                                getResources().getDimensionPixelSize(R.dimen.imgSize)
                        );

                        BitmapFactory.Options option = new BitmapFactory.Options();
                        option.inSampleSize = calRatio;

                        try {
                            // 갤러리앱 쪽에서 제공하는 사진 읽는 스트림
                            InputStream inputStream = getContentResolver().openInputStream(result.getData().getData());
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, option);
                            inputStream.close();
                            if (bitmap != null) {
                                binding.resultImageView.setImageBitmap(bitmap);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        // 갤러리 버튼을 눌렀을 때 런쳐 구동
        binding.galleryButton.setOnClickListener(view -> {
            // 액션 정보, 데이터 정보, 타입 정보
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            galleryLauncher.launch(intent);
        });

    }


    // 몇 분에 몇으로 줄여야 하느냐를 계산해 주는 함수
    private int calculateInSampleSize(Uri fileUri, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            InputStream inputStream = getContentResolver().openInputStream(fileUri);

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();
            inputStream = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //비율 계산........................
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;

        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            int halfHeight = height / 2;
            int halfWidth = width / 2;

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}