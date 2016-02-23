package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.domain.mapper.UserMapper;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.util.PreferencesConstants;
import com.marcohc.helperoid.PreferencesHelper;

public class GetCurrentUserUseCase extends SynchronousUseCase {

    @Override
    public UserModel execute() {
        return UserMapper.getInstance().parseModel(PreferencesHelper.getInstance().getString(PreferencesConstants.USER, null));
    }

}
