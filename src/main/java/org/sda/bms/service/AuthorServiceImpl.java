package org.sda.bms.service;

import org.sda.bms.model.Author;
import org.sda.bms.repository.AuthorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.IllegalFormatWidthException;
import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService{
    private static final String NAME_VALIDATION_REGEX = "^[a-zA-Z]+$";
    //dependencies
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void create(String firstName, String lastName) {
        if (firstName == null || firstName.isBlank() || firstName.isEmpty() ){
            throw new IllegalArgumentException(
                    "Provided first name is empty or blank. Insert a valid value."
            );
        }
        if (lastName == null || lastName.isBlank() || lastName.isEmpty() ){
            throw new IllegalArgumentException(
                    "Provided last name is empty or blank. Provide a valid value."
            );
        }
        if (!firstName.matches(NAME_VALIDATION_REGEX)){
            throw new IllegalArgumentException(
                    "Provided first name contains invalid characters. Provide a valid value."
            );
        }
        if (!lastName.matches(NAME_VALIDATION_REGEX)){
            throw new IllegalArgumentException(
                    "Provided last name contains invalid characters. Provide a valid value."
            );
        }
        Author author = new Author(firstName, lastName);
        authorRepository.create(author);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();

    }

    @Override
    public void delete(int id) {
        if (id <= 0){
            throw new IllegalArgumentException(
                    "Provided id is negative or 0. Provide a valid value.");
        }

        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isEmpty()){
            throw new EntityNotFoundException("Author with provided id was not found in the system");
        }
        authorRepository.delete(authorOptional.get());
    }
}
