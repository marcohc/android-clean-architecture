package com.xs2mobile.buurapp.presentation.presenter.inter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.xs2mobile.buurapp.presentation.model.ChatModel;
import com.xs2mobile.buurapp.presentation.model.NeighbourChatModel;
import com.xs2mobile.buurapp.presentation.view.inter.NeighboursListView;

import java.util.List;

public interface NeighboursListPresenter extends MvpPresenter<NeighboursListView> {

    void onItemClick(NeighbourChatModel user);

    void onViewCreated();

    void onNeighboursWithChatResponse(List<NeighbourChatModel> modelList);

    void onChatCreated(ChatModel model);
}
