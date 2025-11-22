/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller;

/**
 *
 * @author keinerthd
 */

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.model.MegaferiaDataStore;
import core.model.Stand;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StandController {

    private final MegaferiaDataStore store;

    public StandController() {
        this.store = MegaferiaDataStore.getInstance();
    }

    // Crear un stand
    public Response<Stand> crearStand(long id, double price) {

        // id único, >= 0, máximo 15 dígitos
        if (id < 0 || String.valueOf(id).length() > 15) {
            return Response.badRequest("El ID del stand debe ser >= 0 y de máximo 15 dígitos.");
        }

        // precio > 0
        if (price <= 0) {
            return Response.badRequest("El precio del stand debe ser mayor que 0.");
        }


        // id no repetido
        if (store.existsStandById(id)) {
            return Response.conflict("Ya existe un stand con ese ID.");
        }

        Stand stand = new Stand(id, price);

        // Guardar en el "almacén"
        store.addStand(stand);

        // devolver una COPIA (patrón Prototype)
        Stand copy = new Stand(stand.getId(), stand.getPrice());

        return Response.ok("Stand creado correctamente", copy);
    }

    // Obtener stands ordenados por id
    public Response<List<Stand>> obtenerStands() {
        List<Stand> stands = store.getStandsOrderedById();

        // crear copias
        List<Stand> copies = new ArrayList<>();
        for (Stand s : stands) {
            copies.add(new Stand(s.getId(), s.getPrice()));
        }

        return Response.ok(copies);
    }
}