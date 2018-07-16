package codelab.android.library.testjobschedulers

import android.app.Application
import codelab.android.library.testjobschedulers.evernote.EvernoteJobCreator
import com.evernote.android.job.JobManager

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        JobManager.create(this).addJobCreator(EvernoteJobCreator())
    }
}