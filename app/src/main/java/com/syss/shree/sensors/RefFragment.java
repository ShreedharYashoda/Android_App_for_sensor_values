package com.syss.shree.sensors;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class RefFragment extends Fragment {

    TextView tv_ref,tv_list;

    Button btn1,btn2,btn3,btn4,btn5,btn6;

    public RefFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.fragment_ref, container, false);
        tv_ref = (TextView) rootView.findViewById(R.id.textView9);
        tv_list = (TextView) rootView.findViewById(R.id.textView10);


        btn1=(Button) rootView.findViewById(R.id.button1);
        btn2=(Button) rootView.findViewById(R.id.button2);
        btn3=(Button) rootView.findViewById(R.id.button3);
        btn4=(Button) rootView.findViewById(R.id.button4);
        btn5=(Button) rootView.findViewById(R.id.button5);
        btn6=(Button) rootView.findViewById(R.id.button6);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/YrI2pCZC8cc"));
                startActivity(in);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/bjYstsO1PgI"));
                startActivity(in);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/zYVEMCiDcmY"));
                startActivity(in);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/fGcMLu1GJEc"));
                startActivity(in);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/ls1cjNcgdFI"));
                startActivity(in);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/dI9TItdw83U"));
                startActivity(in);
            }
        });
        return rootView;
    }
}
