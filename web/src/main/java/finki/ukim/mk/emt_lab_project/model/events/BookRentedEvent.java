package finki.ukim.mk.emt_lab_project.model.events;

import finki.ukim.mk.emt_lab_project.model.Book;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class BookRentedEvent extends ApplicationEvent {
    private LocalDateTime when;
    private String name;

    public BookRentedEvent(Book source) {
        super(source);
        this.when = LocalDateTime.now();
        this.name=source.getName();
    }

    public BookRentedEvent(Book source, LocalDateTime when) {
        super(source);
        this.when = when;
        this.name=source.getName();
    }
}