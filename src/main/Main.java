/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import core.controller.*;
import core.view.MegaferiaFrame;
import core.model.MegaferiaDataStore;


/**
 *
 * @author keinerthd
 */

public class Main {
    public static void main(String[] args) {

        MegaferiaDataStore store = MegaferiaDataStore.getInstance();
        
        StandController standCtrl = new StandController(store);
        PersonController personCtrl = new PersonController(store);
        PublisherController publisherCtrl = new PublisherController(store, store);
        BookController bookCtrl = new BookController(store, store, store);
        PurchaseController purchaseCtrl = new PurchaseController(store, store);

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
