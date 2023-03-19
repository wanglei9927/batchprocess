package ccn.digit.batch.step;

import ccn.digit.batch.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heibao
 * @date 2023年03月19日 20:59
 */
@Configuration
@RequiredArgsConstructor
public class MybatisReader {

    private final SqlSessionFactory sqlSessionFactory;

    @Bean("reader")
    @StepScope
    public MyBatisCursorItemReader<User> reader(@Value("#{jobParameters[id]}") String id){
        MyBatisCursorItemReader<User> reader = new MyBatisCursorItemReader<User>();
        //这个map是mybatis的参数
        Map<String , Object> map = new HashMap<String , Object>();
        map.put("id" , id);
        reader.setQueryId("ccn.digit.batch.mapper.UserMapper.findById");
        reader.setSqlSessionFactory(sqlSessionFactory);
        reader.setParameterValues(map);
        return reader;

    }


}
