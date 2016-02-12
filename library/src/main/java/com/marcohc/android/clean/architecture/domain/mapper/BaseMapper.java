package com.marcohc.android.clean.architecture.domain.mapper;

import com.marcohc.android.clean.architecture.domain.entity.Entity;
import com.marcohc.android.clean.architecture.domain.model.Model;
import com.marcohc.helperoid.MapperHelper;
import com.marcohc.helperoid.ParserHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapper<M extends Model, E extends Entity> {

    private final Class<M> modelClass;
    private final Class<E> entityClass;

    public BaseMapper(Class<M> modelClass, Class<E> entityClass) {
        this.modelClass = modelClass;
        this.entityClass = entityClass;
    }

    public List<M> parseEntityList(List<? extends E> entitiesList) {
        List<M> modelsList = new ArrayList<>();
        if (entitiesList != null) {
            for (E item : entitiesList) {
                M model = transform(item);
                modelsList.add(model);
            }
        }
        return modelsList;
    }

    public List<E> parseModelList(List<? extends M> modelsList) {
        List<E> entitiesList = new ArrayList<>();
        if (modelsList != null) {
            for (M item : modelsList) {
                E model = transform(item);
                entitiesList.add(model);
            }
        }
        return entitiesList;
    }

    public M parseModel(String json) {
        return ParserHelper.parse(json, modelClass);
    }

    public E parseEntity(String json) {
        return ParserHelper.parse(json, entityClass);
    }

    public M transform(E entity) {
        return MapperHelper.getMapper().map(entity, modelClass);
    }

    public E transform(M model) {
        return MapperHelper.getMapper().map(model, entityClass);
    }

}
