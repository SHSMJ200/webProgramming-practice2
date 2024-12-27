package webProgramming.practice2.controllers.exceptions;

public class FormNotFoundException extends IllegalArgumentException{
    public FormNotFoundException(int id) {
        super("no form exists with id: " + id);
    }
}
