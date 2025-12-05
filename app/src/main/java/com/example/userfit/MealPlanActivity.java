package com.example.userfit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MealPlanActivity extends AppCompatActivity {

    private Button btnMon, btnTue, btnWed, btnThu, btnFri;
    private TextView tvSelectedDay;
    private TextView tvBreakfast, tvLunch, tvDinner, tvSnack, tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);

        btnMon = findViewById(R.id.btn_meal_mon);
        btnTue = findViewById(R.id.btn_meal_tue);
        btnWed = findViewById(R.id.btn_meal_wed);
        btnThu = findViewById(R.id.btn_meal_thu);
        btnFri = findViewById(R.id.btn_meal_fri);

        tvSelectedDay = findViewById(R.id.tv_meal_selected_day);
        tvBreakfast = findViewById(R.id.tv_meal_breakfast);
        tvLunch = findViewById(R.id.tv_meal_lunch);
        tvDinner = findViewById(R.id.tv_meal_dinner);
        tvSnack = findViewById(R.id.tv_meal_snack);
        tvTotal = findViewById(R.id.tv_meal_total);

        // 처음에 월요일 기준으로 한 번 채워두고 싶으면 이 줄 추가
        showMealPlan("MON");

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn_meal_mon) {
                    showMealPlan("MON");
                } else if (view.getId() == R.id.btn_meal_tue) {
                    showMealPlan("TUE");
                } else if (view.getId() == R.id.btn_meal_wed) {
                    showMealPlan("WED");
                } else if (view.getId() == R.id.btn_meal_thu) {
                    showMealPlan("THU");
                } else if (view.getId() == R.id.btn_meal_fri) {
                    showMealPlan("FRI");
                }
            }
        };

        btnMon.setOnClickListener(listener);
        btnTue.setOnClickListener(listener);
        btnWed.setOnClickListener(listener);
        btnThu.setOnClickListener(listener);
        btnFri.setOnClickListener(listener);
    }

    /**
     * 요일별 식단 표시
     * 아래 메뉴/칼로리 부분을 노트에 적어둔 내용으로 바꾸시면 됩니다.
     */
    private void showMealPlan(String day) {
        switch (day) {
            case "MON":
                tvSelectedDay.setText("월요일 식단");

                tvBreakfast.setText("고구마 토스트 (250kcal)");
                tvLunch.setText("닭가슴살 샐러드 (350kcal)");
                tvDinner.setText("볶음국수 (400kcal)");
                tvSnack.setText("요거트 1컵 (150kcal)");
                tvTotal.setText("1150 kcal");
                break;

            case "TUE":
                tvSelectedDay.setText("화요일 식단");

                tvBreakfast.setText("콘프레이크 + 우유 (300kcal)");
                tvLunch.setText("연어샐러드 (400kcal)");
                tvDinner.setText("현미밥 + 닭가슴살 (500kcal)");
                tvSnack.setText("견과류 한 줌 (150kcal)");
                tvTotal.setText("1350 kcal");
                break;

            case "WED":
                tvSelectedDay.setText("수요일 식단");

                tvBreakfast.setText("닭가슴살 샌드위치 (320kcal)");
                tvLunch.setText("불고기덮밥(500kcal)");
                tvDinner.setText("샐러드 + 계란2개 (350kcal)");
                tvSnack.setText("바나나 1개 (100kcal)");
                tvTotal.setText("1270 kcal");
                break;

            case "THU":
                tvSelectedDay.setText("목요일 식단");

                tvBreakfast.setText("오트밀 + 바나나 (300kcal)");
                tvLunch.setText("닭가슴살 비빔밥 (550kcal)");
                tvDinner.setText("토마토 파스타 (450kcal)");
                tvSnack.setText("프로틴 쉐이크 (200kcal)");
                tvTotal.setText("1500 kcal");
                break;

            case "FRI":
                tvSelectedDay.setText("금요일 식단");

                tvBreakfast.setText("과일샐러드 (250kcal)");
                tvLunch.setText("닭가슴살 도시락 (500kcal)");
                tvDinner.setText("연어 스테이크 (450kcal)");
                tvSnack.setText("그릭요거트 (150kcal)");
                tvTotal.setText("1350 kcal");
                break;
        }
    }
}
