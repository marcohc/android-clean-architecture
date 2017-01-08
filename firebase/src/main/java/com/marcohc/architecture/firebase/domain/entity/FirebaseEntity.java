package com.marcohc.architecture.firebase.domain.entity;

import android.support.annotation.Nullable;

import com.marcohc.architecture.aca.domain.model.BaseJsonModel;

import java.io.Serializable;
import java.util.Map;

public class FirebaseEntity extends BaseJsonModel implements Serializable {

    protected String key;

    public String getKey() {
        return key;
    }

    public void setKey(@Nullable String key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof FirebaseEntity && key != null) {
            FirebaseEntity model = (FirebaseEntity) object;
            return key.equals(model.getKey());
        }
        return false;
    }

    @Nullable
    public Map<String, Object> toFirebaseMap() {
        return toMap();
    }
}
