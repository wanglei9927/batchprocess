package ccn.digit.batch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class JobCompletionListener extends JobExecutionListenerSupport {
    @Override
    public void afterJob(JobExecution jobExecution) {
        // 项目完成以后调用
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            System.out.println("项目已经完成");
        }
    }
}
