package Manage;

import core.*;
import java.util.ArrayList;

public class ExistenceValidator {
    
    public static boolean managerExists(long id, ArrayList<Manager> managers) {
        for (Manager manager : managers) {
            if (manager.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean authorExists(long id, ArrayList<Author> authors) {
        for (Author author : authors) {
            if (author.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean publisherExists(String nit, ArrayList<Publisher> publishers) {
        for (Publisher publisher : publishers) {
            if (publisher.getNit().equals(nit)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean narratorExists(long id, ArrayList<Narrator> narrators) {
        for (Narrator narrator : narrators) {
            if (narrator.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean standExists(long id, ArrayList<Stand> stands) {
        for (Stand stand : stands) {
            if (stand.getId() == id) {
                return true;
            }
        }
        return false;
    }
}