package finki.ukim.mk.emt_lab_project.service.impl;

import finki.ukim.mk.emt_lab_project.model.Country;
import finki.ukim.mk.emt_lab_project.model.events.CountryCreatedEvent;
import finki.ukim.mk.emt_lab_project.model.exceptions.CountryNotFoundException;
import finki.ukim.mk.emt_lab_project.repository.AuthorRepository;
import finki.ukim.mk.emt_lab_project.repository.BookRepository;
import finki.ukim.mk.emt_lab_project.repository.CountryRepository;
import finki.ukim.mk.emt_lab_project.service.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImplementation implements CountryService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CountryServiceImplementation(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }
    @Override
    public Optional<Country> save(String name, String continent) {
        Country country = new Country(name, continent);
        this.applicationEventPublisher.publishEvent(new CountryCreatedEvent(country));
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
        country.setName(name);
        country.setContinent(continent);
        return Optional.of(countryRepository.save(country));
    }
}
