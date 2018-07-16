package codelab.android.library.testjobschedulers.evernote

import com.evernote.android.job.Job
import com.evernote.android.job.JobCreator

class EvernoteJobCreator : JobCreator {

    override fun create(tag: String): Job? {
        when(tag) {
            "EvernoteJob" -> return EvernoteJob()
            else -> return null
        }
    }
}