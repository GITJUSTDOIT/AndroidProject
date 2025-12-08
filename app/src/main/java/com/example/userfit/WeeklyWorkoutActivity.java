package com.example.userfit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WeeklyWorkoutActivity extends AppCompatActivity {

    private TextView tvTitle, tvSelectedDay;
    private TextView tvEx1, tvEx2, tvEx3, tvEx4;
    private Button btnMon, btnTue, btnWed, btnThu, btnFri, btnSat, btnSun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_workout);

        tvTitle = findViewById(R.id.tv_weekly_title);
        tvSelectedDay = findViewById(R.id.tv_selected_day);

        tvEx1 = findViewById(R.id.tv_ex1);
        tvEx2 = findViewById(R.id.tv_ex2);
        tvEx3 = findViewById(R.id.tv_ex3);
        tvEx4 = findViewById(R.id.tv_ex4);

        btnMon = findViewById(R.id.btn_mon);
        btnTue = findViewById(R.id.btn_tue);
        btnWed = findViewById(R.id.btn_wed);
        btnThu = findViewById(R.id.btn_thu);
        btnFri = findViewById(R.id.btn_fri);
        btnSat = findViewById(R.id.btn_sat);
        btnSun = findViewById(R.id.btn_sun);

        String grade = getIntent().getStringExtra("s_grade");
        String mode = getIntent().getStringExtra("mode"); // WEEKLY or 1RM_UPDATE

        if (mode != null && mode.equals("1RM_UPDATE")) {
            tvTitle.setText("1RM 갱신 (등급: " + (grade == null ? "-" : grade) + ")");
        } else {
            tvTitle.setText("요일별 추천운동 (등급: " + (grade == null ? "-" : grade) + ")");
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.btn_mon) {
                    showWorkout("MON");
                } else if (id == R.id.btn_tue) {
                    showWorkout("TUE");
                } else if (id == R.id.btn_wed) {
                    showWorkout("WED");
                } else if (id == R.id.btn_thu) {
                    showWorkout("THU");
                } else if (id == R.id.btn_fri) {
                    showWorkout("FRI");
                } else if (id == R.id.btn_sat) {
                    showWorkout("SAT");
                } else if (id == R.id.btn_sun) {
                    showWorkout("SUN");
                }
            }
        };

        btnMon.setOnClickListener(listener);
        btnTue.setOnClickListener(listener);
        btnWed.setOnClickListener(listener);
        btnThu.setOnClickListener(listener);
        btnFri.setOnClickListener(listener);
        btnSat.setOnClickListener(listener);
        btnSun.setOnClickListener(listener);

        // 기본값: 월요일
        showWorkout("MON");
    }

    /** 요일별로 4개 칸 채우기 (예시 루틴, 노트 내용으로 바꿔도 됨) */
    private void showWorkout(String day) {
        switch (day) {
            case "MON":
                tvSelectedDay.setText("월요일 루틴");
                tvEx1.setText("벤치 프레스 (5세트/5회)");
                tvEx2.setText("인클라인 벤치프레스 (3세트/8~10회)");
                tvEx3.setText("덤벨 플라이 (3세트/12회)");
                tvEx4.setText("푸시업 (3세트/최대횟수)");
                break;

            case "TUE":
                tvSelectedDay.setText("화요일 루틴");
                tvEx1.setText("랫풀다운 (4세트/10회)");
                tvEx2.setText("바벨 로우 (3세트/8~10회)");
                tvEx3.setText("시티드 로우 (3세트/12회)");
                tvEx4.setText("데드리프트 (3세트/5회)");
                break;

            case "WED":
                tvSelectedDay.setText("수요일 루틴");
                tvEx1.setText("밀리터리 프레스 (4세트/8~10회)");
                tvEx2.setText("덤벨 숄더프레스 (3세트/10회)");
                tvEx3.setText("사이드 레터럴 레이즈 (3세트/15회)");
                tvEx4.setText("리어 델트 플라이 (3세트/15회)");
                break;

            case "THU":
                tvSelectedDay.setText("목요일 루틴");
                tvEx1.setText("스쿼트 (5세트/5회)");
                tvEx2.setText("레그프레스 (4세트/10회)");
                tvEx3.setText("레그컬 (3세트/12회)");
                tvEx4.setText("레그익스텐션 (3세트/12회)");
                break;

            case "FRI":
                tvSelectedDay.setText("금요일 루틴");
                tvEx1.setText("벤치 프레스 가볍게 (3세트/10~12회)");
                tvEx2.setText("풀업 또는 풀다운 (3세트/최대횟수)");
                tvEx3.setText("숄더 + 코어 (플랭크 등)");
                tvEx4.setText("가벼운 유산소 20~30분");
                break;

            case "SAT":
                tvSelectedDay.setText("토요일 루틴");
                tvEx1.setText("가벼운 전신 루틴");
                tvEx2.setText("맨몸 스쿼트 / 런지");
                tvEx3.setText("코어운동(플랭크 등)");
                tvEx4.setText("스트레칭 10~15분");
                break;

            case "SUN":
                tvSelectedDay.setText("일요일 루틴");
                tvEx1.setText("휴식 또는 가벼운 산책");
                tvEx2.setText("");
                tvEx3.setText("");
                tvEx4.setText("");
                break;
        }
    }
}
