package com.example

import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Singleton
public class AnalyticsService {

    private final Map<Book, Long> bookAnalytics = new ConcurrentHashMap<>();

    public void updateBookAnalytics(Book book) {
        bookAnalytics.compute(book, (b, v) -> {
            if (v == null) {
                return 1L;
            } else {
                return v + 1;
            }
        });
    }

    public List<BookAnalytics> listAnalytics() {
        return bookAnalytics
                .entrySet()
                .stream()
                .map(e -> new BookAnalytics(e.getKey().getIsbn(), e.getValue()))
                .collect(Collectors.toList());
    }
}