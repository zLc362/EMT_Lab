package finki.ukim.mk.emt_lab_project.listeners;

import finki.ukim.mk.emt_lab_project.model.events.CountryCreatedEvent;
import finki.ukim.mk.emt_lab_project.service.CountryService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CountryEventHandlers {
    private final CountryService countryService;

    public CountryEventHandlers(CountryService countryService) {
        this.countryService = countryService;
    }
    @EventListener
    public void onCountryCreated(CountryCreatedEvent event) {
        System.out.println("Country with name: "+event.getName()+" was created on "+event.getWhen());
    }


}
