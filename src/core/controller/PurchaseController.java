/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller;

import core.controller.utils.Response;
import core.model.MegaferiaDataStore;
import core.model.Publisher;
import core.model.Stand;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author keinerthd
 */
public class PurchaseController {

    private MegaferiaDataStore store;

    public PurchaseController() {
        this.store = MegaferiaDataStore.getInstance();
    }

    public Response<Void> comprarStands(List<Long> standIds, List<String> publisherNits) {

        if (standIds == null || standIds.isEmpty()) {
            return Response.badRequest("Debe seleccionar al menos un stand.");
        }
        if (publisherNits == null || publisherNits.isEmpty()) {
            return Response.badRequest("Debe seleccionar al menos una editorial.");
        }

        // Validar repetidos en stands
        List<Long> usedStandIds = new ArrayList<>();
        for (Long id : standIds) {
            if (usedStandIds.contains(id)) {
                return Response.badRequest("No se permiten stands repetidos en la compra.");
            }
            usedStandIds.add(id);
        }

        // Validar repetidos en editoriales
        List<String> usedNits = new ArrayList<>();
        for (String nit : publisherNits) {
            if (usedNits.contains(nit)) {
                return Response.badRequest("No se permiten editoriales repetidas en la compra.");
            }
            usedNits.add(nit);
        }

        // Buscar stands
        List<Stand> standsSeleccionados = new ArrayList<>();
        for (Long id : standIds) {
            if (!store.existsStandById(id)) {
                return Response.badRequest("El stand con ID " + id + " no existe.");
            }
            // Buscar el stand en la lista
            List<Stand> allStands = store.getStands();
            for (Stand s : allStands) {
                if (s.getId() == id) {
                    standsSeleccionados.add(s);
                    break;
                }
            }
        }

        // Evitar que una editorial pueda comprar un stand ya comprado por otra editorial
        for (Stand stand : standsSeleccionados) {
            if (!stand.getPublishers().isEmpty()) {  
                return Response.badRequest(
                        "El stand con ID " + stand.getId() + " ya fue comprado por otra editorial."
                );
            }
        }

        // Buscar editoriales
        List<Publisher> publishersSeleccionadas = new ArrayList<>();
        for (String nit : publisherNits) {
            Publisher p = store.findPublisherByNit(nit);
            if (p == null) {
                return Response.badRequest("La editorial con NIT " + nit + " no existe.");
            }
            publishersSeleccionadas.add(p);
        }

        // Vincular: cada stand con cada editorial
        for (Stand stand : standsSeleccionados) {
            for (Publisher publisher : publishersSeleccionadas) {

                // Evitar vinculos repetidos
                if (!stand.getPublishers().contains(publisher)) {
                    stand.addPublisher(publisher);
                }
                if (!publisher.getStands().contains(stand)) {
                    publisher.addStand(stand);
                }
            }
        }

        return Response.ok("Compra de stands realizada correctamente.", null);
    }
}
