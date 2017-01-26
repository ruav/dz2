package ru.inno.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.inno.entity.BookEntity;

import java.util.List;

/**
 * Created by ruav on 19.01.17.
 */

@Repository
public interface BookRepository  extends CrudRepository<BookEntity, Integer>{

    BookEntity findById(Integer id);
    List<BookEntity> findByTitle(String title);

    List<BookEntity> findByPublisher(String publisher);
    List<BookEntity> findByAuthor(String author);
    List<BookEntity> findByYearPublishing(int year);

    @Transactional
    long deleteById(int id);

//    List<BookEntity> findAll();
//    void removeById(int id);
//    void add(BookEntity book);
//    void update(BookEntity bookEntity);


}
