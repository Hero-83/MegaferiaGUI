/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author keinerthd
 */
public class MegaferiaDataStore {

    private static MegaferiaDataStore instance = new MegaferiaDataStore();

    public static MegaferiaDataStore getInstance() {
        return instance;
    }

    // Listas internas
    private final List<Stand> stands = new ArrayList<>();
    private final List<Person> people = new ArrayList<>();
    private final List<Publisher> publishers = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();

    private MegaferiaDataStore() {
    }

    // ======== STANDS ========
    public void addStand(Stand stand) {
        stands.add(stand);
    }

    public boolean existsStandById(long id) {
        for (Stand s : stands) {
            if (s.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public List<Stand> getStands() {
        return new ArrayList<>(stands);
    }

  

    // ======== PERSONAS ========
    public boolean existsPersonById(long id) {
        for (Person p : people) {
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void addPerson(Person p) {
        people.add(p);
    }

    public Person findPersonById(long id) {
        for (Person p : people) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Person> getPeople() {
        return new ArrayList<>(people);
    }

    // ======== EDITORIALES ========
    public boolean existsPublisherByNit(String nit) {
        for (Publisher e : publishers) {
            if (e.getNit().equals(nit)) {
                return true;
            }
        }
        return false;
    }

    public void addPublisher(Publisher e) {
        publishers.add(e);
    }

    public Publisher findPublisherByNit(String nit) {
        for (Publisher e : publishers) {
            if (e.getNit().equals(nit)) {
                return e;
            }
        }
        return null;
    }

    public List<Publisher> getPublishers() {
        return new ArrayList<>(publishers);
    }

    // ======== LIBROS ========
    public boolean existsBookByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    public void addBook(Book b) {
        books.add(b);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
}
