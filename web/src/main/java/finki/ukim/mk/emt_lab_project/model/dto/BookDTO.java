package finki.ukim.mk.emt_lab_project.model.dto;

import finki.ukim.mk.emt_lab_project.model.Author;
import finki.ukim.mk.emt_lab_project.model.enums.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BookDTO {
    private String name;
    private String category;
    private Long authorId;
    private Integer availableCopies;
    public BookDTO(){}
    public BookDTO(String name, String category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }
}
