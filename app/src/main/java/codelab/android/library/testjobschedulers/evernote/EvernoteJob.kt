package codelab.android.library.testjobschedulers.evernote

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import codelab.android.library.testjobschedulers.R
import com.evernote.android.job.Job
import com.evernote.android.job.JobRequest
import java.util.concurrent.TimeUnit
import com.evernote.android.job.util.support.PersistableBundleCompat

class EvernoteJob : Job() {

    companion object {
        fun scheduleJob(value: String) {
            val extras = PersistableBundleCompat()
            extras.putString("bundle_key", value)

            JobRequest.Builder("EvernoteJob")
                    // interval
                    .setPeriodic(TimeUnit.MINUTES.toMillis(15))
                    // update current tasks
                    .setUpdateCurrent(true)
                    // set charging mode
                    .setRequiresCharging(true)
                    // set idle model
                    .setRequiresDeviceIdle(false)
                    // set bundle params
                    .setExtras(extras)
                    .build()
                    .schedule()
        }
    }

    override fun onRunJob(params: Params): Result {
        showNotification("Title Evernote", params.extras.getString("bundle_key", ""))
        return Result.SUCCESS
    }

    private fun showNotification(title: String, message: String) {
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