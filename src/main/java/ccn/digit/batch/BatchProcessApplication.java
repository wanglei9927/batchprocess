package ccn.digit.batch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author heibao
 * @date 2023年03月19日 15:36
 */
@SpringBootApplication
@EnableBatchProcessing
@MapperScan(basePackages = {"ccn.digit.batch.mapper"})
public class BatchProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchProcessApplication.class, args);
    }

}
