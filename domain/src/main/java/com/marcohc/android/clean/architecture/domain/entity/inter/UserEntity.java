package com.marcohc.android.clean.architecture.domain.entity.inter;

import com.marcohc.android.clean.architecture.domain.entity.Entity;

public interface UserEntity extends Entity {

    void setId(Long id);

    String getUsername();

    void setUsername(String username);

    String getFirstName();

    void setFirstName(String firsName);

    String getLastName();

    void setLastName(String lastName);

    String getDateOfBirth();

    void setDateOfBirth(String dateOfBirth);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getImage();

    void setImage(String image);

    String getToken();

    void setToken(String token);
}