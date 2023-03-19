package ccn.digit.batch.mapper;

import ccn.digit.batch.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author heibao
 * @date 2023年03月19日 21:14
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(@Param("id") String id);


    @Update("update user set email = #{value} where name = #{name}")
    int updateUser(@Param("name") String name,@Param("value") String value);
}
