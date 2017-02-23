package innovision.jonnadulaprithvi.xiv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    private TextView textView_activity_rules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView_activity_rules=(TextView)findViewById(R.id.textView_activity_rules);
        textView_activity_rules.setMovementMethod(ScrollingMovementMethod.getInstance());


        String s="•This event is conducted at 9:30am to 3:00pm on 5th and 6th November.\n\n" +
                    "• After registering in app,your account is credited with 1000 bucks.\n\n" +
                "• Each day 7 games will be held and to participate in a game you have to scan a QR code which will deduct some bucks from your  account.\n\n" +
                "• The player wins the bucks by playing the games and the bucks will be credited to your account after scanning the QR code.\n\n" +
                "• QR codes will be scanned only by the co-ordinators.\n\n" +
                "• Each game can be played by a maximum of 5 times.\n\n" +
                "• Auction will be held at end of 2nd day.To participate in auction you have to play a minimum of 10 games.\n\n" +
                "• You have to update your points at XIV registration counter before leaving event venue.\n\n";
        textView_activity_rules.setText(s);
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(RulesActivity.this, MainActivity.class);
        RulesActivity.this.startActivity(myIntent);
        //super.onBackPressed();
    }
}
