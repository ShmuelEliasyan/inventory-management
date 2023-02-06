package com.inventorymanagement.rest.webservices.restfulwebservices.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PaginationUtils {
    /**
     * Calculates number of pages for given page size and total number of items.
     * <p>
     * Assumption:
     * we suppose that if we have 0 items we want 1 empty page
     */
    public static int calculatePagesCount(int pageSize, int totalCount) {
        return totalCount < pageSize ? 1 : (int) Math.ceil((double) totalCount / (double) pageSize);
    }

    public static PageRequest getPageRequest(Sort.Direction sortDirection, String sortBy, int page, int size) {
        Sort.Order order = new Sort.Order(sortDirection, sortBy);
        Sort sort = Sort.by(order);
        return PageRequest.of(page, size, sort);
    }
}
