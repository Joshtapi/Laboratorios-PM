package com.miempresa.usowidgetv4

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Implementation of App Widget functionality.
 */
const val control_widget = "control_widget"
class mi_widget : AppWidgetProvider() {
    fun actualizarWidget(context: Context,appWidgetManager: AppWidgetManager,widgetId: Int){
        val datos = context.getSharedPreferences("DatosWidget",Context.MODE_PRIVATE)
        val mensaje = datos.getString("mensaje","Mensaje Recibido:")
        val controles = RemoteViews(context.packageName,R.layout.mi_widget)
        controles.setTextViewText(R.id.lblMensaje,mensaje)

        val sdfDate = SimpleDateFormat("HH:mm:ss a")
        val now = Date()
        val hora = sdfDate.format(now)
        controles.setTextViewText(R.id.lblHora,hora)

        val botonwidget = Intent(context,mi_widget::class.java)
        botonwidget.action= control_widget
        botonwidget.putExtra("appWidgetId",widgetId)

        val clicenwidget = Intent(context,DatosWidget::class.java)
        val widgetesperando = PendingIntent.getActivity(
            context,
            widgetId,
            clicenwidget,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        val botonespera = PendingIntent.getBroadcast(
            context,
            0,
            botonwidget,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        controles.setOnClickPendingIntent(R.id.frmWidget, widgetesperando)

        appWidgetManager.updateAppWidget(widgetId,controles)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
            actualizarWidget(context,appWidgetManager,appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (control_widget == intent?.action){
            val widgetId = intent.getIntExtra("appWidgetId",0)
            actualizarWidget(context!!,AppWidgetManager.getInstance(context),widgetId)
        }
        super.onReceive(context, intent)
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.mi_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
