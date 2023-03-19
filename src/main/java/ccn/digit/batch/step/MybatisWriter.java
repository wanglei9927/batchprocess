package ccn.digit.batch.step;

import ccn.digit.batch.entity.Update;
import ccn.digit.batch.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public MyBatisBatchItemWriter<Update> writer() throws Exception {
        return new MyBatisBatchItemWriterBuilder<Update>()
                .sqlSessionFactory(sqlSessionFactory)
                .statementId("ccn.digit.batch.mapper.UserMapper.updateUser")
                .build();
    }
}
