package com.app.nailcare.responses;

public record APIResponse<T>(T data, String message) {}
