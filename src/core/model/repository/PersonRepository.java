/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.model.repository;

import core.model.Person;
import java.util.List;

/**
 *
 * @author keinerthd
 */


public interface PersonRepository {
    boolean existsPersonById(long id);
    void addPerson(Person p);
    Person findPersonById(long id);
    List<Person> getPeople();
}