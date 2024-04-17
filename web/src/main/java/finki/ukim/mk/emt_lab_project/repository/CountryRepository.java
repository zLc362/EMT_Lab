package finki.ukim.mk.emt_lab_project.repository;

import finki.ukim.mk.emt_lab_project.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
