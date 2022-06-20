package com.android.practiceviewpager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;

import com.android.practiceviewpager2.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.viewpager.setAdapter(new MyPagerAdapter(this));
    }

    class MyPagerAdapter extends FragmentStateAdapter {
        List<Fragment> fragments;
        MyPagerAdapter(FragmentActivity activity) {
            super(activity);
            fragments = new ArrayList<>();
            fragments.add(new RedFragment());
            fragments.add(new GreenFragment());
            fragments.add(new BlueFragment());
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }
    }


}