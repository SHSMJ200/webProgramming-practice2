package webProgramming.practice2.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean published;
    @OneToMany
    private List<Field> fields;
    private String submitUrl;
    private String submitMethod;
}
