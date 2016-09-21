package com.marcohc.architecture.app.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.app.presentation.story.user.userdetail.view.UserDetailActivity;

/**
 * Manages the navigation between activities.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public final class Navigator {

    // ************************************************************************************************************************************************************************
    // * Constants
    // ************************************************************************************************************************************************************************

    public static final String USER = "user";

    // ************************************************************************************************************************************************************************
    // * Constructors
    // ************************************************************************************************************************************************************************

    private Navigator() {
    }

    // ************************************************************************************************************************************************************************
    // * Navigation methods
    // ************************************************************************************************************************************************************************

    /**
     * Go to {@link UserDetailActivity}.
     *
     * @param context the context
     * @param model   the UserModel to be displayed in the details
     */
    public static void goToUserDetail(Context context, UserModel model) {
        if (context != null && model != null) {
            Intent intent = new Intent(context, UserDetailActivity.class);
            intent.putExtra(Navigator.USER, model);
            context.startActivity(intent);
        }
    }
}
