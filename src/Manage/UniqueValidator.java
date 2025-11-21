package Manage;

import core.*;
import java.util.ArrayList;

public class UniqueValidator {

    public static boolean isUniqueStandId(long id, ArrayList<Stand> stands) {
        for (Stand stand : stands) {
            if (stand.getId() == id) {
                return false;
            }
        }
        return true;
    }
    public static boolean isUniquePersonId(long id, ArrayList<? extends Person> persons) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return false;
            }
        }
        return true;
    }
    public static boolean isUniqueNIT(String nit, ArrayList<Publisher> publishers) {
        for (Publisher publisher : publishers) {
            if (publisher.getNit().equals(nit)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUniqueISBN(String isbn, ArrayList<Book> books) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return false;
            }
        }
        return true;
    }
}