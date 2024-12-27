package webProgramming.practice2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webProgramming.practice2.models.Field;
import webProgramming.practice2.models.Form;
import webProgramming.practice2.services.FormService;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {
    @Autowired
    private FormService formService;

    @GetMapping
    public List<Form> getFormsFromService() {
        return formService.getAllForms();
    }

    @PostMapping
    public ResponseEntity addNewForm(@RequestBody Form form) {
        formService.makeForm(form);
        return ResponseEntity.ok("form created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity getFormFromService(@PathVariable int id) {
        return ResponseEntity.ok(formService.getFormById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateForm(@PathVariable int id, @RequestBody Form form) {
        formService.updateForm(id, form);
        return ResponseEntity.ok("form updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFormFromService(@PathVariable int id) {
        formService.deleteFormById(id);
        return ResponseEntity.ok("form deleted successfully");
    }

    @GetMapping("/{id}/fields")
    public ResponseEntity getFormFieldsFromService(@PathVariable int id) {
        return ResponseEntity.ok(formService.getFormFieldsById(id));
    }

    @PutMapping("/{id}/fields")
    public ResponseEntity updateFormFields(@PathVariable int id, @RequestBody List<Field> fields) {
        formService.updateFormFields(id, fields);
        return ResponseEntity.ok("form updated successfully");
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity publishForm(@PathVariable int id) {
        formService.publishForm(id);
        return ResponseEntity.ok("form updated successfully");
    }

    @GetMapping("/published")
    public List<Form> getPublishedForms() {
        return formService.getPublishedForms();
    }
}
