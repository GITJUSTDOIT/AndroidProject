package com.example.userfit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseTypeActivity extends AppCompatActivity {

    private TextView tvTypeTitle;
    private Button btnTypeChest, btnTypeBack, btnTypeShoulder, btnTypeLeg;
    private TableLayout tableChest, tableBack, tableShoulder, tableLeg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_type);

        tvTypeTitle = findViewById(R.id.tv_type_title);
        btnTypeChest = findViewById(R.id.btn_type_chest);
        btnTypeBack = findViewById(R.id.btn_type_back);
        btnTypeShoulder = findViewById(R.id.btn_type_shoulder);
        btnTypeLeg = findViewById(R.id.btn_type_leg);

        tableChest = findViewById(R.id.table_type_chest);
        tableBack = findViewById(R.id.table_type_back);
        tableShoulder = findViewById(R.id.table_type_shoulder);
        tableLeg = findViewById(R.id.table_type_leg);

        // ExerciseListActivity에서 넘어온 부위 (기본값: 가슴)
        String part = getIntent().getStringExtra("part");
        if (part == null) part = "chest";

        showPart(part);

        btnTypeChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPart("chest");
            }
        });

        btnTypeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPart("back");
            }
        });

        btnTypeShoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPart("shoulder");
            }
        });

        btnTypeLeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPart("leg");
            }
        });
    }

    private void showPart(String part) {
        tableChest.setVisibility(View.GONE);
        tableBack.setVisibility(View.GONE);
        tableShoulder.setVisibility(View.GONE);
        tableLeg.setVisibility(View.GONE);

        switch (part) {
            case "back":
                tableBack.setVisibility(View.VISIBLE);
                tvTypeTitle.setText("등 운동 종류 (세부 설명)");
                break;
            case "shoulder":
                tableShoulder.setVisibility(View.VISIBLE);
                tvTypeTitle.setText("어깨 운동 종류 (세부 설명)");
                break;
            case "leg":
                tableLeg.setVisibility(View.VISIBLE);
                tvTypeTitle.setText("하체 운동 종류 (세부 설명)");
                break;
            case "chest":
            default:
                tableChest.setVisibility(View.VISIBLE);
                tvTypeTitle.setText("가슴 운동 종류 (세부 설명)");
                break;
        }
    }
}
