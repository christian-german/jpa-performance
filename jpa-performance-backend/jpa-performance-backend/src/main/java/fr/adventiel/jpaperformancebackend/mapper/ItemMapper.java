package fr.adventiel.jpaperformancebackend.mapper;

import fr.adventiel.jpaperformancebackend.dto.ItemDto;
import fr.adventiel.jpaperformancebackend.entity.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto toDto(Item item);

    Item toEntity(ItemDto itemDto);

    List<ItemDto> toDtos(List<Item> itemList);

    List<Item> toEntities(List<ItemDto> itemDtos);

}
