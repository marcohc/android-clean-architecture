package com.marcohc.architecture.app.domain.interactor;

import com.marcohc.architecture.app.domain.mapper.UserMapper;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.app.presentation.util.PreferencesConstants;
import com.marcohc.architecture.common.helper.PreferencesHelper;

public class GetCurrentUserUseCase extends SynchronousUseCase {

    @Override
    public UserModel execute() {
        return UserMapper.getInstance().parseModel(PreferencesHelper.getInstance().getString(PreferencesConstants.USER, null));
    }

}
