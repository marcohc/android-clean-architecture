package com.marcohc.android.clean.architecture.domain.mapper;

import android.os.Bundle;

import com.marcohc.helperoid.ParserHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageMapper extends BaseMapper {

    public static MessageEntity transform(MessageModel model) {
        MessageEntity entity = transform(model, MessageEntityImpl.class);
        entity.setSender(model.getUser().getId());
        return entity;
    }

    public static MessageModel parse(String messageJson) {
        return ParserHelper.parseJson(messageJson, MessageModel.class);
    }

    /**
     * The object created is not complete, just filled with data from notification
     *
     * @param extras
     * @return
     */
    public static MessageModel parseFromNotification(Bundle extras) {
        MessageModel message = new MessageModel();
        Long userId = Long.parseLong((String) extras.get("user"));
        UserModel user = new UserModel();
        user.setId(userId);
        user.setFirstName((String) extras.get("username"));
        message.setText((String) extras.get("message"));
        message.setChatId(Long.parseLong((String) extras.get("chat")));

        String initiativeIdString = (String) extras.get("initiative");
        if (!StringUtils.isBlank(initiativeIdString)) {
            message.setInitiativeId(Long.parseLong(initiativeIdString));
        }

        message.setCreated(new Date(System.currentTimeMillis()));
        message.setUser(user);
        message.setMine(false);
        return message;
    }
}
