package bicepsa.vanko.traveling_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class NewDestination extends AppCompatActivity {

    private String url = "https://travel-project69.herokuapp.com/places";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_destination);

        Button button = (Button)findViewById(R.id.Random);
        sendRequest(null);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(start);
            }
        });

        Button europe = (Button)findViewById(R.id.Europe);
        europe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendRequest("Europe");
                Intent start = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(start);
            }
        });

        Button asia = (Button)findViewById(R.id.Asia);
        asia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendRequest("Asia");
                Intent start = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(start);
            }
        });

    }

    private void sendRequest(String params) {
        if (params != null){
            url += "?continent="+ params;
        }

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest request = new JsonObjectRequest( url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    SharedPreferences data = getSharedPreferences("DestinationData",MODE_PRIVATE);
                    SharedPreferences.Editor editor = data.edit();
                    editor.putString("lat",response.getString("latitude"));
                    editor.putString("longit",response.getString("longitude"));
                    editor.putString("name",response.getString("name"));
                    editor.putString("country",response.getString("country"));
                    editor.putString("web_info",response.getString("web_info"));
                    editor.apply();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.getMessage());
            }
        });
        queue.add(request);
    }
}
