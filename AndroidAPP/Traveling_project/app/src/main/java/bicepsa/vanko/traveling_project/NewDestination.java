package bicepsa.vanko.traveling_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewDestination extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_destination);

        Button button = (Button)findViewById(R.id.Random);
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
                Intent start = new Intent(getApplicationContext(),MapsActivity.class);
                start.putExtra("params","Europe");
                startActivity(start);
            }
        });

        Button asia = (Button)findViewById(R.id.Asia);
        asia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent start = new Intent(getApplicationContext(),MapsActivity.class);
                start.putExtra("params","Asia");
                startActivity(start);
            }
        });

    }
}
