/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Stand;

public class StandController extends BaseController<Stand> {

    public boolean crearStand(int id, String nombre, double precio) {
        if (precio <= 0 || nombre.isEmpty()) {
            System.out.println("Error: Datos inválidos.");
            return false;
        }
        Stand nuevo = new Stand(id, nombre, precio);
        add(nuevo);
        System.out.println("Stand creado exitosamente: " + nuevo);
        return true;
    }
}
