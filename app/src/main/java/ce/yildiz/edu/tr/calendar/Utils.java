package ce.yildiz.edu.tr.calendar;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static final int MAX_CALENDAR_DAYS = 42;

    public static final String DAILY = "Repetir Diariamente";
    public static final String WEEKLY = "Repetir Semanalmente";
    public static final String MONTHLY = "Repetir Mensualmente";
    public static final String YEARLY = "Repeatir Anualmente";

    public static String CURRENT_FILTER = "Hoy";
    public static final String TODAY = "Hoy";
    public static final String NEXT_7_DAYS = "Próximos 7 días";
    public static final String NEXT_30_DAYS = "Próximos 30 días";
    public static final String THIS_YEAR = "Este año";

    public enum AppTheme {
        INDIGO,
        DARK,
    }

    public static final SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
    public static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    public static final SimpleDateFormat eventDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.JAPANESE);

    public static Date convertStringToDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.JAPANESE);
        Date aDate = null;
        try {
            aDate = simpleDateFormat.parse(date);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return aDate;
    }

    @SuppressLint("ResourceType")
    public static ArrayList<String> getColors(Context context) {
        ArrayList<String> colors = new ArrayList<>();
        colors.add(context.getResources().getString(R.color.rojo));
        colors.add(context.getResources().getString(R.color.verde));
        colors.add(context.getResources().getString(R.color.azul));
        colors.add(context.getResources().getString(R.color.amarillo));
        colors.add(context.getResources().getString(R.color.naranja));
        colors.add(context.getResources().getString(R.color.morado));
        colors.add(context.getResources().getString(R.color.marron));
        return colors;
    }

}
