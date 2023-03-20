package ccn.digit.batch.step;

import ccn.digit.batch.entity.Update;
import ccn.digit.batch.entity.User;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author heibao
 * @date 2023年03月19日 22:31
 */
@Configuration
@RequiredArgsConstructor
public class MybatisWriter {


    private final SqlSessionFactory sqlSessionFactory;

    @Bean
    public MyBatisBatchItemWriter<Update> writer(@Qualifier("batchSqlSessionFactory") SqlSessionFactory batchSqlSessionFactory) throws Exception {
        System.out.println(sqlSessionFactory == batchSqlSessionFactory);
        return new MyBatisBatchItemWriterBuilder<Update>()
                .sqlSessionFactory(batchSqlSessionFactory)
                .statementId("ccn.digit.batch.mapper.UserMapper.updateUser")
                .build();
    }
}
