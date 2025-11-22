/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller;

import core.controller.utils.Response;
import core.model.Author;
import core.model.Audiobook;
import core.model.Book;
import core.model.DigitalBook;
import core.model.MegaferiaDataStore;
import core.model.Narrator;
import core.model.Person;
import core.model.PrintedBook;
import core.model.Publisher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author keinerthd
 */


public class BookController {

    private MegaferiaDataStore store;

    public BookController() {
        this.store = MegaferiaDataStore.getInstance();
    }

    // ===== Helpers =====

    // ISBN: XXX-X-XX-XXXXXX-X
    private boolean isValidIsbnFormat(String isbn) {
        if (isbn == null || isbn.length() != 17) return false;

        // 3 dígitos
        for (int i = 0; i < 3; i++) if (!Character.isDigit(isbn.charAt(i))) return false;
        if (isbn.charAt(3) != '-') return false;

        // 1 dígito
        if (!Character.isDigit(isbn.charAt(4))) return false;
        if (isbn.charAt(5) != '-') return false;

        // 2 dígitos
        for (int i = 6; i < 8; i++) if (!Character.isDigit(isbn.charAt(i))) return false;
        if (isbn.charAt(8) != '-') return false;

        // 6 dígitos
        for (int i = 9; i < 15; i++) if (!Character.isDigit(isbn.charAt(i))) return false;
        if (isbn.charAt(15) != '-') return false;

        // último dígito
        if (!Character.isDigit(isbn.charAt(16))) return false;

        return true;
    }

    // Resuelve IDs de autores -> objetos Author, validando existencia y repetidos
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

            Person p = store.findPersonById(id);
            if (p == null || !(p instanceof Author)) {
                return Response.badRequest("El autor con ID " + id + " no es válido.");
            }
            authors.add((Author) p);
        }

        return Response.ok(authors);
    }

    // Validaciones comunes
    private Response<Void> validarDatosBasicosLibro(String title, String isbn, String genre,
                                                    String format, double value, String publisherNit) {

        if (title == null || title.trim().isEmpty()) {
            return Response.badRequest("El título no debe estar vacío.");
        }

        if (isbn == null || isbn.trim().isEmpty()) {
            return Response.badRequest("El ISBN no debe estar vacío.");
        }

        if (!isValidIsbnFormat(isbn)) {
            return Response.badRequest("El ISBN debe tener el formato XXX-X-XX-XXXXXX-X.");
        }

        if (store.existsBookByIsbn(isbn)) {
            return Response.badRequest("Ya existe un libro con ese ISBN.");
        }

        if (genre == null || genre.trim().isEmpty()) {
            return Response.badRequest("El género no debe estar vacío.");
        }

        if (format == null || format.trim().isEmpty()) {
            return Response.badRequest("El formato no debe estar vacío.");
        }

        if (value <= 0) {
            return Response.badRequest("El valor del libro debe ser mayor que 0.");
        }

        if (publisherNit == null || publisherNit.trim().isEmpty()) {
            return Response.badRequest("Debe seleccionar una editorial.");
        }

        Publisher publisher = store.findPublisherByNit(publisherNit);
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
        if (!validBasics.isOk()) return validBasics;

        Response<List<Author>> resAutores = resolverAutores(authorIds);
        if (!resAutores.isOk()) return Response.badRequest(resAutores.getMessage());
        List<Author> authors = resAutores.getData();

        Publisher publisher = store.findPublisherByNit(publisherNit);

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
        store.addBook(book);
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
        if (!validBasics.isOk()) return validBasics;

        Response<List<Author>> resAutores = resolverAutores(authorIds);
        if (!resAutores.isOk()) return Response.badRequest(resAutores.getMessage());
        List<Author> authors = resAutores.getData();

        Publisher publisher = store.findPublisherByNit(publisherNit);

        // El hipervínculo puede ser vacío según el enunciado
        DigitalBook book;
        if (hyperlink == null || hyperlink.trim().isEmpty()) {
            book = new DigitalBook(title, new ArrayList<>(authors), isbn, genre, format, value, publisher);
        } else {
            book = new DigitalBook(title, new ArrayList<>(authors), isbn, genre, format, value, publisher, hyperlink);
        }

        store.addBook(book);
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
        if (!validBasics.isOk()) return validBasics;

        Response<List<Author>> resAutores = resolverAutores(authorIds);
        if (!resAutores.isOk()) return Response.badRequest(resAutores.getMessage());
        List<Author> authors = resAutores.getData();

        Publisher publisher = store.findPublisherByNit(publisherNit);

        Person p = store.findPersonById(narratorId);
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

        store.addBook(book);
        publisher.addBook(book);
        for (Author a : authors) {
            a.addBook(book);
        }
        narrator.addBook(book);

        return Response.ok("Audiolibro creado correctamente.", null);
    }

    // ===== Consultas =====

    public Response<List<Book>> obtenerLibrosPorTipo(Class<? extends Book> tipo) {
        List<Book> books = store.getBooksByFormat(tipo);
        return Response.ok(books);
    }

    public Response<List<Book>> obtenerLibrosPorAutor(long authorId) {
        Person p = store.findPersonById(authorId);
        if (p == null || !(p instanceof Author)) {
            return Response.badRequest("El autor no es válido.");
        }

        Author author = (Author) p;
        List<Book> books = store.getBooksByAuthor(author);
        return Response.ok(books);
    }

    // por formato String (por ejemplo "Impreso", "Digital", etc.)
    public Response<List<Book>> obtenerLibrosPorFormato(String formato) {
        List<Book> all = store.getBooksOrderedByIsbn();
        List<Book> result = new ArrayList<>();

        for (Book b : all) {
            if (b.getFormat() != null && b.getFormat().equalsIgnoreCase(formato)) {
                result.add(b);
            }
        }

        return Response.ok(result);
    }

    public Response<List<Book>> obtenerTodosLosLibros() {
        return Response.ok(store.getBooksOrderedByIsbn());
    }
}

