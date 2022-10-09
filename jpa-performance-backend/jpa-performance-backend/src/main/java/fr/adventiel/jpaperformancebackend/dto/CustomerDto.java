package fr.adventiel.jpaperformancebackend.dto;

import java.util.List;

public record CustomerDto(Long id, Long number, String firstname, String lastname, String email, List<CommandDto> commands) {
}
