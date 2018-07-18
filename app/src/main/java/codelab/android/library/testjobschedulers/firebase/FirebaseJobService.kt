package codelab.android.library.testjobschedulers.firebase

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import codelab.android.library.testjobschedulers.R
import com.firebase.jobdispatcher.JobParameters

class FirebaseJobService : com.firebase.jobdispatcher.JobService() {

    override fun onStopJob(job: JobParameters?): Boolean {
        return false
    }

    override fun onStartJob(job: JobParameters?): Boolean {
        showNotification(context = applicationContext, title = "Title firebase", message = "")
        return false
    }

    private fun showNotification(context: Context, title: String, message: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var notification: Notification? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("Fetched", "Channel", importance)
            notificationManager.createNotificationChannel(channel)

            notification = Notification.Builder(context, "Fetched")
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .build()
        } else {
            notification = NotificationCompat.Builder(context, "Fetched")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true).build()
        }

        notificationManager.notify(1, notification)
    }
}