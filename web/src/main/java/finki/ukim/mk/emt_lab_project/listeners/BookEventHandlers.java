package finki.ukim.mk.emt_lab_project.listeners;

import finki.ukim.mk.emt_lab_project.model.events.BookCreatedEvent;
import finki.ukim.mk.emt_lab_project.model.events.BookDeletedEvent;
import finki.ukim.mk.emt_lab_project.model.events.BookEditedEvent;
import finki.ukim.mk.emt_lab_project.model.events.BookRentedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventHandlers {

    @EventListener
    public void onBookCreated(BookCreatedEvent event){
        System.out.println("Book with name:" + event.getName() + " was Created on "+event.getWhen());
    }
    @EventListener
    public void onBookEdited(BookEditedEvent event){
        System.out.println("Book with name:" + event.getName() + " was Edited on "+event.getWhen());
    }
    @EventListener
    public void onBookDeleted(BookDeletedEvent event){
        System.out.println("Book with name:" + event.getName() + " was Deleted on "+event.getWhen());
    }
    @EventListener
    public void onBookRented(BookRentedEvent event){
        System.out.println("Book with name:" + event.getName() + " was Rented on "+event.getWhen());
    }
}
