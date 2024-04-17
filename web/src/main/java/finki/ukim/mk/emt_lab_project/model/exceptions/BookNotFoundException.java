package finki.ukim.mk.emt_lab_project.model.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long bookId) {
        super(String.format("Book with id %d does not exist.", bookId));
    }
}
