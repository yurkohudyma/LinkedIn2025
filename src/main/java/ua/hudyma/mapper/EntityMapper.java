package ua.hudyma.mapper;

import java.util.List;

public interface EntityMapper<D, E> {
    D toDto(E entity);
    E toEntity(D dto);

    default List<D> toDtoList(List<E> entityList) {
        if (entityList == null) return List.of();
        return entityList.stream()
                .map(this::toDto)
                .toList();
    }

    default List<E> toEntityList(List<D> dtoList) {
        if (dtoList == null) return List.of();
        return dtoList.stream()
                .map(this::toEntity)
                .toList();
    }
}

