package com.xs2mobile.buurapp.presentation.view.inter;

public interface InitiativeCreationView extends BaseView {

    String getInitiativeTitle();

    String getDescription();

    void invalidateTitle();

    void invalidateDescription();

    void goBack();
}
