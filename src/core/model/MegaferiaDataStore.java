/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public boolean existsStandById(long id) {
        return stands.stream().anyMatch(s -> s.getId() == id);
    }

    public void addStand(Stand stand) {
        stands.add(stand);
    }

    public List<Stand> getStandsOrderedById() {
        List<Stand> copy = new ArrayList<>(stands);
        Collections.sort(copy, Comparator.comparingLong(Stand::getId));
        return copy;
    }

    // ======== PERSONAS ========

    public boolean existsPersonById(long id) {
        return people.stream().anyMatch(p -> p.getId() == id);
    }

    public void addPerson(Person p) {
        people.add(p);
    }

    public Person findPersonById(long id) {
        for (Person p : people) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public List<Person> getPeopleOrderedById() {
        List<Person> copy = new ArrayList<>(people);
        Collections.sort(copy, Comparator.comparingLong(Person::getId));
        return copy;
    }

    // ======== EDITORIALES ========

    public boolean existsPublisherByNit(String nit) {
        return publishers.stream().anyMatch(e -> e.getNit().equals(nit));
    }

    public void addPublisher(Publisher e) {
        publishers.add(e);
    }

    public Publisher findPublisherByNit(String nit) {
        for (Publisher e : publishers) {
            if (e.getNit().equals(nit)) return e;
        }
        return null;
    }

    public List<Publisher> getPublishersOrderedByNit() {
        List<Publisher> copy = new ArrayList<>(publishers);
        Collections.sort(copy, Comparator.comparing(Publisher::getNit));
        return copy;
    }

    // ======== LIBROS ========

    public boolean existsBookByIsbn(String isbn) {
        return books.stream().anyMatch(b -> b.getIsbn().equals(isbn));
    }

    public void addBook(Book b) {
        books.add(b);
    }

    public List<Book> getBooksOrderedByIsbn() {
        List<Book> copy = new ArrayList<>(books);
        Collections.sort(copy, Comparator.comparing(Book::getIsbn));
        return copy;
    }

    public List<Book> getBooksByAuthor(Author author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthors().contains(author)) {
                result.add(b);
            }
        }
        Collections.sort(result, Comparator.comparing(Book::getIsbn));
        return result;
    }

    public List<Book> getBooksByFormat(Class<? extends Book> bookType) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (bookType.isInstance(b)) {
                result.add(b);
            }
        }
        Collections.sort(result, Comparator.comparing(Book::getIsbn));
        return result;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}

