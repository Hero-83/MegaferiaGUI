/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.model.repository;

import core.model.Publisher;
import java.util.List;

/**
 *
 * @author keinerthd
 */

public interface PublisherRepository {
    boolean existsPublisherByNit(String nit);
    void addPublisher(Publisher publisher);
    List<Publisher> getPublishers();
    Publisher findPublisherByNit(String nit);
}