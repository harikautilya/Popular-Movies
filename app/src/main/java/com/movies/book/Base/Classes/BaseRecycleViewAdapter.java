package com.movies.book.Base.Classes;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kautilya on 06/02/18.
 */
public abstract class BaseRecycleViewAdapter<K extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<K> {


    public class BaseViewHolder<L extends ViewDataBinding> extends RecyclerView.ViewHolder {
        protected final String LOG_TAG = this.getClass().getName();
        L viewBinding;

        public BaseViewHolder(View itemView) {
            super(itemView);
            viewBinding = DataBindingUtil.bind(itemView);
        }

        public L getViewBinding() {
            return viewBinding;
        }
    }
}