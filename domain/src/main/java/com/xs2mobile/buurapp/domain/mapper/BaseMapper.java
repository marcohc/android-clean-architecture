package com.xs2mobile.buurapp.domain.mapper;

import com.xs2mobile.buurapp.data.entity.Entity;
import com.xs2mobile.buurapp.presentation.model.BaseModel;

import org.modelmapper.ModelMapper;

public abstract class BaseMapper {

    private static ModelMapper mapper;

    public static <E extends Entity> E transform(BaseModel model, Class<E> type) {
        return getMapper().map(model, type);
    }

    public static <M extends BaseModel> M transform(Entity entity, Class<M> type) {
        return getMapper().map(entity, type);
    }

    private static ModelMapper getMapper() {
        if (mapper == null) {
            mapper = new ModelMapper();
        }
        return mapper;
    }
}
