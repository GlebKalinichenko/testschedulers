package codelab.android.library.testjobschedulers

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import codelab.android.library.testjobschedulers.evernote.EvernoteJob
import codelab.android.library.testjobschedulers.jobscheduler.JobSchedulerService
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val jobSchedulerId = 1
    lateinit private var serviceComponent: ComponentName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListener()
    }

    private fun initListener() {
        button_job_scheduler.setOnClickListener { executeJobScheduler() }

        button_evernote.setOnClickListener { executeEvernote() }
    }

    private fun executeEvernote() {
        EvernoteJob.scheduleJob("bundle_value")
    }

    private fun executeJobScheduler() {
        serviceComponent = ComponentName(this, JobSchedulerService::class.java)

        // prepare params bundle inside job service
        var bundle = PersistableBundle()
        bundle.putString("message", "Hi Android Job Scheduler!!!")

        var jobBuilder = JobInfo.Builder(jobSchedulerId, serviceComponent)
        // set min delay for execute don't use with periodic
        //jobBuilder.setMinimumLatency(TimeUnit.SECONDS.toMillis(3))
        // interval
        jobBuilder.setPeriodic(TimeUnit.MINUTES.toMillis(15))
        // handle reboot device
        jobBuilder.setPersisted(true)
        // send some bundle between jobs
        jobBuilder.setExtras(bundle)
        // set type of network
        jobBuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        // set is need to execute job when device in idle
        jobBuilder.setRequiresDeviceIdle(true)

        // Schedule job
        val job = jobBuilder.build()
        (getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler).schedule(job)
    }
}
