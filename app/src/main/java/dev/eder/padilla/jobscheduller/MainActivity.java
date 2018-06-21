package dev.eder.padilla.jobscheduller;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scheduleJob(View view){
        ComponentName componentName = new ComponentName(this,ExampleJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(123,componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(15*60*1000)
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(jobInfo);
        if (resultCode == JobScheduler.RESULT_SUCCESS){
            Util.log(TAG,"Job scheduled success");
        } else{
            Util.log(TAG,"|xXx| Job scheduling failed |xXx|");
        }
    }

    public void cancelJob(View view){
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Util.log(TAG,"job cancelled");
    }
}
