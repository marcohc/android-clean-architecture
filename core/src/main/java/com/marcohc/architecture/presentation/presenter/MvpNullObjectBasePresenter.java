package com.marcohc.architecture.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * A {@link MvpPresenter} implmenetation that implements the <a href="https://en.wikipedia.org/wiki/Null_Object_pattern">null
 * object pattern</a> for the attached mvp mView. So whenever the mView gets detached from this
 * presenter by calling{@link #detachView(boolean)}, a new "null object" mView gets dynamically
 * instantiated by using reflections and set as the current
 * mView (instead of null). The new "null object" mView simply does nothing. This avoids
 * NullPointerExceptions and checks like {@code if (getView() != null)}
 * <p>
 * <p>
 * Please note that when creating the "null object" the first generic parameter (left depth-first
 * search) that will be found that is subtype of {@link MvpView} will be used as the type of the
 * mView. So avoid having multiple generic parameters for "View" like this {@code
 * MyPresenter<FooMvpView, OtherMvpView>} because we can't know wheter FooMvpView or OtherMvpView is
 * the
 * real type of this presenter's mView. In that case (left depth-first search) FooMvpView will be
 * used (may cause ClassCastException if OtherMvpView was the desired one)
 * </p>
 *
 * @param <V> The type of the {@link MvpView}
 * @author Jens Dirller , Hannes Dorfmann
 * @see MvpBasePresenter
 * @since 1.2.0
 */
public abstract class MvpNullObjectBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> mView;
    private final V mNullView;

    public MvpNullObjectBasePresenter() {
        try {

            // Scan the inheritance hierarchy until we reached MvpNullObjectBasePresenter
            Class<V> viewClass = null;
            Class<?> currentClass = getClass();

            while (viewClass == null) {

                Type genericSuperType = currentClass.getGenericSuperclass();

                while (!(genericSuperType instanceof ParameterizedType)) {
                    // Scan inheritance tree until we find ParameterizedType which is probably a MvpSubclass
                    currentClass = currentClass.getSuperclass();
                    genericSuperType = currentClass.getGenericSuperclass();
                }

                Type[] types = ((ParameterizedType) genericSuperType).getActualTypeArguments();

                for (int i = 0; i < types.length; i++) {
                    Class<?> genericType = (Class<?>) types[i];
                    if (genericType.isInterface() && isSubTypeOfMvpView(genericType)) {
                        viewClass = (Class<V>) genericType;
                        break;
                    }
                }

                // Continue with next class in inheritance hierachy (see genericSuperType assignment at start of while loop)
                currentClass = currentClass.getSuperclass();
            }

            mNullView = NoOp.of(viewClass);
        } catch (Throwable t) {
            throw new IllegalArgumentException(
                    "The generic type <V extends MvpView> must be the first generic type argument of class " +
                            getClass().getSimpleName() +
                            " (per convention). Otherwise we can't determine which type of View this" +
                            " Presenter coordinates.", t);
        }
    }

    /**
     * Scans the interface inheritnace hierarchy and checks if on the root is MvpView.class.
     *
     * @param klass The leaf interface where to begin to scan
     * @return true if subtype of MvpView, otherwise false
     */
    private boolean isSubTypeOfMvpView(Class<?> klass) {
        if (klass.equals(MvpView.class)) {
            return true;
        }
        Class[] superInterfaces = klass.getInterfaces();
        for (int i = 0; i < superInterfaces.length; i++) {
            if (isSubTypeOfMvpView(superInterfaces[0])) {
                return true;
            }
        }
        return false;
    }

    @UiThread
    @Override
    public void attachView(@NonNull V view) {
        this.mView = new WeakReference<V>(view);
    }

    @UiThread
    @NonNull
    protected V getView() {
        if (mView != null) {
            V realView = mView.get();
            if (realView != null) {
                return realView;
            }
        }

        return mNullView;
    }

    @UiThread
    @Override
    public void detachView(boolean retainInstance) {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }
}
