package bicepsa.vanko.traveling_project;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by user on 22.5.2017 г..
 */

public class Information extends AppCompatActivity {


    String loc_name;
    String country;
    String web_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Bundle check = getIntent().getExtras();

        loc_name = check.getString("name");
        country = check.getString("country");
        web_info = check.getString("web_info");

        TextView name = (TextView) findViewById(R.id.name);
        name.setText("Information about " + loc_name);

        TextView wiki = (TextView) findViewById(R.id.wiki);
        wiki.setText("https://en.wikipedia.org/wiki/" + loc_name);

        TextView weather = (TextView) findViewById(R.id.weather);
        weather.setText("http://www.holiday-weather.com/country/" + country);

        TextView info = (TextView) findViewById(R.id.info);
        info.setText("http://www.nationsonline.org/oneworld/" + web_info + ".htm");

        Button button = (Button)findViewById(R.id.return_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(start);
            }
        });
    }


}
