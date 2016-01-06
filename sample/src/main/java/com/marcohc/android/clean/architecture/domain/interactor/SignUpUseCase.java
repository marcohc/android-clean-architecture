package com.marcohc.android.clean.architecture.domain.interactor;


import com.marcohc.android.clean.architecture.common.exception.DomainError;
import com.marcohc.android.clean.architecture.domain.bus.request.SaveUserRequest;
import com.marcohc.android.clean.architecture.domain.bus.request.SignUpRequest;
import com.marcohc.android.clean.architecture.domain.bus.response.data.SaveUserDataResponse;
import com.marcohc.android.clean.architecture.domain.bus.response.data.SignUpDataResponse;
import com.marcohc.android.clean.architecture.domain.bus.response.domain.SignUpDomainResponse;
import com.marcohc.android.clean.architecture.domain.entity.UserEntity;
import com.marcohc.android.clean.architecture.domain.mapper.UserMapper;
import com.marcohc.android.clean.architecture.domain.model.UserModel;

import java.util.Map;

public class SignUpUseCase extends AsynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private final Map<String, Object> parametersMap;
    private UserModel userModel;

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

            userModel = UserMapper.parse(userEntity);

            // Save current user
            post(new SaveUserRequest(UserMapper.parse(userModel)));

            // Create user
            // TODO: MANAGE THIS
            post(createResponse());
            unregisterFromBus();

        } else {

            // Send error to inform user
            SignUpDomainResponse response = createResponse();
            response.setError(DomainError.AUTHENTICATION);
            post(response);
            unregisterFromBus();
        }
    }

    public void onEventAsync(SaveUserDataResponse responseFromServer) {
        post(createResponse());
        unregisterFromBus();
    }
}
