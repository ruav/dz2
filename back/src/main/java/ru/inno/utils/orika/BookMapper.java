package ru.inno.utils.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.inno.entity.BookEntity;
import ru.inno.pojo.Book;

/**
 * Created by ruav on 19.01.17.
 */
@Component
@Qualifier("bookMapper")
//@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BookMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {

        factory.classMap(Book.class, BookEntity.class)
                .field("id","id")
                .field("title","title")
                .field("author","author")
                .field("yearPublishing","yearPublishing")
                .field("publisher","publisher")
                .field("version","version")
                .byDefault()
                .register();
    }


}
