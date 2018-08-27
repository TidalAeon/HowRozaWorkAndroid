package tidalaeon.howrozawork;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class main extends Activity {
    public CalendarDay correctionDay; //Это первый рабочий день отсчета

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView nowTextView = findViewById(R.id.nowTextView);
        setContentView(R.layout.activity_main);
        correctionDay = CalendarDay.from(2018, 7, 1);
    }

    public void Create(View view) { //Функция Create() рисует на календаре текущую ситуацию.
        CalendarDay.today();
        MaterialCalendarView calendarView = findViewById(R.id.calendarView);
        ArrayList<CalendarDay> wDays = createCal();
        for (CalendarDay day : wDays) calendarView.setDateSelected(day, true);
        TextView nowTextView = findViewById(R.id.nowTextView);
        nowTextView.setText("" + CalendarDay.today());
        //calendarView.selectRange(CalendarDay.from(2018, 7, 15), CalendarDay.from(2018, 7, 20));
    }

    private ArrayList<CalendarDay> createCal() {
        ArrayList<CalendarDay> wDays = new ArrayList();
        //wDays.add(correctionDay);
        for (int i = correctionDay.getDay(); i < checkMonth(correctionDay); i++) {
            if ((i - correctionDay.getDay()) % 6 < 3)
                wDays.add(CalendarDay.from(2018, correctionDay.getMonth(), i));
        }
        return wDays;
    }

    private int checkMonth(CalendarDay calDay) {
        int maxdate = 31;
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

    public void main(String[] args) throws IOException {

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
        nowTextView.setText("" + days + " дней");
