package com.demodev.backend.auth.dto;

public record TokenResponse(String token, String email, String name) {
}
