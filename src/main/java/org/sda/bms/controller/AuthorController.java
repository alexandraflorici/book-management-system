package org.sda.bms.controller;

import org.sda.bms.model.Author;
import org.sda.bms.repository.exception.EntityCreationFailedException;
import org.sda.bms.repository.exception.EntityDeleteFailedException;
import org.sda.bms.repository.exception.EntityFetchingFailedException;
import org.sda.bms.service.AuthorService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Scanner;

public class AuthorController {
    // Dependencies

    private final AuthorService authorService;
    private final Scanner scanner;

    public AuthorController(AuthorService authorService, Scanner scanner) {
        this.authorService = authorService;
        this.scanner = scanner;
    }

    public void create() {
        try {
            System.out.println("Please provide first name: ");
            String firstName = scanner.nextLine().trim();
            System.out.println("Please provide last name: ");
            String lastName = scanner.nextLine().trim();

            authorService.create(firstName, lastName);
            System.out.println("Author created successfully");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityCreationFailedException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error. Please contact your administrator.");
        }
    }

    public void displayAll() {
        try {
            List<Author> existingAuthors = authorService.findAll();
            for (Author author : existingAuthors) {
                System.out.println(
                        "Id: " + author.getId() +
                                " First name: " + author.getFirstName() +
                                " Last name: " + author.getLastName()
                );
            }
        } catch (EntityFetchingFailedException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteById() {
        try {
            System.out.println("Please provide author id. ");
            int authorId = Integer.parseInt(scanner.nextLine().trim());
            authorService.delete(authorId);
            System.out.println("Author was successfully deleted");
        }catch (NumberFormatException e){
            System.err.println("Provided id is not a number. Provide a valid value.");
        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }catch (EntityNotFoundException e){
            System.err.println(e.getMessage());
        }catch (EntityFetchingFailedException e){
            System.err.println(e.getMessage());
        }catch (EntityDeleteFailedException e) {
            System.err.println(e.getMessage());
        }catch (Exception e){
            System.err.println("Internal server error. Please contact your administrator.");
        }
    }
}
