package com.xs2mobile.buurapp.data.entity.inter;

import com.xs2mobile.buurapp.data.entity.Entity;
import com.xs2mobile.buurapp.data.entity.impl.AddressEntityImpl;

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

    AddressEntity getAddress();

    void setAddress(AddressEntityImpl address);

    String getImage();

    void setImage(String image);

    String getToken();

    void setToken(String token);
}