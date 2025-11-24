/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package core.model.repository;

import core.model.Book;
import java.util.List;

/**
 *
 * @author keinerthd
 */

public interface BookRepository {
    void addBook(Book book);
    List<Book> getBooks();
}
