package com.marcohc.architecture.presentation.view.inter;

import com.marcohc.architecture.presentation.view.BaseView;

public interface ReportView extends BaseView {

    String getDescriptionText();

    String getReportText();

    void goToMain();

    void invalidateDescription();

    void invalidateReportSpinner();

}
