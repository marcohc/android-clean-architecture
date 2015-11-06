package com.marcohc.android.clean.architecture.domain.mapper;

import com.marcohc.android.clean.architecture.domain.entity.Entity;
import com.marcohc.android.clean.architecture.domain.model.BaseModel;
import com.marcohc.helperoid.MapperHelper;

public abstract class BaseMapper {

    public static <E extends Entity> E transform(BaseModel model, Class<E> type) {
        return MapperHelper.getMapper().map(model, type);
    }

    public static <M extends BaseModel> M transform(Entity entity, Class<M> type) {
        return MapperHelper.getMapper().map(entity, type);
    }

}
