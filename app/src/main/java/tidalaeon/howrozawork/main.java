package tidalaeon.howrozawork;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class main extends Activity {
    public CalendarDay correctionDay; //Это первый рабочий день отсчета

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView nowTextView = findViewById(R.id.nowTextView);
        setContentView(R.layout.activity_main);
        Create();
    }

    public void Create() { //Функция Create() рисует на календаре текущую ситуацию.
        CalendarDay.today();
        MaterialCalendarView calendarView = findViewById(R.id.calendarView);
        ArrayList<CalendarDay> wDays = createCal();
        for (CalendarDay day : wDays) {
            calendarView.setDateSelected(day, true);
        }
        TextView nowTextView = findViewById(R.id.nowTextView);
        nowTextView.setText("" + CalendarDay.today());
    }

    private ArrayList<CalendarDay> createCal() {
        ArrayList<CalendarDay> wDays = new ArrayList();
        int fday = 3;
        for (int y = 2018; y <= 2020; y++) {
            for (int m = 0; m <= 11; m++) { //Обертка по месяцам (пока на август-сентябрь)
                for (int d = 1; d <= checkMonth(CalendarDay.from(y, m, 5)); d++) { //Обертка по дням
                    if ((d + fday) % 6 < 3)
                        wDays.add(CalendarDay.from(y, m, d));
                    if (d == checkMonth(CalendarDay.from(y, m, d))) fday = (d + fday) % 6;
                }
            }
        }
        return wDays;
    }

    private int checkMonth(CalendarDay calDay) {
        int maxdate = 0;
        if (calDay.getMonth() == 0 || calDay.getMonth() == 2 || calDay.getMonth() == 4 || calDay.getMonth() == 6 || calDay.getMonth() == 7 || calDay.getMonth() == 9 || calDay.getMonth() == 11)
            maxdate = 31;
        if (calDay.getMonth() == 3 || calDay.getMonth() == 5 || calDay.getMonth() == 8 || calDay.getMonth() == 10)
            maxdate = 30;
        if (calDay.getMonth() == 1) {
            if (calDay.getYear() % 4 == 0) {
                if (calDay.getYear() % 100 != 0 || calDay.getYear() % 400 == 0) maxdate = 29;
                else maxdate = 28;
            } else maxdate = 28;
        }
        return maxdate;
    }

    private static String GetNow() { //Функция GetNow() возвращает текущую дату в красивом виде типа "Сегодня 25.04.1996, вт"
        Date now = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy', 'E");
        return ("Сегодня " + formatForDateNow.format(now));
    }

    /*
    Функция InitCorrectEquations() получив сигнал по нажатию кнопки инициализирует выполнение метода CorrectEquations()
    */
    public void InitCorrectEquations(View view) throws IOException {
        // CorrectEquations(view);
    }

}

    /*
    Функция CorrectEquations() позволяет поменять корректирующую переменную путем выбора следующего выходного на календаре

    public void CorrectEquations(View view) throws IOException {
        //Изменить цвет календаря
        CalendarView cView = (CalendarView) findViewById(R.id.calendarView);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date now = new Date();
        String date1 = format.format(now);
        String date2 = format.format(cView.getDate());
        TextView nowTextView = findViewById(R.id.nowTextView);
        CalendarView calendarView = findViewById(R.id.calendarView);
        Date dateOne = null;
        Date dateTwo = null;
        try {
            dateOne = format.parse(date1);
            dateTwo = format.parse(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Количество дней между датами в миллисекундах
        long difference = dateOne.getTime() - dateTwo.getTime();
        // Перевод количества дней между датами из миллисекунд в дни
        int days =  (int)(difference / (24 * 60 * 60 * 1000)); // миллисекунды / (24ч * 60мин * 60сек * 1000мс)
        nowTextView.setText("" + days + " дней");*/
