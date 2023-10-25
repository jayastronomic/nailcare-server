package com.app.nailcare.responses;

import com.app.nailcare.models.User;

public record AuthUser(User user, String token) { }
