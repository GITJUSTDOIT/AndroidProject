package com.example.userfit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseListActivity extends AppCompatActivity {

    private TextView tvTitle, tvSelectedPart, tvExerciseList;
    private Button btnChest, btnShoulder, btnBack, btnLeg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        tvTitle = findViewById(R.id.tv_ex_list_title);
        tvSelectedPart = findViewById(R.id.tv_selected_part);
        tvExerciseList = findViewById(R.id.tv_exercise_list);

        btnChest = findViewById(R.id.btn_part_chest);
        btnShoulder = findViewById(R.id.btn_part_shoulder);
        btnBack = findViewById(R.id.btn_part_back);
        btnLeg = findViewById(R.id.btn_part_leg);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn_part_chest) {
                    showChestExercises();
                } else if (view.getId() == R.id.btn_part_shoulder) {
                    showShoulderExercises();
                } else if (view.getId() == R.id.btn_part_back) {
                    showBackExercises();
                } else if (view.getId() == R.id.btn_part_leg) {
                    showLegExercises();
                }
            }
        };

        btnChest.setOnClickListener(listener);
        btnShoulder.setOnClickListener(listener);
        btnBack.setOnClickListener(listener);
        btnLeg.setOnClickListener(listener);
    }

    private void showChestExercises() {
        tvSelectedPart.setText("가슴 운동");

        // ✏️ 아래 문자열들을 노트에 있는 내용대로 바꾸면 됨
        String text =
                "1. 펙덱 플라이\n" +
                        "   - 세트/반복/무게: (예시) 3세트 x 12회 @ 체중 대비 ~\n\n" +
                        "2. 벤치 프레스\n" +
                        "   - (예시) 4세트 x 8~10회\n\n" +
                        "3. 인클라인 벤치프레스\n" +
                        "4. 인클라인 덤벨프레스\n" +
                        "5. 딥스\n";

        tvExerciseList.setText(text);
    }

    private void showShoulderExercises() {
        tvSelectedPart.setText("어깨 운동");

        String text =
                "1. 밀리터리 프레스\n" +
                        "2. 덤벨 숄더프레스\n" +
                        "3. 사이드 레터럴 레이즈\n" +
                        "4. 리어 델트 플라이\n";
        tvExerciseList.setText(text);
    }

    private void showBackExercises() {
        tvSelectedPart.setText("등 운동");

        String text =
                "1. 랫풀다운\n" +
                        "2. 턱걸이\n" +
                        "3. 바벨 로우\n" +
                        "4. 시티드 로우\n";
        tvExerciseList.setText(text);
    }

    private void showLegExercises() {
        tvSelectedPart.setText("하체 운동");

        String text =
                "1. 스쿼트\n" +
                        "2. 레그프레스\n" +
                        "3. 레그컬 / 레그익스텐션\n" +
                        "4. 런지\n";
        tvExerciseList.setText(text);
    }
}
