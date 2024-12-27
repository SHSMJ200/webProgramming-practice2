package webProgramming.practice2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webProgramming.practice2.controllers.exceptions.FormNotFoundException;
import webProgramming.practice2.models.Field;
import webProgramming.practice2.models.Form;
import webProgramming.practice2.repositories.FieldRepository;
import webProgramming.practice2.repositories.FormRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormService {
    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FieldRepository fieldRepository;

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    public void makeForm(Form form) {
        for (Field field : form.getFields()) {
            fieldRepository.save(field);
        }
        formRepository.save(form);
    }

    public Form getFormById(int id) {
        Form form = formRepository.findById(id);
        if (form == null) throw new FormNotFoundException(id);
        return form;
    }

    public void updateForm(int id, Form form) {
        Form existingForm = getFormById(id);
        if (existingForm == null) throw new FormNotFoundException(id);

        List<Integer> removableFieldIds = new ArrayList<>();
        for (Field field : existingForm.getFields()) {
            removableFieldIds.add(field.getId());
        }

        form.setId(id);
        makeForm(form);

        for (Integer removableId : removableFieldIds) {
            fieldRepository.deleteById(removableId);
        }
    }

    public void deleteFormById(int id) {
        if (getFormById(id) == null){
            throw new FormNotFoundException(id);
        }
        formRepository.deleteById(id);
    }

    public List<Field> getFormFieldsById(int id) {
        Form form = getFormById(id);
        if (form == null) throw new FormNotFoundException(id);
        return form.getFields();
    }

    public void updateFormFields(int id, List<Field> fields) {
        Form form = getFormById(id);
        if (form == null) throw new FormNotFoundException(id);

        List<Integer> removableFieldIds = new ArrayList<>();
        for (Field field : form.getFields()) {
            removableFieldIds.add(field.getId());
        }

        for (Field field : fields) {
            fieldRepository.save(field);
        }
        form.setFields(fields);
        formRepository.save(form);

        for (Integer removableId : removableFieldIds) {
            fieldRepository.deleteById(removableId);
        }
    }

    public void publishForm(int id) {
        Form form = getFormById(id);
        if (form == null) throw new FormNotFoundException(id);

        form.setPublished(!form.isPublished());
        formRepository.save(form);
    }

    public List<Form> getPublishedForms() {
        return formRepository.findAllByPublished(true);
    }
}
