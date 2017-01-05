/*
 * Copyright (C) 2016 Marco Hernaiz Cao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marcohc.architecture.common.util;

import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;


/**
 * Class to execute animations
 */
public class AnimationUtils {

    public static final int FADE_DURATION = 200;
    public static final int EXPAND_OR_COLLAPSE_DURATION = 300;

    // ************************************************************************************************************************************************************************
    // * Collapse and expand animations
    // ************************************************************************************************************************************************************************

    public static Animation getExpandViewAnimation(final View view, int duration) {

        view.measure(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = view.getMeasuredHeight();

        view.getLayoutParams().height = 0;
        view.setVisibility(View.VISIBLE);
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view.getLayoutParams().height = interpolatedTime == 1 ? RelativeLayout.LayoutParams.WRAP_CONTENT : (int) (targetHeight * interpolatedTime);
                view.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        // animation.setDuration((int) (targetHeight /
        // view.getContext().getResources().getDisplayMetrics().density));
        animation.setDuration(duration);
        return animation;
    }

    public static Animation getCollapseViewAnimation(final View view, int duration) {
        final int initialHeight = view.getMeasuredHeight();

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        animation.setDuration(duration);
        return animation;
    }

    public static void startExpandViewAnimationOn(final View expandableContainer) {
        if (expandableContainer.getVisibility() != View.VISIBLE) {
            Animation animation = getExpandViewAnimation(expandableContainer, EXPAND_OR_COLLAPSE_DURATION);
            expandableContainer.startAnimation(animation);
        }
    }

    public static void startCollapseViewAnimationOn(final View expandableContainer) {
        if (expandableContainer.getVisibility() == View.VISIBLE) {
            Animation animation = getCollapseViewAnimation(expandableContainer, EXPAND_OR_COLLAPSE_DURATION);
            expandableContainer.startAnimation(animation);
        }
    }

    public static void startExpandViewAnimationOn(final View expandableContainer, final View fadeContainer) {
        startExpandViewAnimationOn(expandableContainer, fadeContainer, null, EXPAND_OR_COLLAPSE_DURATION);
    }

    public static void startExpandViewAnimationOn(final View expandableContainer, final View fadeContainer, final int expandDuration) {
        startExpandViewAnimationOn(expandableContainer, fadeContainer, null, expandDuration);
    }

    public static void startExpandViewAnimationOn(final View expandableContainer, final View fadeContainer, final AnimationListener listener) {
        startExpandViewAnimationOn(expandableContainer, fadeContainer, listener, EXPAND_OR_COLLAPSE_DURATION);
    }

    public static void startExpandViewAnimationOn(final View expandableContainer, final View fadeContainer, final AnimationListener listener, final int expandDuration) {

        Animation animation = getExpandViewAnimation(expandableContainer, expandDuration);
        animation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation secondAnimation = getFadeInAnimation(FADE_DURATION);
                if (listener != null) {
                    secondAnimation.setAnimationListener(listener);
                }
                fadeContainer.startAnimation(secondAnimation);
            }
        });
        expandableContainer.startAnimation(animation);
    }

    public static void startCollapseViewAnimationOn(final View collapsibleContainer, final View fadeContainer) {
        startCollapseViewAnimationOn(collapsibleContainer, fadeContainer, null, EXPAND_OR_COLLAPSE_DURATION);
    }

    public static void startCollapseViewAnimationOn(final View collapsibleContainer, final View fadeContainer, int collapseDuration) {
        startCollapseViewAnimationOn(collapsibleContainer, fadeContainer, null, collapseDuration);
    }

    public static void startCollapseViewAnimationOn(final View collapsibleContainer, final View fadeContainer, final AnimationListener listener) {
        startCollapseViewAnimationOn(collapsibleContainer, fadeContainer, listener, EXPAND_OR_COLLAPSE_DURATION);
    }

    public static void startCollapseViewAnimationOn(final View collapsibleContainer, final View fadeContainer, final AnimationListener listener, final int collapseDuration) {

        Animation animation = getFadeOutAnimation(FADE_DURATION);
        animation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation secondAnimation = getCollapseViewAnimation(collapsibleContainer, collapseDuration);
                if (listener != null) {
                    secondAnimation.setAnimationListener(listener);
                }
                collapsibleContainer.startAnimation(secondAnimation);
            }
        });
        fadeContainer.startAnimation(animation);
    }

    public static void startExpandAndCollapseWithRotationImage(final Context context, final View dropDownImage, final View collapsibleContainer, final View fadeContainer) {

        if (collapsibleContainer.getVisibility() == View.GONE) {

            AnimationListener listener = new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    AnimationUtils.startExpandViewAnimationOn(collapsibleContainer, fadeContainer);
                }
            };
            AnimationUtils.startRotateDownOn(context, dropDownImage, listener);

        } else {

            AnimationListener listener = new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    AnimationUtils.startCollapseViewAnimationOn(collapsibleContainer, fadeContainer);
                }
            };
            AnimationUtils.startRotateUpOn(context, dropDownImage, listener);

        }
    }

    // ************************************************************************************************************************************************************************
    // * Fade animations
    // ************************************************************************************************************************************************************************

    public static Animation getFadeInAnimation(int durationInMilliseconds) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setFillAfter(true);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(durationInMilliseconds);
        return fadeIn;
    }

    public static Animation getFadeOutAnimation(int durationInMilliseconds) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setFillAfter(true);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(durationInMilliseconds);
        return fadeOut;
    }

    public static void startFadeInAnimationOn(final View container) {
        startFadeInAnimationOn(container, false);
    }

    public static void startFadeInAnimationOn(final View container, boolean forceFade) {
        if (forceFade || container.getVisibility() != View.VISIBLE) {
            Animation animation = getFadeInAnimation(FADE_DURATION);
            animation.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    container.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }
            });

            container.startAnimation(animation);
        }
    }

    public static void startFadeOutAnimationOn(final View container) {
        startFadeOutAnimationOn(container, false);
    }

    public static void startFadeOutAnimationOn(final View container, boolean forceFade) {
        if (forceFade || container.getVisibility() == View.VISIBLE) {
            Animation animation = getFadeOutAnimation(FADE_DURATION);
            animation.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    container.setVisibility(View.GONE);
                }
            });

            container.startAnimation(animation);
        }
    }

    // ************************************************************************************************************************************************************************
    // * Translation animations
    // ************************************************************************************************************************************************************************

    public static Animation getTranslationAnimation(boolean fromTheCenter, boolean toTheRight, int distance, int time) {
        Animation animation = null;
        if (fromTheCenter && toTheRight) {
            animation = new TranslateAnimation(0, distance, 0, 0);
        } else if (fromTheCenter && !toTheRight) {
            animation = new TranslateAnimation(0, -distance, 0, 0);
        } else if (!fromTheCenter && toTheRight) {
            animation = new TranslateAnimation(distance, 0, 0, 0);
        } else if (!fromTheCenter && !toTheRight) {
            animation = new TranslateAnimation(-distance, 0, 0, 0);
        }
        animation.setDuration(time);
        animation.setFillAfter(true);
        return animation;
    }

    // ************************************************************************************************************************************************************************
    // * Scale animations
    // ************************************************************************************************************************************************************************

    public static void startScaleAnimationOn(View view, float startScale, float endScale, long duration) {
        Animation anim = new ScaleAnimation(1f, 1f, startScale, endScale, Animation.ABSOLUTE, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(duration);
        anim.setFillAfter(true);
        view.startAnimation(anim);
    }

    // ************************************************************************************************************************************************************************
    // * Rotate animations
    // ************************************************************************************************************************************************************************

    public static void startRotateDownOn(Context context, View view, AnimationListener listener) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.rotate_down);
        if (listener != null) {
            animation.setAnimationListener(listener);
        }
        view.startAnimation(animation);
    }

    public static void startRotateDownOn(Context context, View view) {
        startRotateDownOn(context, view, null);
    }

    public static void startRotateUpOn(Context context, View view, AnimationListener listener) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        if (listener != null) {
            animation.setAnimationListener(listener);
        }
        view.startAnimation(animation);
    }

    public static void startRotateUpOn(Context context, View view) {
        startRotateUpOn(context, view, null);
    }

}
