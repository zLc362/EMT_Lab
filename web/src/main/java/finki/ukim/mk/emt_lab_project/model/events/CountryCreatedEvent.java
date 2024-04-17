package finki.ukim.mk.emt_lab_project.model.events;

import finki.ukim.mk.emt_lab_project.model.Country;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class CountryCreatedEvent extends ApplicationEvent {

    private LocalDateTime when;
    private String name;

    public CountryCreatedEvent(Country source) {
        super(source);
        this.when = LocalDateTime.now();
        this.name=source.getName();
    }

    public CountryCreatedEvent(Country source, LocalDateTime when) {
        super(source);
        this.when = when;
        this.name=source.getName();
    }
}
