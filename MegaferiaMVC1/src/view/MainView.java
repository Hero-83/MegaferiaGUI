/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.StandController;

public class MainView {
    public static void main(String[] args) {
        StandController controller = new StandController();

        controller.crearStand(1, "Editorial Alfa", 500.0);
        controller.crearStand(2, "Editorial Beta", 750.0);

        System.out.println(controller.getAll());
    }
}
