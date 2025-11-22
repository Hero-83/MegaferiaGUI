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
    
    List<Stand> getRawStands() {  // solo para controladores
       return stands;
    }
    public List<Stand> getStandsOrderedById() {

        List<Stand> ordered = new ArrayList<>(stands);

        for (int i = 0; i < ordered.size(); i++) {
            for (int j = i + 1; j < ordered.size(); j++) {
                if (ordered.get(i).getId() > ordered.get(j).getId()) {
                    Stand aux = ordered.get(i);
                    ordered.set(i, ordered.get(j));
                    ordered.set(j, aux);
                }
            }
        }
        return ordered;
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

    public List<Person> getPeopleOrderedById() {
        List<Person> ordered = new ArrayList<>(people);

        for (int i = 0; i < ordered.size(); i++) {
            for (int j = i + 1; j < ordered.size(); j++) {
                if (ordered.get(i).getId() > ordered.get(j).getId()) {
                    Person aux = ordered.get(i);
                    ordered.set(i, ordered.get(j));
                    ordered.set(j, aux);
                }
            }
        }
        return ordered;
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

    public List<Publisher> getPublishersOrderedByNit() {
        List<Publisher> ordered = new ArrayList<>(publishers);

        for (int i = 0; i < ordered.size(); i++) {
            for (int j = i + 1; j < ordered.size(); j++) {
                // Comparación lexicográfica sin Comparator
                if (ordered.get(i).getNit().compareTo(ordered.get(j).getNit()) > 0) {
                    Publisher aux = ordered.get(i);
                    ordered.set(i, ordered.get(j));
                    ordered.set(j, aux);
                }
            }
        }
        return ordered;
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
