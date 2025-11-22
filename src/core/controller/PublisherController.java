/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller;

import core.controller.utils.Response;
import core.model.Manager;
import core.model.MegaferiaDataStore;
import core.model.Person;
import core.model.Publisher;
import core.model.Stand;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author keinerthd
 */
public class PublisherController {

    private MegaferiaDataStore store;

    public PublisherController() {
        this.store = MegaferiaDataStore.getInstance();
    }

    // Validar formato NIT: XXX.XXX.XXX-X
    private boolean isValidNitFormat(String nit) {
        if (nit == null || nit.length() != 13) {
            return false;
        }

        // 3 dígitos
        for (int i = 0; i < 3; i++) {
            if (!Character.isDigit(nit.charAt(i))) {
                return false;
            }
        }
        if (nit.charAt(3) != '.') {
            return false;
        }

        // otros 3 dígitos
        for (int i = 4; i < 7; i++) {
            if (!Character.isDigit(nit.charAt(i))) {
                return false;
            }
        }
        if (nit.charAt(7) != '.') {
            return false;
        }

        // otros 3 dígitos
        for (int i = 8; i < 11; i++) {
            if (!Character.isDigit(nit.charAt(i))) {
                return false;
            }
        }
        if (nit.charAt(11) != '-') {
            return false;
        }

        // último dígito
        if (!Character.isDigit(nit.charAt(12))) {
            return false;
        }

        return true;
    }

    public Response<Void> crearEditorial(String nit, String name, String address, long managerId) {

        if (nit == null || nit.trim().isEmpty()) {
            return Response.badRequest("El NIT no debe estar vacío.");
        }

        if (!isValidNitFormat(nit)) {
            return Response.badRequest("El NIT debe tener el formato XXX.XXX.XXX-X.");
        }

        if (store.existsPublisherByNit(nit)) {
            return Response.badRequest("Ya existe una editorial con ese NIT.");
        }

        if (name == null || name.trim().isEmpty()) {
            return Response.badRequest("El nombre de la editorial no debe estar vacío.");
        }

        if (address == null || address.trim().isEmpty()) {
            return Response.badRequest("La dirección de la editorial no debe estar vacía.");
        }

        Person p = store.findPersonById(managerId);
        if (p == null || !(p instanceof Manager)) {
            return Response.badRequest("El gerente debe existir y ser un gerente válido.");
        }

        Manager manager = (Manager) p;

        Publisher publisher = new Publisher(nit, name, address, manager);
        store.addPublisher(publisher);

        return Response.ok("Editorial creada correctamente.", null);
    }

    public Response<List<Publisher>> obtenerEditoriales() {
        List<Publisher> publishers = store.getPublishersOrderedByNit();
        // Si quieres Prototype, aquí creas copias
        return Response.ok(publishers);
    }

    
    
}
