package com.tianzunchina.android.api.network;

import android.os.Handler;
import android.support.v4.util.ArrayMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class TZWebService {
    protected static final int TIME_OUT = 30000;
    protected Handler handler;

    protected TZWebService(Handler handler) {
        this.handler = handler;
    }

    protected TZWebService() {
    }

    /**
     * 调用
     * @param url
     * @param paramMap
     * @param wsType webServiceType
     * @param callBack
     */
    protected void call(String url, ArrayMap<String, Object> paramMap, int wsType,
                        TZWebServiceCallBack callBack){
    }


    protected SoapSerializationEnvelope getEnvelope(SoapObject soapObject) {
        System.setProperty("http.keepAlive", "false");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);
        return envelope;
    }

    public JSONObject getJsonBody(Object obj) throws JSONException {
        String json = (String) obj;
        JSONObject dataJson;
        dataJson = new JSONObject(json);
        return dataJson.getJSONObject("Body");
    }


    public boolean isEnable(JSONObject body) throws JSONException {
        int errCode = body.getInt("ErrorCount");
        return errCode == 0;
    }

    public String postURL(String url, String param) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] datas;
        String msg = "";
        HttpClient client = null;
        try {
            InputStream instream = null;
            HttpPost request = new HttpPost(url);
            StringEntity paramEntity = new StringEntity(param, HTTP.UTF_8);
            paramEntity.setContentType("text/xml");
            request.setHeader("Content-Type", "text/xml");
            request.setEntity(paramEntity);
            client = new DefaultHttpClient();
            HttpResponse response = client.execute(request);
            response.getStatusLine().getStatusCode();
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                instream = responseEntity.getContent();
                int bytesRead;
                datas = new byte[8192];
                while ((bytesRead = instream.read(datas, 0, 8192)) != -1) {
                    baos.write(datas, 0, bytesRead);
                }
                msg = new String(baos.toByteArray(), "utf-8");
            }
            try {
                if (instream != null) {
                    instream.close();
                }
                baos.close();
            } catch (Exception ignore) {
                ignore.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            shutdownHttpClient(client);
        }
        System.err.println("postURL:" + msg);
        return msg;
    }

    private void shutdownHttpClient(HttpClient hc) {
        if (hc != null && hc.getConnectionManager() != null) {
            hc.getConnectionManager().shutdown();
        }
    }
}