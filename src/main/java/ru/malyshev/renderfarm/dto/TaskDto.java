package ru.malyshev.renderfarm.dto;

import org.springframework.security.core.Authentication;

public record TaskDto(String title, String description, Authentication auth) {

}
