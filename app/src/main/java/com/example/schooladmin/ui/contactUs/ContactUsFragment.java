package com.example.schooladmin.ui.contactUs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.schooladmin.R;

public class ContactUsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Set view from the XML file
        View root = inflater.inflate(R.layout.fragment_contact_us, container, false);
        return root;
    }
}