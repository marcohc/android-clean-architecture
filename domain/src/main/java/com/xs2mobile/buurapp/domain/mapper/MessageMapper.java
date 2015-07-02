package com.xs2mobile.buurapp.domain.mapper;

import android.os.Bundle;

import com.marcohc.helperoid.ParserHelper;
import com.xs2mobile.buurapp.data.entity.impl.MessageEntityImpl;
import com.xs2mobile.buurapp.data.entity.inter.MessageEntity;
import com.xs2mobile.buurapp.domain.repository.UserRepository;
import com.xs2mobile.buurapp.presentation.model.MessageModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageMapper extends BaseMapper {

    public static List<MessageModel> parseMessagesList(JSONArray response) {
        List list = ParserHelper.parseJsonArray(response, MessageEntityImpl.class);
        return getMessagesList(list);
    }

    public static MessageEntity transform(MessageModel model) {
        MessageEntity entity = transform(model, MessageEntityImpl.class);
        entity.setSender(model.getUser().getId());
        return entity;
    }

    private static List<MessageModel> getMessagesList(List<MessageEntity> entitiesList) {
        List<MessageModel> modelsList = new ArrayList<>();
        MessageModel model;
        UserModel currentUser = UserRepository.getInstance().getCurrentUser();
        UserModel messageUser;
        for (MessageEntity item : entitiesList) {
            model = transform(item, MessageModel.class);
            messageUser = UserRepository.getInstance().getUser(item.getSender());

            if (messageUser != null) {
                model.setMine(messageUser.getId() == currentUser.getId().longValue());
                model.setUser(messageUser);
            } else {
                model.setMine(false);
                model.setUser(null);
            }

            modelsList.add(model);
        }
        return modelsList;
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
