package finki.ukim.mk.emt_lab_project.service;

import finki.ukim.mk.emt_lab_project.model.Book;
import finki.ukim.mk.emt_lab_project.model.dto.BookDTO;
import finki.ukim.mk.emt_lab_project.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);

    void deleteById(Long id);

    Optional<Book> rent(Long id);

    Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies);

    Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> save(BookDTO bookDTO);

    Optional<Book> edit(Long id, BookDTO bookDTO);
}
