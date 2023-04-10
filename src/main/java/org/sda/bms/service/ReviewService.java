package org.sda.bms.service;

public interface ReviewService {
    void create(int bookId, int score, String comment);

}
