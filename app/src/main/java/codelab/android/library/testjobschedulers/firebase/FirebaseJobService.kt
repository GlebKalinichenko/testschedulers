package codelab.android.library.testjobschedulers.firebase

import com.firebase.jobdispatcher.JobParameters

class FirebaseJobService : com.firebase.jobdispatcher.JobService() {

    override fun onStopJob(job: JobParameters?): Boolean {
        return false
    }

    override fun onStartJob(job: JobParameters?): Boolean {
        return false
    }
}