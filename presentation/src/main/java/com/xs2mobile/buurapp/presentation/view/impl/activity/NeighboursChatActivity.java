package com.xs2mobile.buurapp.presentation.view.impl.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.marcohc.helperoid.ScreenHelper;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.model.MessageModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.presenter.impl.NeighboursChatPresenterImpl;
import com.xs2mobile.buurapp.presentation.presenter.inter.NeighboursChatPresenter;
import com.xs2mobile.buurapp.presentation.util.Constants;
import com.xs2mobile.buurapp.presentation.util.NavigationManager;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.BaseListAdapter;
import com.xs2mobile.buurapp.presentation.view.impl.fragment.AddTextFragment;
import com.xs2mobile.buurapp.presentation.view.inter.NeighboursChatView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class NeighboursChatActivity extends BaseMvpActivity<NeighboursChatView, NeighboursChatPresenter> implements NeighboursChatView, BaseListAdapter.OnSubViewClickListener, AdapterView.OnItemClickListener, AddTextFragment.AddTextFragmentListener {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.listView)
    ListView listView;

    private BaseListAdapter listViewAdapter;
    private AddTextFragment addTextFragment;
    private ImageView userImage;
    private TextView titleText;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    public NeighboursChatPresenter createPresenter() {
        return new NeighboursChatPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.neighbours_chat_activity);

        findViewsById();

        initializeActionBar();

        initializeListView();

        initializeComponentBehavior();

    }

    private void findViewsById() {
        addTextFragment = (AddTextFragment) getSupportFragmentManager().findFragmentById(R.id.addTextFragment);
        addTextFragment.setHintText(getString(R.string.write_a_message));
        addTextFragment.setListener(this);
    }

    private void initializeActionBar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setContentInsetsRelative(0, 0);
        ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.neighbour_action_bar_layout, toolbar);
        userImage = (ImageView) actionBarLayout.findViewById(R.id.userImage);
        titleText = (TextView) actionBarLayout.findViewById(R.id.titleText);
    }

    private void initializeComponentBehavior() {
        hideKeyBoard();
        initializeListView();
        presenter.onViewCreated();
    }

    private void initializeListView() {
        listViewAdapter = new BaseListAdapter(this, R.layout.neighbour_chat_list_item, new ArrayList<MessageModel>(), Constants.DATA_TYPE.NEIGHBOURS_CHAT);
        listView.setOnItemClickListener(this);
        listView.setAdapter(listViewAdapter);
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
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onMessageAdded(String text) {
        presenter.onMessageAdded(text);
    }

    // ************************************************************************************************************************************************************************
    // * Presenter handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public String getUserJson() {
        return getIntent().getStringExtra(NavigationManager.USER);
    }

    @Override
    public String getChatJson() {
        return getIntent().getStringExtra(NavigationManager.CHAT);
    }

    @Override
    public void loadUserData(UserModel user) {

        titleText.setText(user.getFullName());
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                userImage.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                userImage.setImageResource(R.drawable.im_user_place_holder);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };

        if (!StringUtils.isBlank(user.getImage())) {
            Picasso.with(this).load(user.getImage()).resize(100, 100).into(target);
        } else {
            userImage.setImageResource(R.drawable.im_user_place_holder);
        }
    }

    @Override
    public void loadMessagesData(List<MessageModel> messageModelList) {
        setDataToListView(messageModelList);
    }

    @Override
    public void addMessage(MessageModel message) {
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

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    private void setDataToListView(List<MessageModel> messageModelList) {
        if (messageModelList.isEmpty()) {
            listView.setVisibility(View.GONE);
        } else {
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
