/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Persona;

public class PersonaController extends BaseController<Persona> {

    public boolean registrarPersona(int id, String nombre, String tipo) {
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("❌ Error: el nombre no puede estar vacío.");
            return false;
        }

        if (!tipo.equalsIgnoreCase("Autor") &&
            !tipo.equalsIgnoreCase("Gerente") &&
            !tipo.equalsIgnoreCase("Narrador")) {
            System.out.println("❌ Error: tipo de persona inválido.");
            return false;
        }

        Persona nueva = new Persona(id, nombre, tipo);
        add(nueva);
        System.out.println("✅ Persona registrada correctamente: " + nueva);
        return true;
    }
}
