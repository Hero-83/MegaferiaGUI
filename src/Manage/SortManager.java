package Manage;

import core.*;
import java.util.ArrayList;
public class SortManager {
    public static ArrayList<Stand> getSortedStandsById(ArrayList<Stand> stands) {
        ArrayList<Stand> sortedList = new ArrayList<>(stands);
        
        for (int i = 0; i < sortedList.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sortedList.size(); j++) {
                if (sortedList.get(j).getId() < sortedList.get(minIndex).getId()) {
                    minIndex = j;
                }
            }
            Stand temp = sortedList.get(i);
            sortedList.set(i, sortedList.get(minIndex));
            sortedList.set(minIndex, temp);
        }
        
        return sortedList;
    
    public static ArrayList<? extends Person> getSortedPersonsById(ArrayList<? extends Person> persons) {
        ArrayList<Person> sortedList = new ArrayList<>(persons);
        
        for (int i = 0; i < sortedList.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sortedList.size(); j++) {
                if (sortedList.get(j).getId() < sortedList.get(minIndex).getId()) {
                    minIndex = j;
                }
            }
            Person temp = sortedList.get(i);
            sortedList.set(i, sortedList.get(minIndex));
            sortedList.set(minIndex, temp);
        }
        
        return sortedList;
    }
    
    public static ArrayList<Publisher> getSortedPublishersByNIT(ArrayList<Publisher> publishers) {
        ArrayList<Publisher> sortedList = new ArrayList<>(publishers);
        
        for (int i = 0; i < sortedList.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sortedList.size(); j++) {
                if (sortedList.get(j).getNit().compareTo(sortedList.get(minIndex).getNit()) < 0) {
                    minIndex = j;
                }
            }
            Publisher temp = sortedList.get(i);
            sortedList.set(i, sortedList.get(minIndex));
            sortedList.set(minIndex, temp);
        }
        
        return sortedList;
    }
    public static ArrayList<Book> getSortedBooksByISBN(ArrayList<Book> books) {
        ArrayList<Book> sortedList = new ArrayList<>(books);
        
        for (int i = 0; i < sortedList.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sortedList.size(); j++) {
                if (sortedList.get(j).getIsbn().compareTo(sortedList.get(minIndex).getIsbn()) < 0) {
                    minIndex = j;
                }
            }
            Book temp = sortedList.get(i);
            sortedList.set(i, sortedList.get(minIndex));
            sortedList.set(minIndex, temp);
        }
        
        return sortedList;
    }
}