package finki.ukim.mk.emt_lab_project.service.impl;

import finki.ukim.mk.emt_lab_project.model.Author;
import finki.ukim.mk.emt_lab_project.model.Country;
import finki.ukim.mk.emt_lab_project.model.exceptions.AuthorNotFoundException;
import finki.ukim.mk.emt_lab_project.model.exceptions.CountryNotFoundException;
import finki.ukim.mk.emt_lab_project.repository.AuthorRepository;
import finki.ukim.mk.emt_lab_project.repository.BookRepository;
import finki.ukim.mk.emt_lab_project.repository.CountryRepository;
import finki.ukim.mk.emt_lab_project.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImplementation implements AuthorService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImplementation(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
        return Optional.of(authorRepository.save(new Author(name, surname, country)));
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Long countryId) {
        Author author=findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return Optional.of(authorRepository.save(author));
    }
}
