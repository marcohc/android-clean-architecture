package com.marcohc.architecture.domain.interactor;

import com.marcohc.architecture.domain.mapper.UserMapper;
import com.marcohc.architecture.domain.model.UserModel;
import com.marcohc.architecture.presentation.util.PreferencesConstants;
import com.marcohc.helperoid.PreferencesHelper;

public class GetCurrentUserUseCase extends SynchronousUseCase {

    @Override
    public UserModel execute() {
        return UserMapper.getInstance().parseModel(PreferencesHelper.getInstance().getString(PreferencesConstants.USER, null));
    }

}
