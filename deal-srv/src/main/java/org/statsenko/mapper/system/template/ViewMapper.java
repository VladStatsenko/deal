package org.statsenko.mapper.system.template;


import org.mapstruct.MappingTarget;
import org.statsenko.entity.AbstractEntity;

import javax.persistence.Entity;
import java.util.List;

public interface ViewMapper <E extends AbstractEntity, D>{

    D toDto(E entity);

    List<D> toDtoList(List<E> entityList);

    E toEntity(D dto);

    List<E> toEntityList(List<D> dtoList);


}
