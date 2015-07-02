package com.xs2mobile.buurapp.presentation.view.impl.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.marcohc.helperoid.ScreenHelper;
import com.xs2mobile.buurapp.MainApplication;
import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.presenter.impl.InitiativeCreationPresenterImpl;
import com.xs2mobile.buurapp.presentation.presenter.inter.InitiativeCreationPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.InitiativeCreationView;

import butterknife.InjectView;

public class InitiativeCreationActivity extends BaseMvpActivity<InitiativeCreationView, InitiativeCreationPresenter> implements InitiativeCreationView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.titleEditText)
    EditText titleEditText;

    @InjectView(R.id.descriptionEditText)
    EditText descriptionEditText;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    public InitiativeCreationPresenter createPresenter() {
        return new InitiativeCreationPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.initiative_creation_activity);

        initializeActionBar();

        if (MainApplication.isDevelopment()) {
            fillFormWithFakeData();
        }
    }

    private void fillFormWithFakeData() {
        typeOnWithShortPause(R.id.titleEditText, "My Initiative");
        typeOnWithShortPause(R.id.descriptionEditText, "Chanante ipsum dolor sit amet, con las rodillas in the guanter enratonao atiendee vivo con tu madre en un castillo soooy crossoverr enim. Pero qué pelazo ullamco ju-já hueles avinagrado zanguango gambitero gaticos, to sueltecico. Nostrud exercitation horcate nianoniano cartoniano chiquititantantan enim.");
    }

    private void typeOnWithShortPause(int viewId, String value) {
        EditText editText = (EditText) findViewById(viewId);
        editText.setText(value);
    }

    private void initializeActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_logo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem1 = menu.findItem(R.id.item_1);
        menuItem1.setVisible(false);
        menuItem1.setEnabled(false);
        MenuItem menuItem2 = menu.findItem(R.id.item_2);
        menuItem2.setVisible(true);
        menuItem2.setEnabled(true);
        menuItem2.setIcon(R.drawable.ic_action_done);
        menuItem2.setTitle(R.string.accept);
        return true;
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        hideKeyBoard();

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.item_2:
                presenter.onActionDoneClick();
                break;
            default:
                break;
        }

        return false;
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void goBack() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public String getDescription() {
        return descriptionEditText.getText().toString();
    }

    @Override
    public String getInitiativeTitle() {
        return titleEditText.getText().toString();
    }

    @Override
    public void invalidateTitle() {
        YoYo.with(Techniques.Shake).playOn(titleEditText);
    }

    @Override
    public void invalidateDescription() {
        YoYo.with(Techniques.Shake).playOn(descriptionEditText);
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    private void hideKeyBoard() {
        ScreenHelper.hideKeyboard(this);
    }

}
