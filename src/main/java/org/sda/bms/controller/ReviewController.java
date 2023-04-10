package org.sda.bms.controller;

import org.sda.bms.repository.exception.EntityCreationFailedException;
import org.sda.bms.repository.exception.EntityFetchingFailedException;
import org.sda.bms.service.ReviewService;

import javax.persistence.EntityNotFoundException;
import java.util.Scanner;

public class ReviewController {
    private final ReviewService reviewService;
    private final Scanner scanner;

    public ReviewController(ReviewService reviewService, Scanner scanner) {
        this.reviewService = reviewService;
        this.scanner = scanner;
    }

    public void create() {
        try {
            System.out.println("Please insert book Id: ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Please provide score: ");
            int score = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Please provide comment: ");
            String comment = scanner.nextLine().trim();

            reviewService.create(bookId, score, comment);
            System.out.println("Review was successfully created.");
        } catch (NumberFormatException e) {
            System.err.println("Provided id or score is not a number. Provide a numeric value.");
        } catch (EntityCreationFailedException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityFetchingFailedException e) {
            System.err.println(e.getMessage());
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error. Please contact your administrator.");
        }
    }
}
