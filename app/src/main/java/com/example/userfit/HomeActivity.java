package com.example.userfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome, tvSInfo;
    private LinearLayout layoutWeekly, layoutDiet, layoutInfo, layoutOnerm;

    private double sValue;
    private String sGrade;
    private String name;
    private String goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.tv_welcome);
        tvSInfo = findViewById(R.id.tv_s_info);

        layoutWeekly = findViewById(R.id.layout_weekly);
        layoutDiet = findViewById(R.id.layout_diet);
        layoutInfo = findViewById(R.id.layout_info);
        layoutOnerm = findViewById(R.id.layout_onerm);   // ✅ 1RM 갱신 카드

        name = getIntent().getStringExtra("name");
        sValue = getIntent().getDoubleExtra("s_value", 0.0);
        sGrade = getIntent().getStringExtra("s_grade");
        goal = getIntent().getStringExtra("goal");

        if (name == null) name = "";
        if (sGrade == null) sGrade = "-";
        if (goal == null || goal.isEmpty()) goal = "미선택";

        tvWelcome.setText(name + "님 환영합니다!!");
        String sText = String.format("운동목적: %s", goal);
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

        // 맞춤 식단 화면
        layoutDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MealPlanActivity.class);
                intent.putExtra("s_value", sValue);
                intent.putExtra("s_grade", sGrade);
                startActivity(intent);
            }
        });

        // 운동 정보 (부위 선택 + 벤치프레스 표)
        layoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ExerciseListActivity.class);
                intent.putExtra("s_value", sValue);
                intent.putExtra("s_grade", sGrade);
                intent.putExtra("name", name); // ✅ ExerciseList에서 이름도 사용
                startActivity(intent);
            }
        });

        // ✅ 1RM 갱신 카드
        layoutOnerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, OneRmUpdateActivity.class);
                intent.putExtra("name", name);     // "OOO님의 1RM"
                startActivity(intent);             // 결과는 SharedPreferences로만 저장
            }
        });
    }
}
