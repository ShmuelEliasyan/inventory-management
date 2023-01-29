package com.inventorymanagement.rest.webservices.restfulwebservices.utils;

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
}
