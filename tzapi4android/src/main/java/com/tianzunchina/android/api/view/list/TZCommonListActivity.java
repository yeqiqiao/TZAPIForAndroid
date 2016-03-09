package com.tianzunchina.android.api.view.list;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tianzunchina.android.api.R;
import com.tianzunchina.android.api.network.TZCommonListTZWebService;
import com.tianzunchina.android.api.utils.TimeConverter;
import com.tianzunchina.android.api.view.InstalList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sl on 2015/10/22.
 */
public abstract class TZCommonListActivity<T> extends Activity implements XListView.IXListViewListener,
        View.OnClickListener, AdapterView.OnItemClickListener {
    private InstalList instalList;
    public TextView tvLeft, tvTitle, tvRight;
    protected XListView mListView;
    protected List<T> listData;
    protected TZCommonAdapter<T> adapter;
    private TZCommonListTZWebService webService;
    private ArrayMap<String, Object> propertyMap;
    private int takeNumber = 10;
    private boolean isRefresh = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_list);
        initView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(isRefresh){
            this.onRefresh();
        }
    }

    /**
     *
     * 需要调用
     */
    protected void init(String webServiceUrl, String namespace, String name, String skipNumber,
                        String takeNumber, String jsonList, int timeOut, boolean isRefresh ){
        instalList = new InstalList(webServiceUrl, namespace, name, skipNumber, takeNumber, jsonList,
                timeOut);
        webService = new TZCommonListTZWebService(instalList, tzCommonListHandler);
        listData = new ArrayList<T>();
        this.isRefresh = isRefresh;
    }

    protected void init(String webServiceUrl, String namespace, String name, String skipNumber,
                        String takeNumber, String jsonList, int timeOut ){
        instalList = new InstalList(webServiceUrl, namespace, name, skipNumber, takeNumber, jsonList,
                timeOut);
        webService = new TZCommonListTZWebService(instalList, tzCommonListHandler);
        listData = new ArrayList<T>();
        init(webServiceUrl,namespace,name,skipNumber,takeNumber,jsonList,timeOut,true);
    }

    protected void setAdapter (TZCommonAdapter<T> adapter){
        this.adapter = adapter;
        mListView.setAdapter(adapter);
    }

    /**
     * 需调用设置title
     * @param title
     * @param leftName
     * @param rightName
     */
    protected void setTitle(String title, String leftName, String rightName){
        tvTitle.setText(title);
        setBtn(tvLeft, leftName);
        setBtn(tvRight, rightName);
    }

    /**
     * 需调用设置title 默认左右按钮分别为返回及刷新
     * @param title
     */
    protected void setTitle(String title){
        setTitle(title, "返回", "刷新");
    }

    protected void setBtn(TextView tv, String name){
        if(name == null){
            tv.setVisibility(View.INVISIBLE);
        } else {
            tv.setText(name);
        }
    }

    /**
     * 默认操作为关闭本页面
     * 点击titleBar 左边按钮效果
     */
    protected void clickLeft(){
        finish();
    }

    /**
     * 默认操作为调用onRefresh方法
     * 点击titleBar 右边按钮效果
     */
    protected void clickRight(){
        onRefresh();
    }

    protected void setWebServicePropertys(ArrayMap<String, Object> map){
        propertyMap = map;
    }

    protected ArrayMap<String, Object> getWebServicePropertys(){
        return propertyMap;
    }

    /**
     * json转换对象
     */
    protected abstract T json2Obj(JSONObject json);

    protected void setTakeNumber(int take){
        takeNumber = take;
    }

    protected void setData(int skip, List<T> list) {
        if (skip == 0) {
            listData.clear();
            listData.addAll(list);
            mListView.setRefreshTime(TimeConverter.INSTANCE.date2Str(new Date(),"MM-dd HH:mm"));
        } else {
            for (T t : list) {
                if (!listData.contains(t)) {
                    listData.add(t);
                }
            }
        }
        if (takeNumber == list.size()) {
            mListView.setPullLoadEnable(true);
        } else {
            mListView.setPullLoadEnable(false);
        }
        adapter.notifyDataSetChanged();
    }


    private void initView() {
        tvLeft = (TextView) findViewById(R.id.tvBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvRight = (TextView) findViewById(R.id.tvList);

        mListView = (XListView) findViewById(R.id.list);
        tvLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        mListView.setOnItemClickListener(this);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
    }

    @Override
    public void onRefresh() {
        new Thread(){
            @Override
            public void run() {
                webService.getList(0, takeNumber, getWebServicePropertys());
            }
        }.start();
    }

    @Override
    public void onLoadMore() {
        new Thread(){
            @Override
            public void run() {
                webService.getList(listData.size(), takeNumber, getWebServicePropertys());
            }
        }.start();
    }

    private void stopLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tvBack) {
            clickLeft();
        } else if (i == R.id.tvList) {
            clickRight();

        }
    }

    private Handler tzCommonListHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String info = "";
            switch (msg.arg1) {
                case 1:
                    try {
                        JSONObject body = (JSONObject) msg.obj;
                        JSONArray jsonList = body.getJSONArray(instalList.getJsonListName());
                        int size = jsonList.length();
                        List<T> list = new ArrayList<T>();
                        if (size > 0) {
                            for (int i = 0; i < size; i++) {
                                T t = json2Obj(jsonList.getJSONObject(i));
                                list.add(t);
                            }
                        } else {
                            info = "抱歉，暂无数据！";
                        }
                        setData(msg.arg2, list);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        info = "数据异常！\n请稍后重新尝试";
                    }
                    break;
                case 0:
                    info = "服务器异常！\n请稍后重新尝试";
                    break;
                case -1:
                    info = "网络异常！\n请稍后重新尝试";
                    break;
                default:
                    info = "遇到未知错误！\n请稍后重新尝试";
                    break;
            }
            stopLoad();
            if (!info.equals("")) {
                Toast.makeText(TZCommonListActivity.this, info, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ){
            this.finish();
        }
        return false;
    }

    protected void setDivider(int height, int colorID){
        Drawable drawable  = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = getResources().getDrawable(colorID, this.getTheme());
        } else {
            drawable =  getResources().getDrawable(colorID);
        }
        mListView.setDivider(drawable);
        mListView.setDividerHeight(height);
    }
}
