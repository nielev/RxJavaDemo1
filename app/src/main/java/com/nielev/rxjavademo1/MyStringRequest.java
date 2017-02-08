package com.nielev.rxjavademo1;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Author: Nielev
 * <p/>
 * Company: Oray
 * <p/>
 * Date: 2016/6/22 10:43.
 */
public class MyStringRequest extends StringRequest {
    public MyStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public MyStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }
}
