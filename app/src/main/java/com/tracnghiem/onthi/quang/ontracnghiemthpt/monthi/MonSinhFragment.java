package com.tracnghiem.onthi.quang.ontracnghiemthpt.monthi;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.examadapter.ExamAdapter;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.modelontap.Exam;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.slidetracnghiem.SlideActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonSinhFragment extends Fragment {
    private ExamAdapter examAdapter;
    private GridView gridView;
    ArrayList<Exam> arrayList = new ArrayList<>();

    public MonSinhFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mon_sinh, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridView = getActivity().findViewById(R.id.grviewsinh);
        arrayList.add(new Exam("Sinh Học: đề số 1"));
        arrayList.add(new Exam("Sinh Học: đề số 2"));
        arrayList.add(new Exam("Sinh Học: đề số 3"));
        arrayList.add(new Exam("Sinh Học: đề số 4"));
        arrayList.add(new Exam("Sinh Học: đề số 5"));
        arrayList.add(new Exam("Sinh Học: đề số 6"));
        arrayList.add(new Exam("Sinh Học: đề số 7"));
        arrayList.add(new Exam("Sinh Học: đề số 8"));

        examAdapter = new ExamAdapter(getActivity(), arrayList);
        gridView.setAdapter(examAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final ProgressDialog progress = new ProgressDialog(getActivity());
                progress.setMessage("Đang tải dữ liệu...");
                progress.show();
                Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        progress.cancel();

                    }
                };
                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 3000);
                Intent intent = new Intent(getContext(), SlideActivity.class);
                intent.putExtra("masodekiemtra", i + 1);
                intent.putExtra("monhoc", "SinhHoc");
                startActivity(intent);
            }
        });
    }

    }
