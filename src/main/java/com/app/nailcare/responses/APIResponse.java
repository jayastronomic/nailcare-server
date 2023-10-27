package com.app.nailcare.responses;

/**
 * The APIResponse record represents a generic response structure used for API responses in the NailCare application.
 *
 * @param <T> The type of data contained in the API response.
 */
public record APIResponse<T>(T data, String message) {}