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

public class GreedyKnightActivity extends AppCompatActivity {

    private TextView textView_activity_greedy_knight;
    private Button button_activity_greedy_knight;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greedy_knight);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHelper(this);
        textView_activity_greedy_knight=(TextView)findViewById(R.id.textView_activity_greedy_knight);
        button_activity_greedy_knight=(Button)findViewById(R.id.button_activity_greedy_knight);
        textView_activity_greedy_knight.setMovementMethod(ScrollingMovementMethod.getInstance());

        String s="Game last for two minutes and one has to pick at least 25 coins to qualify by moving the knight in 'L' shape. According to player's will he may collect more coins or he may return to the same place where he started without stepping on empty checks. For every coin he collects he get 10 bucks.";
        textView_activity_greedy_knight.setText(s);

        final Activity activity=this;
        button_activity_greedy_knight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.getEventHistory("event4")<5)
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
                        Toast.makeText(GreedyKnightActivity.this, "Sorry. No enough bucks.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(GreedyKnightActivity.this, "Maximum limit exceeded", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null)
        {
            if (result.getContents()==null)
                Toast.makeText(GreedyKnightActivity.this, "You cancelled the scan", Toast.LENGTH_SHORT).show();

            else
            {
                if (result.getContents().substring(0,9).equals("Jonnadula")&&result.getContents().substring(9,10).equals("-"))
                {
                    Intent myIntent = new Intent(GreedyKnightActivity.this, FinalGameActivity.class);
                    GreedyKnightActivity.this.startActivity(myIntent);
                    String qr_result=result.getContents();
                    qr_result=qr_result.substring(10);
                    Toast.makeText(GreedyKnightActivity.this, qr_result+" bucks detected.", Toast.LENGTH_SHORT).show();
                    int value=Integer.parseInt(result.getContents().substring(9));
                    int old_value=db.getBucks();
                    int new_value=old_value+value;
                    db.updateBucks(new_value);
                    db.setEventHistory("event4");
                }
                else
                    Toast.makeText(GreedyKnightActivity.this, "Scan correct QR Code", Toast.LENGTH_SHORT).show();
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
                Intent myIntent = new Intent(GreedyKnightActivity.this, RulesActivity.class);
                GreedyKnightActivity.this.startActivity(myIntent);
                break;

            case R.id.home:
                Intent myIntent4 = new Intent(GreedyKnightActivity.this, MainActivity.class);
                GreedyKnightActivity.this.startActivity(myIntent4);
                break;
            case R.id.mydata:
                Intent myIntentproblem=new Intent(GreedyKnightActivity.this,MyDataActivity.class);
                GreedyKnightActivity.this.startActivity(myIntentproblem);
                break;
            case R.id.event_history:
                Intent myIntent5 = new Intent(GreedyKnightActivity.this, HistoryActivity.class);
                GreedyKnightActivity.this.startActivity(myIntent5);
                break;
            case R.id.contact:
                Intent myIntent6 = new Intent(GreedyKnightActivity.this, ContactActivity.class);
                GreedyKnightActivity.this.startActivity(myIntent6);
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(GreedyKnightActivity.this, MainActivity.class);
        GreedyKnightActivity.this.startActivity(myIntent);
        //super.onBackPressed();
    }

}
