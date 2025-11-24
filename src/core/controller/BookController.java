/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller;

import core.controller.utils.Response;
import core.controller.utils.SortUtils;
import core.controller.utils.ValidationUtils;
import core.controller.utils.FormatValidator;
import core.model.Author;
import core.model.Audiobook;
import core.model.Book;
import core.model.DigitalBook;
import core.model.Manager;
import core.model.Narrator;
import core.model.Person;
import core.model.PrintedBook;
import core.model.Publisher;
import core.model.repository.BookRepository;
import core.model.repository.PersonRepository;
import core.model.repository.PublisherRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author keinerthd
 */
public class BookController {

    private final BookRepository bookStore;
    private final PersonRepository personStore;
    private final PublisherRepository publisherStore;

    public BookController(BookRepository bookStore,PersonRepository personStore, PublisherRepository publisherStore) {
        this.bookStore = bookStore;
        this.personStore = personStore;
        this.publisherStore = publisherStore;
    }

    // ===== Helpers =====
    // Resuelve IDs de autores: objetos Author, validando existencia y repetidos
    private Response<List<Author>> resolverAutores(List<Long> authorIds) {
        if (authorIds == null || authorIds.isEmpty()) {
            return Response.badRequest("Debe seleccionar al menos un autor.");
        }

        List<Author> authors = new ArrayList<>();
        List<Long> usedIds = new ArrayList<>();

        for (Long id : authorIds) {
            if (id == null) {
                return Response.badRequest("ID de autor inválido.");
            }

            // no repetidos
            if (usedIds.contains(id)) {
                return Response.badRequest("No se permiten autores repetidos en el mismo libro.");
            }
            usedIds.add(id);

            Person p = personStore.findPersonById(id);
            if (p == null || !(p instanceof Author)) {
                return Response.badRequest("El autor con ID " + id + " no es válido.");
            }
            authors.add((Author) p);
        }

        return Response.ok(authors);
    }

    private Response<Void> validarDatosBasicosLibro(String title, String isbn, String genre,
            String format, double value, String publisherNit) {

        if (!FormatValidator.isNotEmpty(title)) {
            return Response.badRequest("El título no debe estar vacío.");
        }

        if (!FormatValidator.isValidISBN(isbn)) {
            return Response.badRequest("El ISBN debe tener el formato XXX-X-XX-XXXXXX-X.");
        }

        if (!ValidationUtils.isUniqueISBN(isbn, new ArrayList<>(bookStore.getBooks()))) {
            return Response.badRequest("Ya existe un libro con ese ISBN.");
        }

        if (!FormatValidator.isNotEmpty(genre)) {
            return Response.badRequest("El género no debe estar vacío.");
        }

        if (!FormatValidator.isNotEmpty(format)) {
            return Response.badRequest("El formato no debe estar vacío.");
        }

        if (value <= 0) {
            return Response.badRequest("El valor del libro debe ser mayor que 0.");
        }

        if (!FormatValidator.isNotEmpty(publisherNit)) {
            return Response.badRequest("Debe seleccionar una editorial.");
        }

        Publisher publisher = publisherStore.findPublisherByNit(publisherNit);
        if (publisher == null) {
            return Response.badRequest("La editorial no es válida.");
        }

        return Response.ok(null);
    }

    // ===== Crear libros =====
    public Response<Void> crearLibroImpreso(
            String title,
            List<Long> authorIds,
            String isbn,
            String genre,
            String format,
            double value,
            String publisherNit,
            int pages,
            int copies) {

        if (pages <= 0 || copies <= 0) {
            return Response.badRequest("Páginas y copias deben ser mayores que 0.");
        }

        Response<Void> validBasics = validarDatosBasicosLibro(title, isbn, genre, format, value, publisherNit);
        if (!validBasics.isOk()) {
            return validBasics;
        }

        Response<List<Author>> resAutores = resolverAutores(authorIds);
        if (!resAutores.isOk()) {
            return Response.badRequest(resAutores.getMessage());
        }
        List<Author> authors = resAutores.getData();

        Publisher publisher = publisherStore.findPublisherByNit(publisherNit);

        PrintedBook book = new PrintedBook(
                title,
                new ArrayList<>(authors),
                isbn,
                genre,
                format,
                value,
                publisher,
                pages,
                copies
        );

        // Relacionar
        bookStore.addBook(book);
        publisher.addBook(book);
        for (Author a : authors) {
            a.addBook(book);
        }

        return Response.ok("Libro impreso creado correctamente.", null);
    }

    public Response<Void> crearLibroDigital(
            String title,
            List<Long> authorIds,
            String isbn,
            String genre,
            String format,
            double value,
            String publisherNit,
            String hyperlink) {

        Response<Void> validBasics = validarDatosBasicosLibro(title, isbn, genre, format, value, publisherNit);
        if (!validBasics.isOk()) {
            return validBasics;
        }

        Response<List<Author>> resAutores = resolverAutores(authorIds);
        if (!resAutores.isOk()) {
            return Response.badRequest(resAutores.getMessage());
        }
        List<Author> authors = resAutores.getData();

        Publisher publisher = publisherStore.findPublisherByNit(publisherNit);

        DigitalBook book;
        if (hyperlink == null || hyperlink.trim().isEmpty()) {
            book = new DigitalBook(title, new ArrayList<>(authors), isbn, genre, format, value, publisher);
        } else {
            book = new DigitalBook(title, new ArrayList<>(authors), isbn, genre, format, value, publisher, hyperlink);
        }

        bookStore.addBook(book);
        publisher.addBook(book);
        for (Author a : authors) {
            a.addBook(book);
        }

        return Response.ok("Libro digital creado correctamente.", null);
    }

    public Response<Void> crearAudiolibro(
            String title,
            List<Long> authorIds,
            String isbn,
            String genre,
            String format,
            double value,
            String publisherNit,
            int duration,
            long narratorId) {

        if (duration <= 0) {
            return Response.badRequest("La duración del audiolibro debe ser mayor que 0.");
        }

        Response<Void> validBasics = validarDatosBasicosLibro(title, isbn, genre, format, value, publisherNit);
        if (!validBasics.isOk()) {
            return validBasics;
        }

        Response<List<Author>> resAutores = resolverAutores(authorIds);
        if (!resAutores.isOk()) {
            return Response.badRequest(resAutores.getMessage());
        }
        List<Author> authors = resAutores.getData();

        Publisher publisher = publisherStore.findPublisherByNit(publisherNit);

        Person p = personStore.findPersonById(narratorId);
        if (p == null || !(p instanceof Narrator)) {
            return Response.badRequest("El narrador debe ser válido.");
        }
        Narrator narrator = (Narrator) p;

        Audiobook book = new Audiobook(
                title,
                new ArrayList<>(authors),
                isbn,
                genre,
                format,
                value,
                publisher,
                duration,
                narrator
        );

        bookStore.addBook(book);
        publisher.addBook(book);
        for (Author a : authors) {
            a.addBook(book);
        }
        narrator.addBook(book);

        return Response.ok("Audiolibro creado correctamente.", null);
    }

    // ===== Consultas =====
    public Response<List<Book>> obtenerLibrosPorTipo(Class<? extends Book> tipo) {
        List<Book> allBooks = bookStore.getBooks();
        ArrayList<Book> filteredBooks = new ArrayList<>();

        for (Book book : allBooks) {
            if (tipo.isInstance(book)) {
                filteredBooks.add(book);
            }
        }

        ArrayList<Book> sortedBooks = SortUtils.getSortedBooksByISBN(filteredBooks);
        return Response.ok(sortedBooks);
    }

    public Response<List<Book>> obtenerLibrosPorAutor(long authorId) {
        Person p = personStore.findPersonById(authorId);
        if (p == null || !(p instanceof Author)) {
            return Response.badRequest("El autor no es válido.");
        }

        Author author = (Author) p;
        List<Book> allBooks = bookStore.getBooks();
        ArrayList<Book> authorBooks = new ArrayList<>();

        for (Book book : allBooks) {
            if (book.getAuthors().contains(author)) {
                authorBooks.add(book);
            }
        }

        ArrayList<Book> sortedBooks = SortUtils.getSortedBooksByISBN(authorBooks);
        return Response.ok(sortedBooks);
    }

    public Response<List<Book>> obtenerLibrosPorFormato(String formato) {
        List<Book> allBooks = bookStore.getBooks();
        ArrayList<Book> filteredBooks = new ArrayList<>();

        for (Book book : allBooks) {
            if (book.getFormat() != null && book.getFormat().equalsIgnoreCase(formato)) {
                filteredBooks.add(book);
            }
        }

        ArrayList<Book> sortedBooks = SortUtils.getSortedBooksByISBN(filteredBooks);
        return Response.ok(sortedBooks);
    }

    public Response<List<Book>> obtenerTodosLosLibros() {
        List<Book> allBooks = bookStore.getBooks();
        ArrayList<Book> sortedBooks = SortUtils.getSortedBooksByISBN(new ArrayList<>(allBooks));
        return Response.ok(sortedBooks);
    }
}
