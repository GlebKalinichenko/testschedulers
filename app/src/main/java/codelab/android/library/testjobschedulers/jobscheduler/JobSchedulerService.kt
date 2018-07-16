package codelab.android.library.testjobschedulers.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.widget.Toast

class JobSchedulerService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        params?.let {
            val param = it.extras.getString("message")
            // finish job execution
            jobFinished(it, true)
        }

        Toast.makeText(applicationContext, "OLOLO", Toast.LENGTH_LONG).show()

        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }
}