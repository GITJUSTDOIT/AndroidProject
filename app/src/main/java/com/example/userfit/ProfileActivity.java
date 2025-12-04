package com.example.userfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText etName, etHeight, etWeight;
    private CheckBox cbDiet, cbMaintain, cbBulk;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etName = findViewById(R.id.et_name);
        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);

        cbDiet = findViewById(R.id.cb_diet);
        cbMaintain = findViewById(R.id.cb_maintain);
        cbBulk = findViewById(R.id.cb_bulk);

        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String height = etHeight.getText().toString().trim();
                String weight = etWeight.getText().toString().trim();

                String goal = "";
                if (cbDiet.isChecked()) goal += "다이어트 ";
                if (cbMaintain.isChecked()) goal += "유지 ";
                if (cbBulk.isChecked()) goal += "벌크업 ";

                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("height", height);
                intent.putExtra("weight", weight);
                intent.putExtra("goal", goal.trim());
                startActivity(intent);
            }
        });
    }
}
