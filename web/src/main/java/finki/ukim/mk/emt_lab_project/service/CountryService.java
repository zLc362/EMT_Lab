package finki.ukim.mk.emt_lab_project.service;

import finki.ukim.mk.emt_lab_project.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(String name, String continent);

    void deleteById(Long id);

    Optional<Country> edit(Long id, String name, String continent);
}
