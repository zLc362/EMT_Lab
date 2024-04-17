package finki.ukim.mk.emt_lab_project.model.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long bookId) {
        super(String.format("Book with id %d does not exist.", bookId));
    }
}