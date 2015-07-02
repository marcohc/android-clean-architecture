package com.xs2mobile.buurapp.presentation.view.impl.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.marcohc.helperoid.ScreenHelper;
import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.model.InitiativeModel;
import com.xs2mobile.buurapp.presentation.model.MessageModel;
import com.xs2mobile.buurapp.presentation.presenter.impl.InitiativeDetailPresenterImpl;
import com.xs2mobile.buurapp.presentation.presenter.inter.InitiativeDetailPresenter;
import com.xs2mobile.buurapp.presentation.util.Constants;
import com.xs2mobile.buurapp.presentation.util.NavigationManager;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.BaseListAdapter;
import com.xs2mobile.buurapp.presentation.view.impl.fragment.AddTextFragment;
import com.xs2mobile.buurapp.presentation.view.inter.InitiativeDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class InitiativeDetailActivity extends BaseMvpActivity<InitiativeDetailView, InitiativeDetailPresenter> implements InitiativeDetailView, BaseListAdapter.OnSubViewClickListener, AdapterView.OnItemClickListener, AddTextFragment.AddTextFragmentListener {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.titleText)
    TextView titleText;

    @InjectView(R.id.ownerText)
    TextView ownerText;

    @InjectView(R.id.subOwnerText)
    TextView subOwnerText;

    @InjectView(R.id.descriptionText)
    TextView descriptionText;

    @InjectView(R.id.listView)
    ListView listView;

    @InjectView(R.id.noDataText)
    TextView noDataText;

    @InjectView(R.id.titleEditText)
    EditText titleEditText;

    @InjectView(R.id.descriptionEditText)
    EditText descriptionEditText;

    @InjectView(R.id.detailsContainer)
    ViewGroup detailsContainer;

    @InjectView(R.id.detailsEditContainer)
    ViewGroup detailsEditContainer;

    private BaseListAdapter listViewAdapter;
    private AddTextFragment addTextFragment;
    private MenuItem menuItem1;
    private MenuItem menuItem2;
    private boolean isEditing = false;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    public InitiativeDetailPresenter createPresenter() {
        return new InitiativeDetailPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.initiative_detail_activity);

        findViewsById();

        initializeActionBar();

        initializeListView();

        initializeComponentBehavior();

    }

    private void findViewsById() {
        addTextFragment = (AddTextFragment) getSupportFragmentManager().findFragmentById(R.id.addTextFragment);
        addTextFragment.setListener(this);
    }

    private void initializeActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_logo);
    }

    private void initializeComponentBehavior() {
        hideKeyBoard();
        presenter.onViewCreated();
    }

    private void initializeListView() {
        listViewAdapter = new BaseListAdapter(this, R.layout.initiative_chat_list_item, new ArrayList<MessageModel>(), Constants.DATA_TYPE.INITIATIVE_CHAT);
        listView.setOnItemClickListener(this);
        listView.setAdapter(listViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuItem1 = menu.findItem(R.id.item_1);
        menuItem2 = menu.findItem(R.id.item_2);
        menuItem1.setVisible(false);
        menuItem1.setEnabled(false);
        menuItem2.setVisible(false);
        menuItem2.setEnabled(false);

        InitiativeModel initiative = presenter.getInitiative();
        if (initiative != null && initiative.isMine()) {
            menuItem2.setVisible(true);
            menuItem2.setEnabled(true);
            menuItem2.setIcon(R.drawable.ic_action_edit);
            menuItem2.setTitle(R.string.delete);
        }

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
                goBack();
//                onBackPressed();
                break;
            case R.id.item_1:
                presenter.onDeleteActionButtonClick();
                break;
            case R.id.item_2:
                if (isEditing) {
                    presenter.onEditActionButtonClick();
                    isEditing = false;
                } else {
                    presenter.onEnableEditActionButtonClick();
                    isEditing = true;
                }
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    public void onSubViewItemClick(View view, int position, Object data) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onMessageAdded(String text) {
        presenter.onMessageAdded(text);
    }

    // ************************************************************************************************************************************************************************
    // * Presenter handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void loadInitiativeData(InitiativeModel initiative) {
        getSupportActionBar().setTitle(initiative.getTitle());
        titleText.setText(initiative.getTitle());
        descriptionText.setText(initiative.getDescription());
        if (initiative.isMine()) {
            ownerText.setText(R.string.initiative_of);
            subOwnerText.setText(R.string.you);
        } else {
            ownerText.setText(getString(R.string.initiative_of) + " " + initiative.getLocation().getStreet() + " " + initiative.getLocation().getNumber());
            subOwnerText.setText("");
        }
    }

    @Override
    public void loadMessagesData(List<MessageModel> messageModelList) {
        setDataToListView(messageModelList);
    }

    @Override
    public void enableDetailsEditContainer(boolean enable) {

        if (enable) {
            detailsContainer.setVisibility(View.GONE);
            detailsEditContainer.setVisibility(View.VISIBLE);
            titleEditText.setText(presenter.getInitiative().getTitle());
            descriptionEditText.setText(presenter.getInitiative().getDescription());
            enableMenuItem1();
            enableMenuItem2();
        } else {
            detailsContainer.setVisibility(View.VISIBLE);
            detailsEditContainer.setVisibility(View.GONE);
            menuItem1.setEnabled(false);
            menuItem1.setVisible(false);
            menuItem2.setEnabled(true);
            menuItem2.setVisible(true);
            menuItem2.setIcon(R.drawable.ic_action_edit);
            menuItem2.setTitle(R.string.edit);
        }
    }

    @Override
    public String getInitiativeJson() {
        return getIntent().getStringExtra(NavigationManager.INITIATIVE);
    }

    @Override
    public void addMessage(MessageModel message) {
        noDataText.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        listViewAdapter.add(message);
        listViewAdapter.notifyDataSetChanged();
        listView.post(new Runnable() {
            @Override
            public void run() {
                listView.smoothScrollToPosition(listViewAdapter.getCount() - 1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        goBack();
    }

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

    private void enableMenuItem2() {
        menuItem2.setEnabled(true);
        menuItem2.setVisible(true);
        menuItem2.setIcon(R.drawable.ic_action_done);
        menuItem2.setTitle(R.string.edit);
    }

    private void enableMenuItem1() {
        menuItem1.setEnabled(true);
        menuItem1.setVisible(true);
        menuItem1.setIcon(R.drawable.ic_action_trash);
        menuItem1.setTitle(R.string.delete);
    }

    private void setDataToListView(List<MessageModel> messageModelList) {
        if (messageModelList.isEmpty()) {
            noDataText.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            noDataText.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            listViewAdapter.clear();
            listViewAdapter.addThemAll(messageModelList);
            listViewAdapter.notifyDataSetChanged();
            listView.post(new Runnable() {
                @Override
                public void run() {
                    listView.setSelection(listViewAdapter.getCount() - 1);
                }
            });
        }
    }

    private void hideKeyBoard() {
        ScreenHelper.hideKeyboard(this);
    }

}
