package proyecto.jonas.volleyimp.services;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import proyecto.jonas.volleyimp.utils.Utils;

public abstract class VolleyImp{

    private Context mContext;
    private RequestQueue mRequestQueue;
    private IVolleyCallback mResponseEvent;

    public VolleyImp(Context context,IVolleyCallback _responseEvent){
        this.mContext = context;
        this.mResponseEvent = _responseEvent;
    }

    void getJsonRequestasd(){
        mRequestQueue = Volley.newRequestQueue(mContext);

    }

    void getJsonRequest(){
        mRequestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(
                getMethod(),
                getUrl(),

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String formatData = formatResponseData(response);
                        mResponseEvent.onSuccess(  parseResponseData( formatData ) );
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleErrorVolleyResponse(error);
                }
        }){
            @Override
            public Map<String, String> getHeaders() {
                return setHeader();
            }
        };

        mRequestQueue.add(stringRequest);
    }

    /**
     * This Method can be override in a child class to pass
     * header values
     */
    protected Map setHeader(){
        Map<String,String> params = new HashMap<String, String>();
        return params;
    }

    /**
     * This Method can be override in a child class to handle errors
     * from service response
     * @param error
     */
    protected void handleErrorVolleyResponse(VolleyError error){
        Utils.alertError(mContext , "Error obteniendo data response en VolleyImp");
    }

    /**
     * This Method can be override in a child class to format the String response
     * and be converted to json
     * @param dataToFormat
     * @return dataFormat
     */
    protected String formatResponseData(String dataToFormat){
        return dataToFormat;
    }

    protected abstract String getUrl();

    protected abstract int getMethod();



    protected abstract HashMap parseResponseData(String jsonString);

}
