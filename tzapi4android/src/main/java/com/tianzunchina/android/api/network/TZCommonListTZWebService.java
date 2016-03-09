package com.tianzunchina.android.api.network;

import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.tianzunchina.android.api.view.InstalList;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class TZCommonListTZWebService extends TZWebService {
	private final InstalList instalList;

	public TZCommonListTZWebService(InstalList instalList, Handler handler){
		super(handler);
		this.instalList = instalList;
	}
	
	public void getList(int skip, int task, ArrayMap<String, Object> map) {
		SoapObject soapObject = new SoapObject(instalList.getNamespace(), instalList.getName());
		if(instalList.getSkipNumber() != null){
			soapObject.addProperty(instalList.getSkipNumber(), skip);
		}
		if(instalList.getTakeNumber() != null){
			soapObject.addProperty(instalList.getTakeNumber(), task);
		}
		if(map != null){
			for(int i = 0; i < map.size(); i++){
				soapObject.addProperty(map.keyAt(i), map.valueAt(i));
			}
		}
		SoapSerializationEnvelope envelope = getEnvelope(soapObject);
		Message msg = new Message();
		msg.what = skip;
		try {
			HttpTransportSE transport = new HttpTransportSE(instalList.getWebServiceUrl(), instalList.getTimeOut());
			transport.call(instalList.getNamespace() + instalList.getName(), envelope);
			String json = envelope.getResponse().toString();
			Log.i("andy","接口返回数据--->"+json.toString());
			JSONObject body = getJsonBody(json);
			msg.arg1 = 1;
			msg.arg2 = skip;
			msg.obj = body;
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			msg.arg1 = -1;
		} catch (ConnectTimeoutException e) {
			msg.arg1 = -1;
		} catch (JSONException e) {
			e.printStackTrace();
			msg.arg1 = 0;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			msg.arg1 = - 2;
		} catch (SoapFault soapFault) {
			soapFault.printStackTrace();
			msg.arg1 = - 2;
		} catch (HttpResponseException e) {
			msg.arg1 = - 1;
			e.printStackTrace();
		} catch (IOException e) {
			msg.arg1 = - 1;
			e.printStackTrace();
		}catch (Exception e) {
			msg.arg1 = - 10;
			e.printStackTrace();
		} finally {
			handler.sendMessage(msg);
		}
	}
}