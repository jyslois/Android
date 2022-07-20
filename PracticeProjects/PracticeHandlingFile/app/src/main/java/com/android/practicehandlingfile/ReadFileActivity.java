package com.android.practicehandlingfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;

import com.android.practicehandlingfile.databinding.ActivityReadFileBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFileActivity extends AppCompatActivity {

    ActivityReadFileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadFileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "test.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuffer buffer = new StringBuffer();
            String line;

            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            br.close();

            binding.fileResult.setText(buffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}