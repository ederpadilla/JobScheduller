package dev.eder.padilla.jobscheduller;

import android.app.job.JobParameters;
import android.app.job.JobService;

public class ExampleJobService extends JobService {

    private static String TAG  = ExampleJobService.class.getSimpleName();

    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Util.log(TAG,"TAG,On start Job !!!!");
        backGroundWork(jobParameters);
        return true;
    }

    private void backGroundWork(final JobParameters jobParameters) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i<12; i++){
                    Util.log(TAG,"Valor hell yeah "+i);
                    if (jobCancelled){
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Util.log(TAG,"Job finished");
                jobFinished(jobParameters,false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Util.log(TAG,"On Job cancelled");
        jobCancelled = true;
        return true;
    }

}
