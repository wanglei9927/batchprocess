package ccn.digit.batch.step;

import ccn.digit.batch.entity.User;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
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

    private final DynamicRoutingDataSource master;

    @Bean(name = "batchSqlSessionFactory")
    @Primary
    public SqlSessionFactory batchSqlSessionFactory()
            {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(master.getDataSource("master"));
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(Master1DataSourceConfig.MAPPER_LOCATION));
                try {
                    return sessionFactory.getObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }


    @Bean("reader")
    @StepScope
    public MyBatisCursorItemReader<User> reader(@Value("#{jobParameters[id]}") String id){
        MyBatisCursorItemReader<User> reader = new MyBatisCursorItemReader<User>();


        //这个map是mybatis的参数
        Map<String , Object> map = new HashMap<String , Object>();
        map.put("id" , id);
        reader.setQueryId("ccn.digit.batch.mapper.UserMapper.findById");
        reader.setSqlSessionFactory(batchSqlSessionFactory());
        reader.setParameterValues(map);
        return reader;

    }


}
