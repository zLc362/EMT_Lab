package finki.ukim.mk.emt_lab_project.service;

import finki.ukim.mk.emt_lab_project.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);

    Optional<Author> save(String name, String surname, Long countryId);

    void deleteById(Long id);

    Optional<Author> edit(Long id, String name, String surname, Long countryId);
}
