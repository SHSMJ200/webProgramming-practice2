package webProgramming.practice2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webProgramming.practice2.models.Field;

public interface FieldRepository extends JpaRepository<Field, Integer> {
}
