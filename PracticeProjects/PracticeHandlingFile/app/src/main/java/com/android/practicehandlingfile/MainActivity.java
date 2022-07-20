package com.android.practicehandlingfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.android.practicehandlingfile.databinding.ActivityMainBinding;
import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.saveButton.setOnClickListener(view -> {
            // 외장, 앱별 read/write
            try {
                File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "test.txt");

                // 파일이 없다면 새로 만들어 주기
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter writer = new FileWriter(file, true);
                writer.write(binding.content.getText().toString());
                writer.flush();
                writer.close();
                Toast.makeText(this, "file saved", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, ReadFileActivity.class);
                startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}