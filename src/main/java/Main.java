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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Book book = new Book();

        BookDao bookDao = new BookDaoImpl();


        try {
            book = bookDao.getBookById(1);
        } catch (MyException e) {
            e.printStackTrace();
        }

        System.out.println(book.toString());
    }
}
