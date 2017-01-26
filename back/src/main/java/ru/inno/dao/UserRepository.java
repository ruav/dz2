package ru.inno.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.inno.entity.UserEntity;

/**
 * Created by ruav on 19.01.17.
 */

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{

    UserEntity findById(Integer id);
    UserEntity findByLogin(String login);

    long removeById(int id);


//    List<BookEntity> findAll();
//    void removeById(int id);
//    void add(BookEntity book);
//    void update(BookEntity bookEntity);


}
