package com.example.userfit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ExerciseListActivity extends AppCompatActivity {

    private TextView tvSelectedPart;
    private TextView tvExerciseList; // 지금은 안 쓰지만 id 때문에 남겨둠

    // 테이블들
    private TableLayout tableChest, tableBack, tableShoulder, tableLeg;

    // 가슴 무게 TextView들 (S값 & 1RM에 따라 바뀜)
    private TextView tvWeightPecDeck, tvWeightBench, tvWeightIncline,
            tvWeightInclineDumbbell, tvWeightDips, tvWeightPushup;

    // 하체 스쿼트 무게 (1RM 80%)
    private TextView tvWeightSquat;

    private Button btnChest, btnBack, btnShoulder, btnLeg, btnPartTypes;

    private String sGrade;
    private String currentPart = "chest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        tvSelectedPart = findViewById(R.id.tv_selected_part);
        tvExerciseList = findViewById(R.id.tv_exercise_list);

        tableChest = findViewById(R.id.table_chest);
        tableBack = findViewById(R.id.table_back);
        tableShoulder = findViewById(R.id.table_shoulder);
        tableLeg = findViewById(R.id.table_leg);

        tvWeightPecDeck = findViewById(R.id.tv_weight_pecdeck);
        tvWeightBench = findViewById(R.id.tv_weight_bench);
        tvWeightIncline = findViewById(R.id.tv_weight_incline);
        tvWeightInclineDumbbell = findViewById(R.id.tv_weight_incline_dumbbell);
        tvWeightDips = findViewById(R.id.tv_weight_dips);
        tvWeightPushup = findViewById(R.id.tv_weight_pushup);
        tvWeightSquat = findViewById(R.id.tv_weight_squat);

        btnChest = findViewById(R.id.btn_chest);
        btnBack = findViewById(R.id.btn_back);
        btnShoulder = findViewById(R.id.btn_shoulder);
        btnLeg = findViewById(R.id.btn_leg);
        btnPartTypes = findViewById(R.id.btn_part_types);

        sGrade = getIntent().getStringExtra("s_grade");

        btnChest.setOnClickListener(v -> showChestExercises());
        btnBack.setOnClickListener(v -> showBackExercises());
        btnShoulder.setOnClickListener(v -> showShoulderExercises());
        btnLeg.setOnClickListener(v -> showLegExercises());

        btnPartTypes.setOnClickListener(v -> {
            android.content.Intent intent =
                    new android.content.Intent(ExerciseListActivity.this, ExerciseTypeActivity.class);
            intent.putExtra("part", currentPart);
            startActivity(intent);
        });

        // 처음 들어오면 가슴부터 보게 하고 싶으면:
        showChestExercises();
    }

    /** 등급 -> 무게 배율 (S값 A~F) */
    private double getWeightFactor(String grade) {
        if (grade == null) return 1.0;
        switch (grade) {
            case "A": return 0.4;
            case "B": return 0.6;
            case "C": return 0.8;
            case "D": return 1.0;
            case "E": return 1.1;
            case "F": return 1.2;
            default:  return 1.0;
        }
    }

    /** SharedPreferences에 저장된 1RM 가져오기 (없으면 0) */
    private float getSavedOneRm() {
        SharedPreferences prefs = getSharedPreferences("userfit_prefs", MODE_PRIVATE);
        return prefs.getFloat("one_rm", 0f);
    }

    /** 현재 부위가 가슴/하체일 때만 1RM 적용 */
    private void applyOneRmIfExists() {
        float oneRm = getSavedOneRm();
        if (oneRm <= 0) return;

        double eighty = oneRm * 0.8; // 1RM의 80%

        if ("chest".equals(currentPart)) {
            String text = String.format(Locale.getDefault(),
                    "%.1f kg (1RM의 80%%)", eighty);
            tvWeightBench.setText(text);
        } else if ("leg".equals(currentPart)) {
            String text = String.format(Locale.getDefault(),
                    "%.1f kg (1RM의 80%%)", eighty);
            tvWeightSquat.setText(text);
        }
    }

    /** 네 개 테이블 중 어떤 것만 보여줄지 공통 처리 */
    private void setTableVisible(TableLayout showTable) {
        tableChest.setVisibility(View.GONE);
        tableBack.setVisibility(View.GONE);
        tableShoulder.setVisibility(View.GONE);
        tableLeg.setVisibility(View.GONE);

        showTable.setVisibility(View.VISIBLE);
        tvExerciseList.setVisibility(View.GONE);
    }

    /** 가슴운동 화면 */
    private void showChestExercises() {
        currentPart = "chest";
        tvSelectedPart.setText("가슴 운동");
        setTableVisible(tableChest);

        double factor = getWeightFactor(sGrade);

        int basePecDeck = 25;
        int baseIncline = 30;
        int baseInclineDumbbell = 10;

        int pecDeckKg = (int) Math.round(basePecDeck * factor);
        int inclineKg = (int) Math.round(baseIncline * factor);
        int inclineDumbbellKg = (int) Math.round(baseInclineDumbbell * factor);

        tvWeightPecDeck.setText(pecDeckKg + " kg");
        tvWeightBench.setText("벤치 1RM의 80%");
        tvWeightIncline.setText(inclineKg + " kg");
        tvWeightInclineDumbbell.setText(inclineDumbbellKg + " kg (한 팔)");
        tvWeightDips.setText("체중");
        tvWeightPushup.setText("체중");

        applyOneRmIfExists(); // 벤치프레스에 1RM 적용
    }

    /** 등운동 화면 */
    private void showBackExercises() {
        currentPart = "back";
        tvSelectedPart.setText("등 운동");
        setTableVisible(tableBack);
        // 등은 고정 무게라 별도 계산 없음
    }

    /** 어깨운동 화면 */
    private void showShoulderExercises() {
        currentPart = "shoulder";
        tvSelectedPart.setText("어깨 운동");
        setTableVisible(tableShoulder);
    }

    /** 하체운동 화면 */
    private void showLegExercises() {
        currentPart = "leg";
        tvSelectedPart.setText("하체 운동");
        setTableVisible(tableLeg);

        // 기본 문구
        tvWeightSquat.setText("스쿼트 1RM의 80%");

        // 1RM이 있으면 스쿼트 무게도 80%로 갱신
        applyOneRmIfExists();
    }
}
