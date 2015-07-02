package com.xs2mobile.buurapp.presentation.presenter.impl;

import com.squareup.otto.Subscribe;
import com.xs2mobile.buurapp.domain.interactor.impl.NeighboursListInteractorImpl;
import com.xs2mobile.buurapp.domain.interactor.inter.NeighboursListInteractor;
import com.xs2mobile.buurapp.domain.bus.event.RefreshScreenEvent;
import com.xs2mobile.buurapp.presentation.model.ChatModel;
import com.xs2mobile.buurapp.presentation.model.NeighbourChatModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;
import com.xs2mobile.buurapp.presentation.presenter.inter.NeighboursListPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.NeighboursListView;

import java.util.List;

public class NeighboursListPresenterImpl extends BasePresenter<NeighboursListView, NeighboursListInteractor> implements NeighboursListPresenter {

    private UserModel user;

    @Override
    public NeighboursListInteractor createInteractor() {
        return new NeighboursListInteractorImpl(this);
    }

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onItemClick(NeighbourChatModel model) {

        this.user = model.getUser();

        // Check if this user has a chat, if not, create it
        ChatModel chat = getInteractor().getChatByUserId(user.getId());
        if (chat != null) {
            goToNeighboursChat(user, chat);
        } else {
            // Create chat
            getView().showLoading(true);
            getInteractor().createChat(user);
        }
    }

    @Override
    public void onViewCreated() {
        getView().showLoading(true);
        getInteractor().getNeighboursWithChats();
    }

    @Subscribe
    public void onRefreshScreenEvent(RefreshScreenEvent refreshScreenEvent) {
        refreshData();
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onNeighboursWithChatResponse(List<NeighbourChatModel> modelList) {
        getView().loadData(modelList);
    }

    @Override
    public void onChatCreated(ChatModel model) {
        goToNeighboursChat(user, model);
    }

    // ************************************************************************************************************************************************************************
    // * Auxiliary methods
    // ************************************************************************************************************************************************************************

    private void goToNeighboursChat(UserModel user, ChatModel chat) {
        getView().goToNeighboursChat(user, chat);
    }

    private void refreshData() {
        getInteractor().getNeighboursWithChats();
    }
}
