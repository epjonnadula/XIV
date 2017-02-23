package innovision.jonnadulaprithvi.xiv;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BlackJackActivity extends AppCompatActivity {

    private TextView textView_activity_black_jack;
    private Button button_activity_black_jack;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHelper(this);
        textView_activity_black_jack=(TextView)findViewById(R.id.textView_activity_black_jack);
        button_activity_black_jack=(Button)findViewById(R.id.button_activity_black_jack);
        textView_activity_black_jack.setMovementMethod(ScrollingMovementMethod.getInstance());

        String s="The game is to be played by maximum five players and minimum 2 players. Every player is given a card randomly. The player may ask more cards if necessary. The sum of card must not exceed 33.The game terminates when all the players are not willing to take any cards. The player with highest sum will be awarded 150 bucks. ";

        textView_activity_black_jack.setText(s);

        final Activity activity=this;
        button_activity_black_jack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.getEventHistory("event11")<5)
                {
                    if (db.getBucks()>=50)
                    {
                        IntentIntegrator integrator=new IntentIntegrator(activity);
                        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                        integrator.setPrompt("Scan");
                        integrator.setCameraId(0);
                        integrator.setBeepEnabled(false);
                        integrator.initiateScan();
                    }
                    else
                        Toast.makeText(BlackJackActivity.this, "Sorry. No enough bucks.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(BlackJackActivity.this, "Maximum limit exceeded", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null)
        {
            if (result.getContents()==null)
                Toast.makeText(BlackJackActivity.this, "You cancelled the scan", Toast.LENGTH_SHORT).show();

            else
            {
                if (result.getContents().substring(9,10).equals("-")&&result.getContents().substring(0,9).equals("Jonnadula"))
                {

                    Intent myIntent = new Intent(BlackJackActivity.this, FinalGameActivity.class);
                    BlackJackActivity.this.startActivity(myIntent);
                    String qr_result=result.getContents();
                    qr_result=qr_result.substring(10);
//                    Toast.makeText(BlackJackActivity.this, ""+qr_result, Toast.LENGTH_SHORT).show();
                    Toast.makeText(BlackJackActivity.this, qr_result+" bucks detected.", Toast.LENGTH_SHORT).show();
                    int value=Integer.parseInt(result.getContents().substring(9));
                    int old_value=db.getBucks();
                    int new_value=old_value+value;
                    db.updateBucks(new_value);
                    db.setEventHistory("event11");
                }
                else
                    Toast.makeText(BlackJackActivity.this, "Scan correct QR Code", Toast.LENGTH_SHORT).show();
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.rules:
                Intent myIntent = new Intent(BlackJackActivity.this, RulesActivity.class);
                BlackJackActivity.this.startActivity(myIntent);
                break;

            case R.id.home:
                Intent myIntent4 = new Intent(BlackJackActivity.this, MainActivity.class);
                BlackJackActivity.this.startActivity(myIntent4);
                break;
            case R.id.mydata:
                Intent myIntentproblem=new Intent(BlackJackActivity.this,MyDataActivity.class);
                BlackJackActivity.this.startActivity(myIntentproblem);
                break;
            case R.id.event_history:
                Intent myIntent5 = new Intent(BlackJackActivity.this, HistoryActivity.class);
                BlackJackActivity.this.startActivity(myIntent5);
                break;
            case R.id.contact:
                Intent myIntent6 = new Intent(BlackJackActivity.this, ContactActivity.class);
                BlackJackActivity.this.startActivity(myIntent6);
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(BlackJackActivity.this, MainActivity.class);
        BlackJackActivity.this.startActivity(myIntent);
        //super.onBackPressed();
    }

}
