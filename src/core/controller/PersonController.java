
package core.controller;

import core.controller.utils.Response;
import core.model.Author;
import core.model.Manager;
import core.model.MegaferiaDataStore;
import core.model.Narrator;
import core.model.Person;
import core.controller.SortUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author keinerthd
 */
public class PersonController {

    private MegaferiaDataStore store;

    public PersonController() {
        this.store = MegaferiaDataStore.getInstance();
    }

    private Response<Void> validarPersona(long id, String firstname, String lastname) {

        if (id < 0 || String.valueOf(id).length() > 15) {
            return Response.badRequest("El ID de la persona debe ser >= 0 y de maximo 15 dígitos.");
        }

        if (firstname == null || firstname.trim().isEmpty()) {
            return Response.badRequest("El nombre no debe estar vacio.");
        }

        if (lastname == null || lastname.trim().isEmpty()) {
            return Response.badRequest("El apellido no debe estar vacio.");
        }

        if (store.existsPersonById(id)) {
            return Response.badRequest("Ya existe una persona con ese ID.");
        }

        return Response.ok(null);
    }

    // ===== Crear Autor =====
    public Response<Void> crearAutor(long id, String firstname, String lastname) {

        Response<Void> valid = validarPersona(id, firstname, lastname);
        if (!valid.isOk()) {
            return valid;
        }

        Author author = new Author(id, firstname, lastname);
        store.addPerson(author);

        return Response.ok("Autor creado correctamente.", null);
    }

    // ===== Crear Gerente =====
    public Response<Void> crearGerente(long id, String firstname, String lastname) {

        Response<Void> valid = validarPersona(id, firstname, lastname);
        if (!valid.isOk()) {
            return valid;
        }

        Manager manager = new Manager(id, firstname, lastname);
        store.addPerson(manager);

        return Response.ok("Gerente creado correctamente.", null);
    }

    // ===== Crear Narrador =====
    public Response<Void> crearNarrador(long id, String firstname, String lastname) {

        Response<Void> valid = validarPersona(id, firstname, lastname);
        if (!valid.isOk()) {
            return valid;
        }

        Narrator narrator = new Narrator(id, firstname, lastname);
        store.addPerson(narrator);

        return Response.ok("Narrador creado correctamente.", null);
    }

    // ===== Consultas =====
    // Todas las personas ordenadas por id
    public Response<List<Person>> obtenerPersonas() {
        List<Person> people = store.getPeopleOrderedById();

        // (si quieres cumplir Prototype 100% puedes hacer copias aquí)
        return Response.ok(people);
    }

    public Response<List<Author>> obtenerAutores() {
        List<Person> people = store.getPeopleOrderedById();
        List<Author> authors = new ArrayList<>();

        for (Person p : people) {
            if (p instanceof Author) {
                authors.add((Author) p);
            }
        }

        return Response.ok(authors);
    }

    public Response<List<Manager>> obtenerGerentes() {
        List<Person> people = store.getPeopleOrderedById();
        List<Manager> managers = new ArrayList<>();

        for (Person p : people) {
            if (p instanceof Manager) {
                managers.add((Manager) p);
            }
        }

        return Response.ok(managers);
    }

    public Response<List<Narrator>> obtenerNarradores() {
        List<Person> people = store.getPeopleOrderedById();
        List<Narrator> narrators = new ArrayList<>();

        for (Person p : people) {
            if (p instanceof Narrator) {
                narrators.add((Narrator) p);
            }
        }

        return Response.ok(narrators);
    }
    
    public Response<List<Author>> obtenerAutoresConMasLibros() {
        List<Person> people = store.getPeopleOrderedById();
        ArrayList<Author> authors = new ArrayList<>();

        for (Person p : people) {
            if (p instanceof Author) {
                authors.add((Author) p);
            }
        }
        
        ArrayList<Author> sortedAuthors = SortUtils.getSortedAuthorsByBookCount(authors);
        return Response.ok(sortedAuthors);
    }
}
