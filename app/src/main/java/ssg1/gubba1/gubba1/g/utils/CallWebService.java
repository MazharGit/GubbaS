package ssg1.gubba1.gubba1.g.utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ssg1.gubba1.gubba1.g.AppController;
import ssg1.gubba1.gubba1.g.Login;
import ssg1.gubba1.gubba1.g.interfaces.IJsonParserInterface;
import ssg1.gubba1.gubba1.g.interfaces.IMultiPartParserInterface;

public class CallWebService implements Response.Listener<byte[]>, Response.ErrorListener{


	IJsonParserInterface jsonParsingInterface=null;
	IMultiPartParserInterface multiPartParserInterface;
	private static String TAG = CallWebService.class.getSimpleName();
	// Progress dialog
	private ProgressDialog pDialog;
	// temporary string to show the parsed response
	private String jsonResponse;

	Context context;
	public static JSONObject j=new JSONObject();
	SharedPref sp;

	public CallWebService(Context context) {
		System.out.println("OBJ "+context);
		this.context=context;
		sp=new SharedPref(context);
		pDialog = new ProgressDialog(context);
		pDialog.setMessage("Please wait...");
		//db=new VtsSqliteHelper(context);
	}

	/**
	 * Method to make json object request where json response starts wtih {
	 * */
	JSONObject responce=null;
	public void makeJsonObjectRequestPost(final String urlJsonObj, JSONObject jb, final boolean isShowProgress) {

		Log.d(TAG, urlJsonObj);
		if(CheckNetworkConnctivity.checkConnectivity(context)) {
			Log.d(TAG, jb.toString());
			try {
				jsonParsingInterface = (IJsonParserInterface) context;
			} catch (Exception e) {
			}
			if (isShowProgress)
				showpDialog();

			JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
					urlJsonObj, jb, new Response.Listener<JSONObject>() {

				@Override
				public void onResponse(JSONObject response) {
					Log.d(TAG, response.toString());

					try {
						if (isShowProgress)
							hidepDialog();

						j = response;

						jsonParsingInterface.parseJsonResult(response,
								urlJsonObj);

					} catch (NullPointerException e) {
						e.printStackTrace();
						if (isShowProgress)
							hidepDialog();
					}

				}

			}, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					VolleyLog.d(TAG, "Error: " + error.getMessage());
					if (isShowProgress)
						hidepDialog();
					try {
						switch (error.networkResponse.statusCode) {

							case 401: //Unauthorized
								Login.LUserName.setError("Enter Valid Username");
								Login.LPassword.setError("Enter Valid Password");
								Toast.makeText(context, "Enter Valid Usename and Password!", Toast.LENGTH_LONG).show();
							break;
							case 403: //Forbidden
								Toast.makeText(context, "Sorry, you shall not pass!", Toast.LENGTH_LONG).show();
								break;
							case 404: //Not Found
							case 400: //Bad Request
							case 405: //Method Not Allowed
								Toast.makeText(context, "Sorry, something went wrong!", Toast.LENGTH_LONG).show();
								break;
							case 408: //Request Timeout
								Toast.makeText(context, "Sorry, your request has timed-out. Please try again!", Toast.LENGTH_LONG).show();
								break;
							case 500: //Internal Server Error
								Toast.makeText(context, "Sorry, we have encountered an error, we are working on it!", Toast.LENGTH_LONG).show();
								break;
							case 502: //Bad Gateway
							case 503: //Service Unavailable
							case 504: //Gateway Timeout
								Toast.makeText(context, "Sorry, the servers are under maintenace. Please try again in few minutes.", Toast.LENGTH_LONG).show();
								break;
							case 0: //No internetconnection
								Toast.makeText(context, "Your internet connection may be super slow, bouncy or dead, please check!", Toast.LENGTH_LONG).show();
								break;
						}
					}catch (Exception e)
					{
						//Toast.makeText(context, "Your internet connection may be super slow, bouncy or dead, please check!", Toast.LENGTH_LONG).show();
					}

				}


			}) {
				@Override
				public Map<String, String> getHeaders() throws AuthFailureError {
					HashMap<String, String> params = new HashMap<String, String>();
					String creds = String.format("%s:%s", SharedPref.readString("Username", ""),SharedPref.readString("password", "") );
					String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
					params.put("Authorization", auth);
					return params;
				}
			};
			jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
					30000,
					DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
					DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


			// Adding request to request queue
			AppController.getInstance().addToRequestQueue(jsonObjReq);
		}else {
			Toast.makeText(context, "Please check your internet connection",
					Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * Method to make json array request where response starts with [
	 * */
	private void makeJsonArrayRequest(String urlJsonArry , final boolean isShowProgress) {
		if(isShowProgress)
			showpDialog();

		JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());

						try {


						} catch (NullPointerException e) {
							e.printStackTrace();
							/*Toast.makeText(getApplicationContext(),
									"Error: " + e.getMessage(),
									Toast.LENGTH_LONG).show();*/
						}
						if(isShowProgress)
							hidepDialog();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						/*Toast.makeText(getApplicationContext(),
								error.getMessage(), Toast.LENGTH_SHORT).show();*/
						if(isShowProgress)
							hidepDialog();
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req);
	}




	public void callWebserviceWithAuth(final String url, final Map<String, String> jb) {
		if(CheckNetworkConnctivity.checkConnectivity(context)) {
		RequestQueue queue = Volley.newRequestQueue(context);
		final IJsonParserInterface jsonParsingInterface = (IJsonParserInterface) context;
		StringRequest postRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response) {
						// response
						Log.d("Response", response);
						try {
							jsonParsingInterface.parseJsonResult(new JSONObject(response),
                                    url);
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				},
				new Response.ErrorListener()
				{
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.d("ERROR", "error => " + error.toString());
						Toast.makeText(context,"Please check your internet connection",
								Toast.LENGTH_SHORT).show();
						// hide the progress dialog

					}
				}
		) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();

				Log.d("ABCD ERROR", "ABCDEFGHIJKLMNOPQ");

				/*String authKey = null;
				try {
					authKey = new JSONObject(sp.readString("AUTH_KEY")).optJSONObject("data").optString("authKey").toString();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if(!DataValidation.isNullString(authKey)) {
					params.put("Authorization", "Bearer "+authKey);
					params.put("Content-Type", "application/json");
					params.put("Accept", "application/json");

				}*/


				return params;
			}

			/* @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String,String>();
                    params.put("device_id", "11111");
                    return params;
                }
    */
			@Override
			public String getBodyContentType() {
				return "application/json";
			}

		};



		queue.add(postRequest);
	}else {
		Toast.makeText(context, "Please check your internet connection",
				Toast.LENGTH_SHORT).show();
	}

	}




	public void makeJsonObjectRequestGet(final String urlJsonObj, final boolean isShowProgress) {

		Log.d(TAG, urlJsonObj);
		if(CheckNetworkConnctivity.checkConnectivity(context)) {

			try {
				jsonParsingInterface = (IJsonParserInterface) context;
			} catch (Exception e) {
			}
			if (isShowProgress)
				showpDialog();


			JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
					urlJsonObj, new Response.Listener<JSONObject>() {

				@Override
				public void onResponse(JSONObject response) {
					Log.d(TAG, response.toString());

					try {
						if (isShowProgress)
							hidepDialog();

						j = response;

						jsonParsingInterface.parseJsonResult(response,
								urlJsonObj);

					} catch (NullPointerException e) {
						e.printStackTrace();
						if (isShowProgress)
							hidepDialog();
					}

				}

			}, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					VolleyLog.d(TAG, "Error: " + error.getMessage());
					if (isShowProgress)
						hidepDialog();
					try {
						switch (error.networkResponse.statusCode) {

							case 401: //Unauthorized
								Toast.makeText(context, "Your session has expired. Please enter Valid Usename and Password!", Toast.LENGTH_LONG).show();
								break;
							case 403: //Forbidden
								Toast.makeText(context, "Sorry, you shall not pass!", Toast.LENGTH_LONG).show();
								break;
							case 404: //Not Found
							case 400: //Bad Request
							case 405: //Method Not Allowed
								Toast.makeText(context, "Sorry, something went wrong!", Toast.LENGTH_LONG).show();
								break;
							case 408: //Request Timeout
								Toast.makeText(context, "Sorry, your request has timed-out. Please try again!", Toast.LENGTH_LONG).show();
								break;
							case 500: //Internal Server Error
								Toast.makeText(context, "Sorry, we have encountered an error, we are working on it!", Toast.LENGTH_LONG).show();
								break;
							case 502: //Bad Gateway
							case 503: //Service Unavailable
							case 504: //Gateway Timeout
								Toast.makeText(context, "Sorry, the servers are under maintenace. Please try again in few minutes.", Toast.LENGTH_LONG).show();
								break;
							case 0: //No internetconnection
								Toast.makeText(context, "Your internet connection may be super slow, bouncy or dead, please check!", Toast.LENGTH_LONG).show();
								break;
						}
					}catch (Exception e)
					{
						//Toast.makeText(context, "Your internet connection may be super slow, bouncy or dead, please check!", Toast.LENGTH_LONG).show();
					}

				}


			}) {
				@Override
				public Map<String, String> getHeaders() throws AuthFailureError {
					HashMap<String, String> params = new HashMap<String, String>();
					String creds = String.format("%s:%s", SharedPref.readString("Username", ""),SharedPref.readString("password", "") );
					String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
					params.put("Authorization", auth);
					return params;
				}
			};
			jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
					30000,
					DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
					DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


			// Adding request to request queue
			AppController.getInstance().addToRequestQueue(jsonObjReq);
		}else {
			Toast.makeText(context, "Please check your internet connection",
					Toast.LENGTH_SHORT).show();
		}

	}





	private void showpDialog() {
		try {

			if (!pDialog.isShowing())
				pDialog.show();
		}catch (Exception e){}
	}

	private void hidepDialog() {
		try {
		if (pDialog.isShowing())
			pDialog.dismiss();
	}catch (Exception e){}
	}


	InputStreamVolleyRequest request;


	public void makeJFileRequest(final String urlJsonObj, HashMap<String, String> params, final boolean isShowProgress) {

		if(CheckNetworkConnctivity.checkConnectivity(context)) {
			System.out.println("callWebService"+urlJsonObj);


			if (isShowProgress)
				showpDialog();

			request = new InputStreamVolleyRequest(Request.Method.POST, urlJsonObj, this, this, params);
			request.setRetryPolicy(new DefaultRetryPolicy(
					600000,
					DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
					DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
			AppController.getInstance().addToRequestQueue(request);
		}else {
				Toast.makeText(context, "Please check your internet connection",
						Toast.LENGTH_SHORT).show();
			}
	}




	@Override
	public void onResponse(byte[] response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			multiPartParserInterface = (IMultiPartParserInterface) context;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			hidepDialog();
		}catch (Exception e){e.printStackTrace();}

		try {
			//System.out.println("DEBUG::RESUME 2"+ new String(response, "UTF-8"));

			if (response!=null) {

				multiPartParserInterface.parseMultiPartResult(request,response,request.getUrl());

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.d("KEY_ERROR", "UNABLE TO DOWNLOAD FILE");
			e.printStackTrace();
		}
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		Log.d("KEY_ERROR", "UNABLE TO DOWNLOAD FILE. ERROR:: "+error.getMessage());

		try {
			hidepDialog();
		}catch (Exception e){e.printStackTrace();}


		try {
			switch (error.networkResponse.statusCode) {

				case 401: //Unauthorized
					Toast.makeText(context, "Your session has expired. Please enter Valid Usename and Password!", Toast.LENGTH_LONG).show();
					break;
				case 403: //Forbidden
					Toast.makeText(context, "Sorry, you shall not pass!", Toast.LENGTH_LONG).show();
					break;
				case 404: //Not Found
				case 400: //Bad Request
				case 405: //Method Not Allowed
					Toast.makeText(context, "Sorry, something went wrong!", Toast.LENGTH_LONG).show();
					break;
				case 408: //Request Timeout
					Toast.makeText(context, "Sorry, your request has timed-out. Please try again!", Toast.LENGTH_LONG).show();
					break;
				case 500: //Internal Server Error
					Toast.makeText(context, "Sorry, we have encountered an error, we are working on it!", Toast.LENGTH_LONG).show();
					break;
				case 502: //Bad Gateway
				case 503: //Service Unavailable
				case 504: //Gateway Timeout
					Toast.makeText(context, "Sorry, the servers are under maintenace. Please try again in few minutes.", Toast.LENGTH_LONG).show();
					break;
				case 0: //No internetconnection
					Toast.makeText(context, "Your internet connection may be super slow, bouncy or dead, please check!", Toast.LENGTH_LONG).show();
					break;
			}
		}catch (Exception e)
		{
			//Toast.makeText(context, "Your internet connection may be super slow, bouncy or dead, please check!", Toast.LENGTH_LONG).show();
		}

	}

}
