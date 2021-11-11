package com.example

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @Get("/")
    public List<BookAnalytics> listAnalytics() {
        return analyticsService.listAnalytics();
    }
}
