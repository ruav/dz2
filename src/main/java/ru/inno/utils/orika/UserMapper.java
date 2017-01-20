package ru.inno.utils.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.inno.entity.UserEntity;
import ru.inno.pojo.User;

/**
 * Created by ruav on 19.01.17.
 */
@Component
@Qualifier("userMapper")
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class UserMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {

        factory.classMap(User.class, UserEntity.class)
                .field("id","id")
                .field("login","login")
                .field("password","password")
                .field("admin","admin")
                .field("firstName","firstName")
                .field("lastName","lastName")
                .field("version","version")
                .byDefault()
                .register();
    }


}
