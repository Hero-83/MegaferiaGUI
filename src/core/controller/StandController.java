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
import core.model.MegaferiaDataStore;
import core.model.Stand;
import core.model.Publisher;
import java.util.ArrayList;
import java.util.List;

public class StandController {

    private MegaferiaDataStore store;

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
            return Response.badRequest("Ya existe un stand con ese ID.");
        }

        Stand stand = new Stand(id, price);
        store.addStand(stand);

        // devolver una COPIA (patrpn Prototype)
        Stand copy = new Stand(stand.getId(), stand.getPrice());

        return Response.ok("Stand creado correctamente", copy);
    }

    // Obtener stands ordenados por id
    public Response<List<Stand>> obtenerStands() {
        List<Stand> stands = store.getStandsOrderedById();

        // crear copias con sus editoriales
        List<Stand> copies = new ArrayList<>();
        for (Stand s : stands) {
            Stand copy = new Stand(s.getId(), s.getPrice());

            if (s.getPublishers() != null) {
                for (Publisher p : s.getPublishers()) {
                    copy.addPublisher(p);  
                }
            }

            copies.add(copy);
        }

        return Response.ok(copies);
    }
}
