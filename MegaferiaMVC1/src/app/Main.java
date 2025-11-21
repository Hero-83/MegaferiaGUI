/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import controller.StandController;
import model.Stand;
import java.util.Scanner;
import controller.PersonaController;
import model.Persona;

public static void Main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StandController standController = new StandController();
    PersonaController personaController = new PersonaController();

    int opcion;
    do {
        System.out.println("\n=== MEGAFERIA LIBRO - MENÚ PRINCIPAL ===");
        System.out.println("1. Crear Stand");
        System.out.println("2. Ver Stands");
        System.out.println("3. Registrar Persona");
        System.out.println("4. Ver Personas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese ID del stand: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese nombre del stand: ");
                String nombre = sc.nextLine();
                System.out.print("Ingrese precio del stand: ");
                double precio = sc.nextDouble();

                standController.crearStand(id, nombre, precio);
                break;

            case 2:
                System.out.println("\n--- LISTA DE STANDS ---");
                for (Stand s : standController.getAll()) {
                    System.out.println(s);
                }
                break;

            case 3:
                System.out.print("Ingrese ID de la persona: ");
                int idPersona = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese nombre: ");
                String nombreP = sc.nextLine();
                System.out.print("Ingrese tipo (Autor / Gerente / Narrador): ");
                String tipo = sc.nextLine();

                personaController.registrarPersona(idPersona, nombreP, tipo);
                break;

            case 4:
                System.out.println("\n--- LISTA DE PERSONAS ---");
                for (Persona p : personaController.getAll()) {
                    System.out.println(p);
                }
                break;

            case 0:
                System.out.println("👋 Saliendo del sistema...");
                break;

            default:
                System.out.println("⚠️ Opción no válida.");
        }

    } while (opcion != 0);

    sc.close();
}


