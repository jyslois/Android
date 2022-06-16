package com.android.practicefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.practicefragment.databinding.FragmentOneBinding;

public class OneFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
        FragmentOneBinding binding = FragmentOneBinding.inflate(inflator, container, false);

        binding.button.setOnClickListener(view -> {
            // Fragment 내에서 getActivity() 함수로 Activity 객체 획득
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            TwoFragment twoFragment = new TwoFragment();
            ft.replace(R.id.main_content, twoFragment);

            ft.addToBackStack(null);
            ft.commit();
        });

        return binding.getRoot();
    }

}