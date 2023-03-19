package ccn.digit.batch.step;

import ccn.digit.batch.entity.Update;
import ccn.digit.batch.entity.User;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author heibao
 * @date 2023年03月19日 23:34
 */
public class MybatisProcessor implements ItemProcessor<User,Update> {
    @Override
    public Update process(User user) throws Exception {
        Update update = new Update();
        update.setName(user.getName());
        update.setValue(user.getName()+"@gmail.com");
        return update;
    }
}
