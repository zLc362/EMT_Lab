package finki.ukim.mk.emt_lab_project.service.impl;

import finki.ukim.mk.emt_lab_project.model.Author;
import finki.ukim.mk.emt_lab_project.model.Book;
import finki.ukim.mk.emt_lab_project.model.dto.BookDTO;
import finki.ukim.mk.emt_lab_project.model.enums.Category;
import finki.ukim.mk.emt_lab_project.model.events.BookCreatedEvent;
import finki.ukim.mk.emt_lab_project.model.events.BookDeletedEvent;
import finki.ukim.mk.emt_lab_project.model.events.BookEditedEvent;
import finki.ukim.mk.emt_lab_project.model.events.BookRentedEvent;
import finki.ukim.mk.emt_lab_project.model.exceptions.AuthorNotFoundException;
import finki.ukim.mk.emt_lab_project.model.exceptions.BookNotFoundException;
import finki.ukim.mk.emt_lab_project.repository.AuthorRepository;
import finki.ukim.mk.emt_lab_project.repository.BookRepository;
import finki.ukim.mk.emt_lab_project.repository.CountryRepository;
import finki.ukim.mk.emt_lab_project.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImplementation(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
        this.applicationEventPublisher.publishEvent(new BookDeletedEvent(book));
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> rent(Long id) {
        Book book = findById(id).orElseThrow(()->new BookNotFoundException(id));
        if(book.getAvailableCopies()==0)
            return Optional.empty();
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        this.applicationEventPublisher.publishEvent(new BookRentedEvent(book));
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Book book = findById(id).orElseThrow(()->new BookNotFoundException(id));
        Author author = authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException(authorId));
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        this.applicationEventPublisher.publishEvent(new BookEditedEvent(book));
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDTO bookDTO) {
        Book book = findById(id).orElseThrow(()->new BookNotFoundException(id));
        Author author = authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(()->new AuthorNotFoundException(bookDTO.getAuthorId()));
        book.setName(book.getName());
        book.setCategory(Category.valueOf(bookDTO.getCategory()));
        book.setAuthor(author);
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        this.applicationEventPublisher.publishEvent(new BookEditedEvent(book));
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException(authorId));
        Book book = new Book(name,category,author,availableCopies);
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> save(BookDTO bookDTO) {
        Author author = authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(()->new AuthorNotFoundException(bookDTO.getAuthorId()));
        Book book = new Book(bookDTO.getName(), Category.valueOf(bookDTO.getCategory()),author, bookDTO.getAvailableCopies());
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(bookRepository.save(book));
    }
}
