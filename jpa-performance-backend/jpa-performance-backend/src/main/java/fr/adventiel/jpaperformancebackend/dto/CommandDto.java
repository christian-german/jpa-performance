package fr.adventiel.jpaperformancebackend.dto;

import java.time.LocalDate;
import java.util.List;

public record CommandDto(Long id, Long number, LocalDate orderedAt, List<ItemDto> items) {
}
