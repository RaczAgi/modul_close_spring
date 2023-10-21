package hu.progmatic.modul_close_spring.repo;

import hu.progmatic.modul_close_spring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
}
