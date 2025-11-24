/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller;

import core.controller.utils.Response;
import core.controller.utils.SortUtils;
import core.controller.utils.ValidationUtils;
import core.controller.utils.FormatValidator;
import core.model.*;
import core.model.repository.PersonRepository;
import core.model.repository.PublisherRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author keinerthd
 */
public class PublisherController {

    private PublisherRepository store;
    private PersonRepository personStore;
    
    public PublisherController(PublisherRepository store, PersonRepository personStore) {
        this.store = store;           
        this.personStore = personStore;
    }



    public Response<Void> crearEditorial(String nit, String name, String address, long managerId) {

        if (!FormatValidator.isValidNIT(nit)) {
            return Response.badRequest("El NIT debe tener el formato XXX.XXX.XXX-X.");
        }

        if (!ValidationUtils.isUniqueNIT(nit, new ArrayList<>(store.getPublishers()))) {
            return Response.badRequest("Ya existe una editorial con ese NIT.");
        }

        if (!FormatValidator.isNotEmpty(name)) {
            return Response.badRequest("El nombre de la editorial no debe estar vacío.");
        }

        if (!FormatValidator.isNotEmpty(address)) {
            return Response.badRequest("La dirección de la editorial no debe estar vacía.");
        }

        Person p = personStore.findPersonById(managerId);
        if (p == null || !(p instanceof Manager)) {
            return Response.badRequest("El gerente debe existir y ser un gerente válido.");
        }

        Manager manager = (Manager) p;

        Publisher publisher = new Publisher(nit, name, address, manager);
        store.addPublisher(publisher);

        return Response.ok("Editorial creada correctamente.", null);
    }

    public Response<List<Publisher>> obtenerEditoriales() {
        List<Publisher> publishers = store.getPublishers();
        ArrayList<Publisher> sortedPublishers = SortUtils.getSortedPublishersByNIT(new ArrayList<>(publishers));
        return Response.ok(sortedPublishers);
    }

    
    
}
