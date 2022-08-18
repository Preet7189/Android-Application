package com.example.schooladmin.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.schooladmin.R;
import com.example.schooladmin.ui.activity.AddStudent;
import com.example.schooladmin.ui.activity.StudentListActivity;

public class HomeFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //Get Ids of all the required fields
        final CardView cardAddStudent = root.findViewById(R.id.cardAddStudent);
        final CardView cardStudentList = root.findViewById(R.id.cardStudentList);
        final CardView cardMarkAttendance = root.findViewById(R.id.cardMarkAttendance);


        //Click on the add student
        cardAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(getActivity(), AddStudent.class);
            startActivity(intent);
            }
        });

        //Click on the student List
        cardStudentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudentListActivity.class);
                Bundle b = new Bundle();
                b.putString("TAG","EmployeeList");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        //Click on the Mark Attendance
        cardMarkAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudentListActivity.class);
                Bundle b = new Bundle();
                b.putString("TAG","MarkAttendance");
                intent.putExtras(b);
                startActivity(intent);
            }
        });


        return root;
    }
}