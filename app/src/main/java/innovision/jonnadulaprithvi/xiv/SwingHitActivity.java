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

public class SwingHitActivity extends AppCompatActivity {

    private TextView textView_activity_swing_hit;
    private Button button_activity_swing_hit;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swing_hit);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHelper(this);
        textView_activity_swing_hit=(TextView)findViewById(R.id.textView_activity_swing_hit);
        button_activity_swing_hit=(Button)findViewById(R.id.button_activity_swing_hit);
        textView_activity_swing_hit.setMovementMethod(ScrollingMovementMethod.getInstance());

        String s="A bob is tied to a stick with a string and is given to the player. The player must be holding the stick with mouth and he has to swing the pendulum to hit the glasses which are numbered.\n" +
                "The glasses should be hit in the order of numbers given. The player is given 3 minutes and will be given 100 bucks for successful completion of game.";

        textView_activity_swing_hit.setText(s);

        final Activity activity=this;
        button_activity_swing_hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.getEventHistory("event12")<5)
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
                        Toast.makeText(SwingHitActivity.this, "Sorry. No enough bucks.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(SwingHitActivity.this, "Maximum limit exceeded", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null)
        {
            if (result.getContents()==null)
                Toast.makeText(SwingHitActivity.this, "You cancelled the scan", Toast.LENGTH_SHORT).show();

            else
            {
                if (result.getContents().substring(0,9).equals("Jonnadula")&&result.getContents().substring(9,10).equals("-"))
                {
                    Intent myIntent = new Intent(SwingHitActivity.this, FinalGameActivity.class);
                    SwingHitActivity.this.startActivity(myIntent);
                    String qr_result=result.getContents();
                    qr_result=qr_result.substring(10);
                    Toast.makeText(SwingHitActivity.this, qr_result+" bucks detected.", Toast.LENGTH_SHORT).show();
                    int value=Integer.parseInt(result.getContents().substring(9));
                    int old_value=db.getBucks();
                    int new_value=old_value+value;
                    db.updateBucks(new_value);
                    db.setEventHistory("event12");
                }
                else
                    Toast.makeText(SwingHitActivity.this, "Scan correct QR Code", Toast.LENGTH_SHORT).show();
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
                Intent myIntent = new Intent(SwingHitActivity.this, RulesActivity.class);
                SwingHitActivity.this.startActivity(myIntent);
                break;

            case R.id.home:
                Intent myIntent4 = new Intent(SwingHitActivity.this, MainActivity.class);
                SwingHitActivity.this.startActivity(myIntent4);
                break;
            case R.id.mydata:
                Intent myIntentproblem=new Intent(SwingHitActivity.this,MyDataActivity.class);
                SwingHitActivity.this.startActivity(myIntentproblem);
                break;
            case R.id.event_history:
                Intent myIntent5 = new Intent(SwingHitActivity.this, HistoryActivity.class);
                SwingHitActivity.this.startActivity(myIntent5);
                break;
            case R.id.contact:
                Intent myIntent6 = new Intent(SwingHitActivity.this, ContactActivity.class);
                SwingHitActivity.this.startActivity(myIntent6);
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(SwingHitActivity.this, MainActivity.class);
        SwingHitActivity.this.startActivity(myIntent);
        //super.onBackPressed();
    }
}
