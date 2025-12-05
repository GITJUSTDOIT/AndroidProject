package com.example.userfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome, tvSInfo;
    private LinearLayout layoutWeekly, layoutDiet, layoutInfo;

    private double sValue;
    private String sGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.tv_welcome);
        tvSInfo = findViewById(R.id.tv_s_info);
        layoutWeekly = findViewById(R.id.layout_weekly);
        layoutDiet = findViewById(R.id.layout_diet);
        layoutInfo = findViewById(R.id.layout_info);

        String name = getIntent().getStringExtra("name");
        sValue = getIntent().getDoubleExtra("s_value", 0.0);
        sGrade = getIntent().getStringExtra("s_grade");
        String goal = getIntent().getStringExtra("goal");

        if (name == null) name = "";
        if (sGrade == null) sGrade = "-";
        if (goal == null || goal.isEmpty()) goal = "미선택";
        tvWelcome.setText(name + "님 환영합니다!!");

        String sText = String.format("S = %.3f  /  등급: %s\n운동목적: %s", sValue, sGrade, goal);
        tvSInfo.setText(sText);

        // 요일별 추천운동 화면
        layoutWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, WeeklyWorkoutActivity.class);
                intent.putExtra("s_value", sValue);
                intent.putExtra("s_grade", sGrade);
                startActivity(intent);
            }
        });

        // 식단 화면
        layoutDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MealPlanActivity.class);
                intent.putExtra("s_value", sValue);
                intent.putExtra("s_grade", sGrade);
                startActivity(intent);
            }
        });

        // 운동 정보 목록
        layoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ExerciseListActivity.class);
                intent.putExtra("s_value", sValue);
                intent.putExtra("s_grade", sGrade);
                startActivity(intent);
            }
        });
    }
}
