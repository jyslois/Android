package com.android.pratcieacitivitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.android.pratcieacitivitylifecycle.databinding.ActivityDialogBinding;

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDialogBinding binding = ActivityDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.dialogButton.setOnClickListener(view -> {
            this.finish();
        });
    }
}