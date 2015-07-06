package com.marcohc.android.clean.architecture.domain.mapper;


import com.marcohc.android.clean.architecture.domain.entity.MessageEntity;
import com.marcohc.android.clean.architecture.domain.model.MessageModel;
import com.marcohc.helperoid.ParserHelper;

public class MessageMapper extends BaseMapper {

    public static MessageEntity transform(MessageModel model) {
        MessageEntity entity = transform(model, MessageEntity.class);
        return entity;
    }

    public static MessageModel parse(String messageJson) {
        return ParserHelper.parseJson(messageJson, MessageModel.class);
    }

}
