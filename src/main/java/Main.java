import ru.inno.dao.BookDao;
import ru.inno.dao.BookDaoImpl;
import ru.inno.dao.DBConnection;
import ru.inno.pojo.Book;
import ru.inno.utils.MyException;

import java.sql.SQLException;

/**
 * @author Alexander Rudnev
 */
public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, MyException {
        Book book;

        BookDao bookDao = new BookDaoImpl(DBConnection.getConnection());


        book = bookDao.getById(1);

        System.out.println(book.toString());
    }
}
