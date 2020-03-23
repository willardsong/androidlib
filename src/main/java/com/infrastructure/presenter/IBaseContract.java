package com.infrastructure.presenter;

/**
 * @author weijingsong
 * @date 2020/3/12
 */
public interface IBaseContract {

    /**
     * The interface Base view.
     *
     * @param <T> the type parameter
     */
    interface IBaseView<T> {
        /**
         * setPresenter
         *
         * @param t the t
         */
        void setPresenter(T t);
    }

        interface IBasePresenter {
        /**
         * onCreate
         */
        void onCreate();

        /**
         * onDestroy
         */
        void onDestroy();
    }
}
