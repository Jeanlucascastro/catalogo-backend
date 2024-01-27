package com.sociedade.catalogoback.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
