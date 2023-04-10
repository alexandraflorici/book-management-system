package org.sda.bms.service;

import org.sda.bms.model.Book;
import org.sda.bms.model.Review;
import org.sda.bms.repository.BookRepository;
import org.sda.bms.repository.ReviewRepository;

import javax.persistence.EntityNotFoundException;
import java.text.AttributedCharacterIterator;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {
    //dependencies
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(int bookId, int score, String comment) {
        if (bookId <= 0) {
            throw new IllegalArgumentException(
                    "Provided id is negative or 0. Provide a valid value."
            );
        }
        if (score < 1 || score > 5) {
            throw new IllegalArgumentException(
                    "Provide score is not between 1 and 5. Please provide a valid value."
            );
        }
        if (comment == null || comment.isEmpty() || comment.isBlank()) {
            throw new IllegalArgumentException(
                    "Provided comment is null, empty or blank. Please provide a valid comment."
            );
        }
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            throw new EntityNotFoundException(
                    "Book with given Id was not found in the system."
            );
        }
        Book book = optionalBook.get();
        Review review = new Review(score, comment);
        review.setBook(book);
        reviewRepository.create(review);
    }
}