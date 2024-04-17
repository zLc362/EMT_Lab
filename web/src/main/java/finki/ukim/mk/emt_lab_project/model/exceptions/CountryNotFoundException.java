package finki.ukim.mk.emt_lab_project.model.exceptions;

public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(Long countryId) {
        super(String.format("Country with id %d does not exist.", countryId));
    }
}