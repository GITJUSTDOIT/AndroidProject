package com.example.userfit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OneRmUpdateActivity extends AppCompatActivity {

    private TextView tvTitle, tvUser;
    private EditText etOneRm;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_rm_update);

        tvTitle = findViewById(R.id.tv_onerm_title);
        tvUser = findViewById(R.id.tv_onerm_user);
        etOneRm = findViewById(R.id.et_onerm_value);
        btnSave = findViewById(R.id.btn_onerm_save);

        // 이름 받아와서 "OOO님의 1RM" 표시
        String name = getIntent().getStringExtra("name");
        if (name == null || name.isEmpty()) {
            name = "사용자";
        }
        tvUser.setText(name + "님의 1RM :");

        // 기존에 저장된 1RM 있으면 EditText에 표시
        SharedPreferences prefs = getSharedPreferences("userfit_prefs", MODE_PRIVATE);
        float savedOneRm = prefs.getFloat("one_rm", 0f);
        if (savedOneRm > 0) {
            etOneRm.setText(String.valueOf(savedOneRm));
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etOneRm.getText().toString().trim();
                if (input.isEmpty()) {
                    Toast.makeText(OneRmUpdateActivity.this,
                            "1RM 값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    float oneRm = Float.parseFloat(input);

                    // SharedPreferences에 저장
                    SharedPreferences prefs = getSharedPreferences("userfit_prefs", MODE_PRIVATE);
                    prefs.edit().putFloat("one_rm", oneRm).apply();

                    // 결과를 호출한 액티비티에 돌려주기
                    Intent data = new Intent();
                    data.putExtra("one_rm", oneRm);
                    setResult(RESULT_OK, data);

                    Toast.makeText(OneRmUpdateActivity.this,
                            "1RM이 저장되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (NumberFormatException e) {
                    Toast.makeText(OneRmUpdateActivity.this,
                            "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
