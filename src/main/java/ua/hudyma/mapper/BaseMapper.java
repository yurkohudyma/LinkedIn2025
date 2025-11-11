package ua.hudyma.mapper;

import java.util.List;
import java.util.function.Function;

public abstract class BaseMapper<D, E> {

    public List<D> toDtoList(List<E> entities) {
        return mapList(entities, this::toDto);
    }

    public List<E> toEntityList(List<D> dtos) {
        return mapList(dtos, this::toEntity);
    }

    public D mapToDto (E e){
        return toDto(e);
    }

    protected abstract D toDto(E entity);
    protected abstract E toEntity(D dto);

    protected <T, R> List<R> mapList(List<T> source, Function<T, R> mapper) {
        if (source == null || source.isEmpty()) {
            return List.of();
        }
        return source.stream().map(mapper).toList();
    }
}

