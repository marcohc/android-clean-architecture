package com.marcohc.architecture.domain.mapper;

import com.marcohc.architecture.common.helper.ParserHelper;
import com.marcohc.architecture.domain.entity.Entity;
import com.marcohc.architecture.domain.model.Model;

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
// TODO: Create test for this class
public abstract class BaseMapper<M extends Model, E extends Entity> {

    private final Class<M> mModelClass;
    private final Class<E> mEntityClass;

    public BaseMapper(Class<M> modelClass, Class<E> entityClass) {
        this.mModelClass = modelClass;
        this.mEntityClass = entityClass;
    }

    /**
     * Transform an entity list to a model list.
     *
     * @param entitiesList the entity list to transform
     * @return the transformed model list
     */
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

    /**
     * Parse the json String to a model.
     *
     * @param json the model serialized into a json String
     * @return the model
     */
    public M parseModel(String json) {
        return ParserHelper.getInstance().parse(json, mModelClass);
    }

    /**
     * Parse the json String to a entity.
     *
     * @param json the model serialized into a json String
     * @return the entity
     */
    public E parseEntity(String json) {
        return ParserHelper.getInstance().parse(json, mEntityClass);
    }

    /**
     * Transform entity to a model.
     *
     * @param entity the entity to transform
     * @return the transformed model
     */
    public M transform(E entity) {
        return ParserHelper.getInstance().parse(entity, mModelClass);
    }

    /**
     * Transform model to a entity.
     *
     * @param model the model to transform
     * @return the transformed entity
     */
    public E transform(M model) {
        return ParserHelper.getInstance().parse(model, mEntityClass);
    }

}
