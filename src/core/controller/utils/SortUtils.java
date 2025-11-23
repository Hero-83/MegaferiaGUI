package core.controller.utils;

import core.model.*;
import java.util.ArrayList;
public class SortUtils {
    public static ArrayList<Stand> getSortedStandsById(ArrayList<Stand> stands) {
        ArrayList<Stand> sortedList = new ArrayList<>(stands);
        sortedList.sort((s1, s2) -> Long.compare(s1.getId(), s2.getId()));
        return sortedList;
    }
    
    public static ArrayList<Person> getSortedPersonsById(ArrayList<? extends Person> persons) {
        ArrayList<Person> sortedList = new ArrayList<>(persons);
        sortedList.sort((p1, p2) -> Long.compare(p1.getId(), p2.getId()));
        return sortedList;
    }
    
    public static ArrayList<Publisher> getSortedPublishersByNIT(ArrayList<Publisher> publishers) {
        ArrayList<Publisher> sortedList = new ArrayList<>(publishers);
        sortedList.sort((p1, p2) -> p1.getNit().compareTo(p2.getNit()));
        return sortedList;
    }
    public static ArrayList<Book> getSortedBooksByISBN(ArrayList<Book> books) {
        ArrayList<Book> sortedList = new ArrayList<>(books);
        sortedList.sort((b1, b2) -> b1.getIsbn().compareTo(b2.getIsbn()));
        return sortedList;
    }
    
    public static ArrayList<Author> getSortedAuthorsByBookCount(ArrayList<Author> authors) {
        ArrayList<Author> sortedList = new ArrayList<>(authors);
        
        for (int i = 0; i < sortedList.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < sortedList.size(); j++) {
                if (sortedList.get(j).getBookQuantity() > sortedList.get(maxIndex).getBookQuantity()) {
                    maxIndex = j;
                }
            }
            Author temp = sortedList.get(i);
            sortedList.set(i, sortedList.get(maxIndex));
            sortedList.set(maxIndex, temp);
        }
        
        return sortedList;
    }
}