package com.tianzunchina.android.api.view.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * 通用列表适配器
 * Created by sl on 2015/10/21.
 */
public abstract class TZCommonAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int layoutId;

    /**
     *
     * @param context
     * @param datas 列表数据
     * @param layoutId item布局
     */
    public TZCommonAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TZViewHolder holder = TZViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        convert(holder, getItem(position), position);
        return holder.getConvertView();
    }

    public abstract void convert(TZViewHolder holder, T t, int position);
}