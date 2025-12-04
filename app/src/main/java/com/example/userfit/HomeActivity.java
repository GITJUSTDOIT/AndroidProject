package com.example.userfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private LinearLayout layoutWeekly, layoutDiet, layoutInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.tv_welcome);
        layoutWeekly = findViewById(R.id.layout_weekly);
        layoutDiet = findViewById(R.id.layout_diet);
        layoutInfo = findViewById(R.id.layout_info);

        String name = getIntent().getStringExtra("name");
        if (name == null) name = "";
        tvWelcome.setText(name + "님 환영합니다!!");

        layoutWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, WeeklyWorkoutActivity.class);
                startActivity(intent);
            }
        });

        layoutDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "맞춤 식단은 추후 추가 예정입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        layoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "운동 정보는 추후 추가 예정입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
