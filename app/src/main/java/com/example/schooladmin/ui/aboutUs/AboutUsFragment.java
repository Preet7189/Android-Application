package com.example.schooladmin.ui.aboutUs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.schooladmin.R;

public class AboutUsFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Load view from the xml
        View root = inflater.inflate(R.layout.fragment_about_us, container, false);
        return root;
    }
}