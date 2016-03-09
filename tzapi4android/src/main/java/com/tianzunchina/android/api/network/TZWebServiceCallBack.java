package com.tianzunchina.android.api.network;

import java.io.IOException;

/**
 * Created by admin on 2016/3/8 0008.
 */
interface TZWebServiceCallBack {

    void onFailure(TZRequest TZRequest, IOException e);

    void onResponse(String response) throws IOException;
}
