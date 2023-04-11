package Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
