package com.marcohc.architecture.aca.domain.mapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marcohc.architecture.aca.domain.entity.Entity;
import com.marcohc.architecture.aca.domain.model.Model;
import com.marcohc.architecture.parser.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Base mapper with useful classes for parsing models and entities.
 *
 * @param <M> the model type to be mapped
 * @param <E> the entity type to be mapped
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public abstract class BaseMapper<M extends Model, E extends Entity> {

    private final Class<M> mModelClass;
    private final Class<E> mEntityClass;

    public BaseMapper(Class<M> modelClass, Class<E> entityClass) {
        this.mModelClass = modelClass;
        this.mEntityClass = entityClass;
    }

    /**
     * Parse the json String to a entity.
     *
     * @param json the model serialized into a json String
     * @return the entity
     */
    @Nullable
    public E parseEntity(String json) {
        return Parser.parse(json, mEntityClass);
    }

    /**
     * Parse the json String to a model.
     *
     * @param json the model serialized into a json String
     * @return the model
     */
    @Nullable
    public M parseModel(String json) {
        return Parser.parse(json, mModelClass);
    }

    /**
     * Transform entity to a model.
     *
     * @param entity the entity to transform
     * @return the transformed model
     */
    @Nullable
    public M transform(E entity) {
        return Parser.parse(entity, mModelClass);
    }

    /**
     * Transform model to a entity.
     *
     * @param model the model to transform
     * @return the transformed entity
     */
    @Nullable
    public E transform(M model) {
        return Parser.parse(model, mEntityClass);
    }

    /**
     * Transform an entity list to a model list.
     *
     * @param entitiesList the entity list to transform
     * @return the transformed model list
     */
    @NonNull
    public List<M> transformEntityList(List<? extends E> entitiesList) {
        List<M> modelsList = new ArrayList<>();
        if (entitiesList != null) {
            for (E item : entitiesList) {
                M model = transform(item);
                modelsList.add(model);
            }
        }
        return modelsList;
    }

    /**
     * Transform a model list to an entity list.
     *
     * @param modelsList the model list to transform
     * @return the transformed entity list
     */
    @NonNull
    public List<E> transformModelList(List<? extends M> modelsList) {
        List<E> entitiesList = new ArrayList<>();
        if (modelsList != null) {
            for (M item : modelsList) {
                E model = transform(item);
                entitiesList.add(model);
            }
        }
        return entitiesList;
    }

}
