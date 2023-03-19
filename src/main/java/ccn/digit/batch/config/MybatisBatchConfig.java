package ccn.digit.batch.config;

import ccn.digit.batch.entity.Update;
import ccn.digit.batch.entity.User;
import ccn.digit.batch.step.MybatisProcessor;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author heibao
 * @date 2023年03月19日 22:43
 */
@Configuration
@RequiredArgsConstructor
public class MybatisBatchConfig {

    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;
    private final MyBatisCursorItemReader<User> myBatisCursorItemReader;
    private final MyBatisBatchItemWriter<Update> myBatisBatchItemWriter;

    @Bean
    public Job processMybatisJob(JobExecutionListener listener) {
        return jobBuilderFactory.get("processMybatisJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(orderStep2())
                .end()
                .build();
    }

    @Bean
    public Step orderStep2() {
        return stepBuilderFactory.get("orderStep2")
                .<User, Update> chunk(10)
                .reader(myBatisCursorItemReader)    // 读取。处理
                .processor(new MybatisProcessor())
                .writer(myBatisBatchItemWriter)
                .build();  // 最后写
    }

}
