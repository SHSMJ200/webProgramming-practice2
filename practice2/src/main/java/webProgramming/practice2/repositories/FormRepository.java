package webProgramming.practice2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webProgramming.practice2.models.Form;

import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {
    Form findById(int id);

    List<Form> findAllByPublished(boolean published);
}
