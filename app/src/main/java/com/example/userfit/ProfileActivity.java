package com.example.userfit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText etName, etHeight, etWeight;
    private RadioGroup rgGoal;
    private RadioButton rbDiet, rbMaintain, rbBulk;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etName = findViewById(R.id.et_name);
        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);

        rgGoal = findViewById(R.id.rg_goal);
        rbDiet = findViewById(R.id.rb_diet);
        rbMaintain = findViewById(R.id.rb_maintain);
        rbBulk = findViewById(R.id.rb_bulk);

        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String heightStr = etHeight.getText().toString().trim();
                String weightStr = etWeight.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(heightStr) || TextUtils.isEmpty(weightStr)) {
                    Toast.makeText(ProfileActivity.this, "이름, 키, 몸무게를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // ✅ 운동목적 하나 선택했는지 확인
                int checkedId = rgGoal.getCheckedRadioButtonId();
                if (checkedId == -1) {
                    Toast.makeText(ProfileActivity.this, "운동목적을 하나 선택해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String goal;
                if (checkedId == R.id.rb_diet) {
                    goal = "다이어트";
                } else if (checkedId == R.id.rb_maintain) {
                    goal = "유지";
                } else {
                    goal = "벌크업";
                }

                double height;
                double weight;

                try {
                    height = Double.parseDouble(heightStr);   // cm
                    weight = Double.parseDouble(weightStr);   // kg
                } catch (NumberFormatException e) {
                    Toast.makeText(ProfileActivity.this, "키와 몸무게는 숫자로 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // S 계산: 몸무게 / 키(cm) (필요하면 여기만 수정)
                double s = weight / height;
                String grade = calculateGrade(s);

                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("height", heightStr);
                intent.putExtra("weight", weightStr);
                intent.putExtra("goal", goal);          // ✅ 운동목적 전달
                intent.putExtra("s_value", s);
                intent.putExtra("s_grade", grade);
                startActivity(intent);
            }
        });
    }

    private String calculateGrade(double s) {
        if (s < 0.25) {
            return "A";
        } else if (s < 0.30) {
            return "B";
        } else if (s < 0.35) {
            return "C";
        } else if (s < 0.40) {
            return "D";
        } else if (s < 0.45) {
            return "E";
        } else {
            return "F";
        }
    }
}
