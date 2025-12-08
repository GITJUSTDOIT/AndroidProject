package com.example.userfit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MealPlanActivity extends AppCompatActivity {

    private TextView tvSelectedDay;
    private TextView tvBreakfast, tvLunch, tvDinner, tvSnack, tvTotal;

    private Button btnMon, btnTue, btnWed, btnThu, btnFri, btnSat, btnSun;

    /** 요일별 식단 하나를 저장하는 클래스 */
    private static class DayMeal {
        String breakfast;
        String lunch;
        String dinner;
        String snack;
        String totalKcal;

        DayMeal(String b, String l, String d, String s, String t) {
            breakfast = b;
            lunch = l;
            dinner = d;
            snack = s;
            totalKcal = t;
        }
    }

    /** 목표별 식단 플랜들 */
    private final Map<String, DayMeal> dietPlan = new HashMap<>();      // 다이어트
    private final Map<String, DayMeal> bulkPlan = new HashMap<>();      // 벌크업
    private final Map<String, DayMeal> maintainPlan = new HashMap<>();  // 유지

    /** 현재 선택된 플랜(위 셋 중 하나) */
    private Map<String, DayMeal> currentPlan;

    /** HomeActivity에서 넘어온 운동목적 */
    private String goal;   // "다이어트" / "유지" / "벌크업" / 등

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);

        // ---------- View 연결 ----------
        tvSelectedDay = findViewById(R.id.tv_meal_selected_day);
        tvBreakfast   = findViewById(R.id.tv_meal_breakfast);
        tvLunch       = findViewById(R.id.tv_meal_lunch);
        tvDinner      = findViewById(R.id.tv_meal_dinner);
        tvSnack       = findViewById(R.id.tv_meal_snack);
        tvTotal       = findViewById(R.id.tv_meal_total);

        btnMon = findViewById(R.id.btn_meal_mon);
        btnTue = findViewById(R.id.btn_meal_tue);
        btnWed = findViewById(R.id.btn_meal_wed);
        btnThu = findViewById(R.id.btn_meal_thu);
        btnFri = findViewById(R.id.btn_meal_fri);
        btnSat = findViewById(R.id.btn_meal_sat);
        btnSun = findViewById(R.id.btn_meal_sun);

        // ---------- 운동목적 받아오기 ----------
        goal = getIntent().getStringExtra("goal");
        if (goal == null || goal.isEmpty()) {
            goal = "미선택";
        }

        // ---------- 식단 데이터 초기화 ----------
        initDietPlan();
        initBulkPlan();
        initMaintainPlan();

        // ---------- 목표에 맞는 플랜 선택 ----------
        if ("다이어트".equals(goal)) {
            currentPlan = dietPlan;
        } else if ("벌크업".equals(goal)) {
            currentPlan = bulkPlan;
        } else if ("유지".equals(goal)) {
            currentPlan = maintainPlan;
        } else {
            // 혹시 이상한 값이면 유지 플랜 사용
            currentPlan = maintainPlan;
        }

        tvSelectedDay.setText("목표: " + goal + " / 요일을 선택하세요.");

        // ---------- 요일 버튼 공통 리스너 ----------
        View.OnClickListener dayClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dayKey;
                int id = v.getId();

                if (id == R.id.btn_meal_mon) {
                    dayKey = "월";
                } else if (id == R.id.btn_meal_tue) {
                    dayKey = "화";
                } else if (id == R.id.btn_meal_wed) {
                    dayKey = "수";
                } else if (id == R.id.btn_meal_thu) {
                    dayKey = "목";
                } else if (id == R.id.btn_meal_fri) {
                    dayKey = "금";
                } else if (id == R.id.btn_meal_sat) {
                    dayKey = "토";
                } else if (id == R.id.btn_meal_sun) {
                    dayKey = "일";
                } else {
                    dayKey = "월";
                }

                showMeal(dayKey);
            }
        };

        btnMon.setOnClickListener(dayClickListener);
        btnTue.setOnClickListener(dayClickListener);
        btnWed.setOnClickListener(dayClickListener);
        btnThu.setOnClickListener(dayClickListener);
        btnFri.setOnClickListener(dayClickListener);
        btnSat.setOnClickListener(dayClickListener);
        btnSun.setOnClickListener(dayClickListener);
    }

    // ---------- 선택된 요일의 식단을 화면에 띄우는 메서드 ----------
    private void showMeal(String dayKey) {
        DayMeal meal = currentPlan.get(dayKey);

        if (meal == null) {
            tvSelectedDay.setText(dayKey + "요일 식단 정보가 없습니다.");
            tvBreakfast.setText("");
            tvLunch.setText("");
            tvDinner.setText("");
            tvSnack.setText("");
            tvTotal.setText("");
            return;
        }

        tvSelectedDay.setText("목표: " + goal + " / " + dayKey + "요일 식단");

        tvBreakfast.setText(meal.breakfast);
        tvLunch.setText(meal.lunch);
        tvDinner.setText(meal.dinner);
        tvSnack.setText(meal.snack);
        tvTotal.setText(meal.totalKcal);
    }

    // ----------------------- 다이어트 식단 -----------------------
    private void initDietPlan() {
        dietPlan.put("월", new DayMeal(
                "고구마 토스트\n250kcal",
                "닭가슴살 샐러드\n350kcal",
                "볶음국수\n400kcal",
                "요거트 1컵\n150kcal",
                "합계 1150kcal"
        ));

        dietPlan.put("화", new DayMeal(
                "오트밀 + 과일\n250kcal",
                "샐러드 샌드위치\n350kcal",
                "수비드 스테이크\n450kcal",
                "과일 스무디\n250kcal",
                "합계 1300kcal"
        ));

        dietPlan.put("수", new DayMeal(
                "닭가슴살 샐러드\n300kcal",
                "콩나물국 + 밥\n400kcal",
                "생선구이\n350kcal",
                "견과류 한 줌\n150kcal",
                "합계 1200kcal"
        ));

        dietPlan.put("목", new DayMeal(
                "고구마 스프\n200kcal",
                "참치 샐러드\n350kcal",
                "오믈렛\n350kcal",
                "닭가슴살 구이\n350kcal",
                "합계 1250kcal"
        ));

        dietPlan.put("금", new DayMeal(
                "요거트 + 과일\n250kcal",
                "생선구이 + 샐러드\n450kcal",
                "리조또\n300kcal",
                "과일 1접시\n150kcal",
                "합계 1150kcal"
        ));

        // 토요일 / 일요일
        dietPlan.put("토", new DayMeal(
                "스크램블 에그\n300kcal",
                "계란 샐러드\n350kcal",
                "새우볶음밥\n400kcal",
                "견과류\n200kcal",
                "합계 1250kcal"
        ));

        dietPlan.put("일", new DayMeal(
                "과일 샐러드\n300kcal",
                "닭가슴살 구이\n400kcal",
                "국수\n350kcal",
                "닭가슴살 샐러드\n350kcal",
                "합계 1400kcal"
        ));
    }

    // ----------------------- 벌크업 식단 -----------------------
    private void initBulkPlan() {
        bulkPlan.put("월", new DayMeal(
                "오트밀 + 바나나 + 우유\n750kcal",
                "닭가슴살 비빔밥 + 두부\n900kcal",
                "소고기 스테이크 + 파스타\n1100kcal",
                "단백질 쉐이크 + 견과류\n550kcal",
                "합계 3300kcal"
        ));

        bulkPlan.put("화", new DayMeal(
                "소고기죽 + 치즈\n700kcal",
                "연어덮밥 + 계란추가\n1050kcal",
                "삼겹살 250g + 밥 + 쌈채소\n1150kcal",
                "고구마 2개 + 요거트\n450kcal",
                "합계 3350kcal"
        ));

        bulkPlan.put("수", new DayMeal(
                "닭가슴살 샌드위치 + 우유\n750kcal",
                "불고기덮밥 + 계란\n750kcal",
                "닭다리 구이 300g + 밥\n1050kcal",
                "바나나 + 프로틴바\n500kcal",
                "합계 3250kcal"
        ));

        bulkPlan.put("목", new DayMeal(
                "시리얼 + 우유 + 계란3개\n800kcal",
                "치킨 브레스트 파스타\n1100kcal",
                "연어구이 + 감자 + 샐러드\n1000kcal",
                "초코우유 + 견과류\n500kcal",
                "합계 3400kcal"
        ));

        bulkPlan.put("금", new DayMeal(
                "잡곡밥 + 삶은 계란\n800kcal",
                "돈까스 + 밥 + 샐러드\n1100kcal",
                "제육볶음 + 밥 + 미역국\n1000kcal",
                "단백질 쉐이크 + 바나나\n400kcal",
                "합계 3300kcal"
        ));

        bulkPlan.put("토", new DayMeal(
                "소고기 오므라이스\n800kcal",
                "치킨 플레이트 + 밥 + 치즈추가\n1200kcal",
                "고등어구이 + 밥 + 된장찌개\n1000kcal",
                "프로틴바 + 우유\n500kcal",
                "합계 3500kcal"
        ));

        bulkPlan.put("일", new DayMeal(
                "단백질 팬케이크 + 과일\n800kcal",
                "카레라이스 + 계란\n1100kcal",
                "닭가슴살 샐러드 + 파스타\n1000kcal",
                "요거트 + 견과\n300kcal",
                "합계 3200kcal"
        ));
    }

    // ----------------------- 유지 식단 -----------------------
    private void initMaintainPlan() {
        maintainPlan.put("월", new DayMeal(
                "시리얼 + 우유\n400kcal",
                "닭가슴살 샐러드\n450kcal",
                "잡곡밥 + 생선구이\n550kcal",
                "요거트\n150kcal",
                "합계 1550kcal"
        ));

        maintainPlan.put("화", new DayMeal(
                "토스트 + 계란\n400kcal",
                "비빔밥(밥 적게)\n550kcal",
                "치킨 스테이크 + 샐러드\n600kcal",
                "과일\n150kcal",
                "합계 1700kcal"
        ));

        maintainPlan.put("수", new DayMeal(
                "오트밀 + 과일\n450kcal",
                "연어샐러드 + 빵\n550kcal",
                "돼지고기 안심구이 + 밥\n600kcal",
                "견과류\n150kcal",
                "합계 1750kcal"
        ));

        maintainPlan.put("목", new DayMeal(
                "현미밥 + 계란후라이\n450kcal",
                "생선구이 정식\n600kcal",
                "파스타 + 샐러드\n600kcal",
                "프로틴바\n200kcal",
                "합계 1850kcal"
        ));

        maintainPlan.put("금", new DayMeal(
                "과일요거트볼\n400kcal",
                "닭가슴살 덮밥\n600kcal",
                "김치찌개 + 밥\n600kcal",
                "우유 + 과자 조금\n200kcal",
                "합계 1800kcal"
        ));

        maintainPlan.put("토", new DayMeal(
                "스크램블 에그 + 토스트\n450kcal",
                "연어덮밥\n650kcal",
                "소고기 국거리 + 밥\n600kcal",
                "견과류\n150kcal",
                "합계 1850kcal"
        ));

        maintainPlan.put("일", new DayMeal(
                "팬케이크(작게) + 우유\n500kcal",
                "불고기 브라운라이스\n650kcal",
                "닭가슴살 스테이크 + 샐러드\n600kcal",
                "과일\n150kcal",
                "합계 1900kcal"
        ));
    }
}
