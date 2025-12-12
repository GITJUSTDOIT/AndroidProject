package com.example.userfit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WeeklyWorkoutActivity extends AppCompatActivity {

    private TextView tvTitle, tvSelectedDay;

    private ImageView ivEx1, ivEx2, ivEx3, ivEx4;
    private TextView tvEx1, tvEx2, tvEx3, tvEx4;
    private Button btnMon, btnTue, btnWed, btnThu, btnFri, btnSat, btnSun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_workout);

        // 제목, 선택 요일
        tvTitle = findViewById(R.id.tv_weekly_title);
        tvSelectedDay = findViewById(R.id.tv_selected_day);

        // 이미지 + 텍스트 한 세트씩
        ivEx1 = findViewById(R.id.iv_ex1);
        ivEx2 = findViewById(R.id.iv_ex2);
        ivEx3 = findViewById(R.id.iv_ex3);
        ivEx4 = findViewById(R.id.iv_ex4);

        tvEx1 = findViewById(R.id.tv_ex1);
        tvEx2 = findViewById(R.id.tv_ex2);
        tvEx3 = findViewById(R.id.tv_ex3);
        tvEx4 = findViewById(R.id.tv_ex4);

        // 요일 버튼
        btnMon = findViewById(R.id.btn_mon);
        btnTue = findViewById(R.id.btn_tue);
        btnWed = findViewById(R.id.btn_wed);
        btnThu = findViewById(R.id.btn_thu);
        btnFri = findViewById(R.id.btn_fri);
        btnSat = findViewById(R.id.btn_sat);
        btnSun = findViewById(R.id.btn_sun);

        // 등급 / 모드
        String grade = getIntent().getStringExtra("s_grade");
        String mode = getIntent().getStringExtra("mode"); // WEEKLY or 1RM_UPDATE

        if ("1RM_UPDATE".equals(mode)) {
            tvTitle.setText("1RM 갱신 (등급: " + (grade == null ? "-" : grade) + ")");
        } else {
            tvTitle.setText("요일별 추천운동");
        }

        // 공통 리스너
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

        // 기본: 월요일
        showWorkout("MON");
    }

    /** 공통으로 이미지/텍스트를 한 번에 비우는 헬퍼 */
    private void clearAll() {
        ivEx1.setImageDrawable(null);
        ivEx2.setImageDrawable(null);
        ivEx3.setImageDrawable(null);
        ivEx4.setImageDrawable(null);

        tvEx1.setText("");
        tvEx2.setText("");
        tvEx3.setText("");
        tvEx4.setText("");
    }

    /** 요일별로 4칸 텍스트 + 이미지 채우기 */
    private void showWorkout(String day) {

        clearAll();  // 먼저 초기화

        switch (day) {
            case "MON":
                tvSelectedDay.setText("월요일 루틴 (가슴)");

                // 1. 벤치프레스
                ivEx1.setImageResource(R.drawable.bench_press);
                tvEx1.setText("벤치 프레스 (5세트 / 5회)");

                // 2. 인클라인 벤치프레스
                ivEx2.setImageResource(R.drawable.incline_bench_press);
                tvEx2.setText("인클라인 벤치프레스 (3세트 / 8~10회)");

                // 3. 덤벨 플라이 → 펙덱 플라이 이미지 재사용
                ivEx3.setImageResource(R.drawable.pec_deck_fly);
                tvEx3.setText("덤벨 플라이 (3세트 / 12회)");

                // 4. 푸쉬업
                ivEx4.setImageResource(R.drawable.pushup);
                tvEx4.setText("푸쉬업 (3세트 / 최대 횟수)");
                break;

            case "TUE":
                tvSelectedDay.setText("화요일 루틴 (등)");

                // 1. 랫풀다운
                ivEx1.setImageResource(R.drawable.lat_pulldown);
                tvEx1.setText("랫풀다운 (4세트 / 10회)");

                // 2. 바벨 로우 → 티바로우 이미지 사용
                ivEx2.setImageResource(R.drawable.tbar_row);
                tvEx2.setText("바벨 로우 (3세트 / 8~10회)");

                // 3. 시티드 로우
                ivEx3.setImageResource(R.drawable.seated_row);
                tvEx3.setText("시티드 로우 (3세트 / 12회)");

                // 4. 데드리프트 → 일단 스쿼트 이미지로 대체(원하면 deadlift 그림 추가해서 교체)
                ivEx4.setImageResource(R.drawable.barbell_squat);
                tvEx4.setText("스쿼트 (3세트 / 5회)");
                break;

            case "WED":
                tvSelectedDay.setText("수요일 루틴 (어깨)");

                // 1. 밀리터리 프레스 → 스미스 머신 숄더프레스 이미지 사용
                ivEx1.setImageResource(R.drawable.smith_shoulder_press);
                tvEx1.setText("밀리터리 프레스 (4세트 / 8~10회)");

                // 2. 덤벨 숄더프레스
                ivEx2.setImageResource(R.drawable.dumbbell_shoulder_press);
                tvEx2.setText("덤벨 숄더프레스 (3세트 / 10회)");

                // 3. 사이드 레터럴 레이즈
                ivEx3.setImageResource(R.drawable.side_lateral_raise);
                tvEx3.setText("사이드 레터럴 레이즈 (3세트 / 15회)");

                // 4. 리어 델트 플라이 → 리버스 펙덱 플라이 이미지 사용
                ivEx4.setImageResource(R.drawable.reverse_pec_deck_fly);
                tvEx4.setText("리어 델트 플라이 (3세트 / 15회)");
                break;

            case "THU":
                tvSelectedDay.setText("목요일 루틴 (하체)");

                ivEx1.setImageResource(R.drawable.barbell_squat);
                tvEx1.setText("스쿼트 (5세트 / 5회)");

                ivEx2.setImageResource(R.drawable.leg_press);
                tvEx2.setText("레그프레스 (4세트 / 10회)");

                ivEx3.setImageResource(R.drawable.leg_curl);
                tvEx3.setText("레그컬 (3세트 / 12회)");

                ivEx4.setImageResource(R.drawable.leg_extension);
                tvEx4.setText("레그익스텐션 (3세트 / 12회)");
                break;

            case "FRI":
                tvSelectedDay.setText("금요일 루틴 (가슴 + 등 + 어깨)");

                ivEx1.setImageResource(R.drawable.bench_press);
                tvEx1.setText("벤치 프레스 (가볍게 3세트 / 10~12회)");

                // 풀업 또는 풀다운 → 풀업 그림 사용
                ivEx2.setImageResource(R.drawable.pullup);
                tvEx2.setText("풀업 또는 풀다운 (3세트 / 최대 횟수)");

                // 숄더 + 코어 → 덤벨 프론트 레이즈로 어깨 자극 표현
                ivEx3.setImageResource(R.drawable.dumbbell_front_raise);
                tvEx3.setText("어깨 + 코어 (프론트 레이즈, 플랭크 등)");

                // 유산소 → 이미지는 자유, 일단 비워두거나 원하는 그림 추가해도 됨
                ivEx4.setImageDrawable(null);
                tvEx4.setText("가벼운 유산소 20~30분");
                break;

            case "SAT":
                tvSelectedDay.setText("토요일 루틴 (가벼운 전신)");

                ivEx1.setImageResource(R.drawable.pushup);
                tvEx1.setText("가벼운 전신 루틴 (푸쉬업 등)");

                ivEx2.setImageResource(R.drawable.barbell_squat);
                tvEx2.setText("스쿼트");

                ivEx3.setImageDrawable(null); // 없으면 null 로 두고 직접 추가해도 됨
                tvEx3.setText("스트레칭 10~15분");

                break;

            case "SUN":
                tvSelectedDay.setText("일요일 루틴 (휴식)");

                // 휴식날은 그림/텍스트 다 비워두거나 안내 문구만
                tvEx1.setText("휴식 또는 가벼운 산책");
                ivEx1.setImageDrawable(null);

                // 나머지는 비움
                ivEx2.setImageDrawable(null);
                ivEx3.setImageDrawable(null);
                ivEx4.setImageDrawable(null);

                tvEx2.setText("");
                tvEx3.setText("");
                tvEx4.setText("");
                break;
        }
    }
}
