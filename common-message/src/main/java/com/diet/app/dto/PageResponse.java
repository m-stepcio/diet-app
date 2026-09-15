package com.diet.app.dto;

public record PageResponse(
        List<T> items,
        int page,
        int size,
        long totalSize,
        int totalPages
) {
}
