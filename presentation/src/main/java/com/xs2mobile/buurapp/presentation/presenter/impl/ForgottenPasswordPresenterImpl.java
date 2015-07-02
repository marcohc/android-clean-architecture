package com.xs2mobile.buurapp.presentation.presenter.impl;

        import com.xs2mobile.buurapp.domain.interactor.impl.ForgottenPasswordInteractorImpl;
        import com.xs2mobile.buurapp.domain.interactor.inter.ForgottenPasswordInteractor;
        import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;
        import com.xs2mobile.buurapp.presentation.presenter.inter.ForgottenPasswordPresenter;
        import com.xs2mobile.buurapp.presentation.view.inter.ForgottenPasswordView;

public class ForgottenPasswordPresenterImpl extends BasePresenter<ForgottenPasswordView, ForgottenPasswordInteractor> implements ForgottenPasswordPresenter {

    @Override
    public ForgottenPasswordInteractor createInteractor() {
        return new ForgottenPasswordInteractorImpl(this);
    }

    @Override
    public void onActionDoneClick() {
        getView().showError("Pending for API call!");
    }

}
