package com.miempresa.usowidget_tarea;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.miempresa.usowidget_tarea.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
public class MiWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // Actualiza la interfaz del widget aquí
        // Puedes obtener acceso a los TextViews lblHora y lblFecha usando los appWidgetIds

        // Crea una instancia de RemoteViews para acceder a los elementos del widget
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_main);

        // Actualiza el texto de lblHora y lblFecha
        views.setTextViewText(R.id.lblHora, obtenerHoraActual());
        views.setTextViewText(R.id.lblFecha, obtenerFechaActual());

        // Actualiza el widget con las nuevas vistas
        appWidgetManager.updateAppWidget(appWidgetIds, views);
    }

    private String obtenerHoraActual() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a", Locale.getDefault());
        return sdf.format(calendar.getTime());
    }

    private String obtenerFechaActual() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM", Locale.getDefault());
        return sdf.format(calendar.getTime());
    }
}

