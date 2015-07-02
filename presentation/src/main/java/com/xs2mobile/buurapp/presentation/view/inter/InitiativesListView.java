package com.xs2mobile.buurapp.presentation.view.inter;

import com.xs2mobile.buurapp.presentation.model.InitiativeModel;

import java.util.List;

public interface InitiativesListView extends BaseView {

    void loadListData(List<InitiativeModel> itemsList);

    void goToInitiativeCreation();

    void goToInitiativeDetail(int position);
}
