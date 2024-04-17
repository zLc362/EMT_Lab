package finki.ukim.mk.emt_lab_project.config;

import finki.ukim.mk.emt_lab_project.model.Author;
import finki.ukim.mk.emt_lab_project.model.Country;
import finki.ukim.mk.emt_lab_project.model.enums.Category;
import finki.ukim.mk.emt_lab_project.service.AuthorService;
import finki.ukim.mk.emt_lab_project.service.BookService;
import finki.ukim.mk.emt_lab_project.service.CountryService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private final AuthorService authorService;

    private final BookService bookService;

    private final CountryService countryService;
    public DataInitializer(AuthorService authorService, BookService bookService, CountryService countryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.countryService = countryService;
    }


    @PostConstruct
    public void initData() {
        this.countryService.save("America","North America");
        this.countryService.save("Macedonia","Europe");
        this.countryService.save("Germany","Europe");
        this.countryService.save("France","Europe");
        this.countryService.save("Italy","Europe");
        this.countryService.save("Brazil","South America");
        this.countryService.save("Canada","North America");

        List<Country> countries = countryService.findAll();

        this.authorService.save("John","Doe",countries.get(0).getId());
        this.authorService.save("Bob","Brock",countries.get(1).getId());
        this.authorService.save("Angelina","Trucker",countries.get(2).getId());
        this.authorService.save("Amy","Doe",countries.get(3).getId());

        List<Author> authors = authorService.findAll();

        this.bookService.save("Book1", Category.DRAMA,authors.get(0).getId(),4);
        this.bookService.save("Book2", Category.BIOGRAPHY,authors.get(1).getId(),6);
        this.bookService.save("Book3", Category.HISTORY,authors.get(3).getId(),3);
        this.bookService.save("Book4", Category.THRILER,authors.get(0).getId(),8);
        this.bookService.save("Book5", Category.FANTASY,authors.get(2).getId(),9);
    }
}