/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import core.controller.*;
import core.view.MegaferiaFrame;

/**
 *
 * @author keinerthd
 */

public class Main {
    public static void main(String[] args) {

        StandController standCtrl = new StandController();
        PersonController personCtrl = new PersonController();
        PublisherController publisherCtrl = new PublisherController();
        BookController bookCtrl = new BookController();
        PurchaseController purchaseCtrl = new PurchaseController();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MegaferiaFrame(
                    standCtrl,
                    personCtrl,
                    publisherCtrl,
                    bookCtrl,
                    purchaseCtrl
                ).setVisible(true);
            }
        });
    }
}
