package com.sociedade.catalogoback.domain.user.dto;

import com.sociedade.catalogoback.domain.user.UserRole;

public record RegisterDTO(String login, String email, String password, UserRole role) {
}
