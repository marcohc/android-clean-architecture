package com.marcohc.android.clean.architecture.domain.interactor;


import com.marcohc.android.clean.architecture.domain.bus.request.SignUpRequest;
import com.marcohc.android.clean.architecture.domain.bus.response.data.SignUpDataResponse;
import com.marcohc.android.clean.architecture.domain.bus.response.domain.SignUpDomainResponse;
import com.marcohc.android.clean.architecture.domain.entity.UserEntity;
import com.marcohc.android.clean.architecture.domain.mapper.UserMapper;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.domain.util.AuthenticationManager;
import com.marcohc.android.clean.architecture.presentation.exception.MyAppError;

import java.util.Map;

public class SignUpUseCase extends AsynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private final Map<String, Object> parametersMap;

    // ************************************************************************************************************************************************************************
    // * Constructor
    // ************************************************************************************************************************************************************************

    public SignUpUseCase(Map<String, Object> parametersMap) {
        super();
        this.parametersMap = parametersMap;
    }

    // ************************************************************************************************************************************************************************
    // * Bus events factory methods
    // ************************************************************************************************************************************************************************

    @Override
    protected SignUpRequest createRequest() {
        return new SignUpRequest((String) parametersMap.get("email"), (String) parametersMap.get("password"));
    }

    @Override
    protected SignUpDomainResponse createResponse() {
        return new SignUpDomainResponse();
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    public void onEventAsync(SignUpDataResponse responseFromServer) {

        if (!responseFromServer.hasError()) {

            // Create entity to persist in users
            UserEntity userEntity = new UserEntity();
//            userEntity.setKey((String) responseFromServer.getResult().get("uid"));
//            userEntity.setProvider("password");
            userEntity.setFirstName((String) parametersMap.get("firstName"));
            userEntity.setLastName((String) parametersMap.get("lastName"));
            userEntity.setEmail((String) parametersMap.get("email"));
//            userEntity.setCreatedAt(System.currentTimeMillis() * 1000);

            UserModel userModel = UserMapper.getInstance().transform(userEntity);

            // Save current user
            userModel.setKey(userModel.getEmail());
            AuthenticationManager.getInstance().logIn(userModel.getKey(), userModel);

            post(createResponse());
            unregisterFromBus();

        } else {

            // Send error to inform user
            SignUpDomainResponse response = createResponse();
            response.setError(MyAppError.AUTHENTICATION);
            post(response);
            unregisterFromBus();
        }
    }
}
