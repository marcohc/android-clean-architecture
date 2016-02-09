package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.domain.entity.UserEntity;
import com.marcohc.android.clean.architecture.domain.mapper.UserMapper;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.util.PreferencesConstants;
import com.marcohc.helperoid.ParserHelper;
import com.marcohc.helperoid.PreferencesHelper;

public class GetCurrentUserUseCase extends SynchronousUseCase {

    @Override
    public UserModel execute() {
        return UserMapper.parse(ParserHelper.parse(PreferencesHelper.getString(PreferencesConstants.USER, null), UserEntity.class));
    }

}
