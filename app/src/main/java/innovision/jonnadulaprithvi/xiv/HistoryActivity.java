package innovision.jonnadulaprithvi.xiv;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryActivity extends AppCompatActivity {

    DatabaseHelper db;
    TextView event0,event1,event2,event3,event4,event5,event6,event7,event8,event9,event10,event11,event12,event13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=new DatabaseHelper(this);

        event0=(TextView)findViewById(R.id.event0);
        event1=(TextView)findViewById(R.id.event1);
        event2=(TextView)findViewById(R.id.event2);
        event3=(TextView)findViewById(R.id.event3);
        event4=(TextView)findViewById(R.id.event4);
        event5=(TextView)findViewById(R.id.event5);
        event6=(TextView)findViewById(R.id.event6);
        event7=(TextView)findViewById(R.id.event7);
        event8=(TextView)findViewById(R.id.event8);
        event9=(TextView)findViewById(R.id.event9);
        event10=(TextView)findViewById(R.id.event10);
        event11=(TextView)findViewById(R.id.event11);
        event12=(TextView)findViewById(R.id.event12);
        event13=(TextView)findViewById(R.id.event13);

        event0.setText(String.valueOf(db.getEventHistory("event0")));
        event1.setText(String.valueOf(db.getEventHistory("event1")));
        event2.setText(String.valueOf(db.getEventHistory("event2")));
        event3.setText(String.valueOf(db.getEventHistory("event3")));
        event4.setText(String.valueOf(db.getEventHistory("event4")));
        event5.setText(String.valueOf(db.getEventHistory("event5")));
        event6.setText(String.valueOf(db.getEventHistory("event6")));
        event7.setText(String.valueOf(db.getEventHistory("event7")));
        event8.setText(String.valueOf(db.getEventHistory("event8")));
        event9.setText(String.valueOf(db.getEventHistory("event9")));
        event10.setText(String.valueOf(db.getEventHistory("event10")));
        event11.setText(String.valueOf(db.getEventHistory("event11")));
        event12.setText(String.valueOf(db.getEventHistory("event12")));
        event13.setText(String.valueOf(db.getEventHistory("event13")));

    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(HistoryActivity.this, MainActivity.class);
        HistoryActivity.this.startActivity(myIntent);
        //super.onBackPressed();
    }
}
