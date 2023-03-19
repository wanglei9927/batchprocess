package ccn.digit.batch.config;

import ccn.digit.batch.listener.JobCompletionListener;
import ccn.digit.batch.step.Processor;
import ccn.digit.batch.step.Reader;
import ccn.digit.batch.step.Writer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    public final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer()).listener(listener())// 监听
                .flow(orderStep1()).end().build(); // 创建步骤1
    }

    @Bean
    // 步骤1 bean 先读再写
    public Step orderStep1() {
        return stepBuilderFactory.get("orderStep1").<String, String> chunk(1)
                .reader(new Reader()).processor(new Processor())    // 读取。处理
                .writer(new Writer()).build();  // 最后写
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionListener(); // 创建监听
    }

}
