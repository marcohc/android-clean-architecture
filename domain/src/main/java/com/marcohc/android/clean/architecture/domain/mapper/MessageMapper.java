package com.marcohc.android.clean.architecture.domain.mapper;


import com.marcohc.android.clean.architecture.common.model.MessageModel;
import com.marcohc.android.clean.architecture.data.entity.impl.MessageEntityImpl;
import com.marcohc.android.clean.architecture.data.entity.inter.MessageEntity;
import com.marcohc.helperoid.ParserHelper;

public class MessageMapper extends BaseMapper {

    public static MessageEntity transform(MessageModel model) {
        MessageEntity entity = transform(model, MessageEntityImpl.class);
        return entity;
    }

    public static MessageModel parse(String messageJson) {
        return ParserHelper.parseJson(messageJson, MessageModel.class);
    }

}
