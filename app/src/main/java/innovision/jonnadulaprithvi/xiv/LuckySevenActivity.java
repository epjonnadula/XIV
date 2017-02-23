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

public class LuckySevenActivity extends AppCompatActivity {

    private TextView textView_activity_lucky_seven;
    private Button button_activity_lucky_seven;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_seven);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHelper(this);
        textView_activity_lucky_seven=(TextView)findViewById(R.id.textView_activity_lucky_seven);
        button_activity_lucky_seven=(Button)findViewById(R.id.button_activity_lucky_seven);
        textView_activity_lucky_seven.setMovementMethod(ScrollingMovementMethod.getInstance());

        String s="One has to bet the bucks up to 50 and they can challenge whether the rolled dice will be less than or greater than or equal to 7. If they win the bet of greater than or less than 7 they get two times the bet money.If they win the bet on equal to 7 they will get 4 times the bet money.";

        textView_activity_lucky_seven.setText(s);

        final Activity activity=this;
        button_activity_lucky_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.getEventHistory("event13")<5)
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
                        Toast.makeText(LuckySevenActivity.this, "Sorry. No enough bucks.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(LuckySevenActivity.this, "Maximum limit exceeded", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null)
        {
            if (result.getContents()==null)
                Toast.makeText(LuckySevenActivity.this, "You cancelled the scan", Toast.LENGTH_SHORT).show();

            else
            {
                if (result.getContents().substring(0,9).equals("Jonnadula")&&result.getContents().substring(9,10).equals("-"))
                {
                    Intent myIntent = new Intent(LuckySevenActivity.this, FinalGameActivity.class);
                    LuckySevenActivity.this.startActivity(myIntent);
                    String qr_result=result.getContents();
                    qr_result=qr_result.substring(10);
                    Toast.makeText(LuckySevenActivity.this, qr_result+" bucks detected.", Toast.LENGTH_SHORT).show();
                    int value=Integer.parseInt(result.getContents().substring(9));
                    int old_value=db.getBucks();
                    int new_value=old_value+value;
                    db.updateBucks(new_value);
                    db.setEventHistory("event13");
                }
                else
                    Toast.makeText(LuckySevenActivity.this, "Scan correct QR Code", Toast.LENGTH_SHORT).show();
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
                Intent myIntent = new Intent(LuckySevenActivity.this, RulesActivity.class);
                LuckySevenActivity.this.startActivity(myIntent);
                break;

            case R.id.home:
                Intent myIntent4 = new Intent(LuckySevenActivity.this, MainActivity.class);
                LuckySevenActivity.this.startActivity(myIntent4);
                break;
            case R.id.mydata:
                Intent myIntentproblem=new Intent(LuckySevenActivity.this,MyDataActivity.class);
                LuckySevenActivity.this.startActivity(myIntentproblem);
                break;
            case R.id.event_history:
                Intent myIntent5 = new Intent(LuckySevenActivity.this, HistoryActivity.class);
                LuckySevenActivity.this.startActivity(myIntent5);
                break;
            case R.id.contact:
                Intent myIntent6 = new Intent(LuckySevenActivity.this, ContactActivity.class);
                LuckySevenActivity.this.startActivity(myIntent6);
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(LuckySevenActivity.this, MainActivity.class);
        LuckySevenActivity.this.startActivity(myIntent);
        //super.onBackPressed();
    }
}
