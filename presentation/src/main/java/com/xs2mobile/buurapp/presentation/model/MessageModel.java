package com.xs2mobile.buurapp.presentation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.marcohc.helperoid.FormatterHelper;
import com.xs2mobile.buurapp.presentation.util.Constants;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.InitiativeChatViewHolder;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.NeighbourChatViewHolder;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.ViewHolderAbstractClass;

import org.joda.time.DateTime;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageModel extends BaseModel {

    private Long id;
    private Long chatId;
    private Long initiativeId;
    private String text;
    private UserModel user;
    private Date created;
    private boolean mine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getInitiativeId() {
        return initiativeId;
    }

    public void setInitiativeId(Long initiativeId) {
        this.initiativeId = initiativeId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    /**
     * - Time/Date: Rules   sec 1s-59s; mins 1m-59m; hours 1h-12h; days 10-03-15
     *
     * @return
     */
    public String getDateFormatted() {

        String dateFormatted = "";
        if (created != null) {

            DateTime current = new DateTime();
            DateTime aux = new DateTime(created);

            current = current.minusSeconds(60);
            if (aux.isAfter(current)) {
                current = new DateTime();
                dateFormatted = current.getSecondOfDay() - aux.getSecondOfDay() + "s";
                return dateFormatted;
            }

            current = new DateTime();
            current = current.minusMinutes(60);
            if (aux.isAfter(current)) {
                current = new DateTime();
                dateFormatted = current.getMinuteOfDay() - aux.getMinuteOfDay() + "m";
                return dateFormatted;
            }

            current = new DateTime();
            current = current.minusHours(12);
            if (aux.isAfter(current)) {
                current = new DateTime();
                dateFormatted = current.getHourOfDay() - aux.getHourOfDay() + "h";
                return dateFormatted;
            }

            dateFormatted = FormatterHelper.formatShortDate(created);

        }
        return dateFormatted;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    @Override
    public ViewHolderAbstractClass getDefaultViewHolder(Constants.DATA_TYPE itemType) {
        if (itemType.equals(Constants.DATA_TYPE.INITIATIVE_CHAT)) {
            return new InitiativeChatViewHolder();
        } else if (itemType.equals(Constants.DATA_TYPE.NEIGHBOURS_CHAT)) {
            return new NeighbourChatViewHolder();
        }
        return null;
    }
}
