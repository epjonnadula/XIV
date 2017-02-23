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

public class GeometricDumbsheradsActivity extends AppCompatActivity {

    private TextView textView_activity_geometric_dumbsherads;
    private Button button_activity_geometric_dumbsherads;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometric_dumbsherads);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHelper(this);
        textView_activity_geometric_dumbsherads=(TextView)findViewById(R.id.textView_activity_geometric_dumbsherads);
        button_activity_geometric_dumbsherads=(Button)findViewById(R.id.button_activity_geometric_dumbsherads);
        textView_activity_geometric_dumbsherads.setMovementMethod(ScrollingMovementMethod.getInstance());

        String s="A team of two are selected and a geometric diagram is given to one of the team mate. The team mate should explain the diagram without using the hands.The other team mate should draw the diagram similarly.\nFor the successful completion of the task 150 bucks are awarded for both the team mates.";
        textView_activity_geometric_dumbsherads.setText(s);

        final Activity activity=this;
        button_activity_geometric_dumbsherads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.getEventHistory("event2")<5)
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
                        Toast.makeText(GeometricDumbsheradsActivity.this, "Sorry. No enough bucks.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(GeometricDumbsheradsActivity.this, "Maximum limit exceeded", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null)
        {
            if (result.getContents()==null)
                Toast.makeText(GeometricDumbsheradsActivity.this, "You cancelled the scan", Toast.LENGTH_SHORT).show();

            else
            {
                if (result.getContents().substring(0,9).equals("Jonnadula")&&result.getContents().substring(9,10).equals("-"))
                {
                    Intent myIntent = new Intent(GeometricDumbsheradsActivity.this, FinalGameActivity.class);
                    GeometricDumbsheradsActivity.this.startActivity(myIntent);
                    String qr_result=result.getContents();
                    qr_result=qr_result.substring(10);
                    Toast.makeText(GeometricDumbsheradsActivity.this, qr_result+" bucks detected.", Toast.LENGTH_SHORT).show();
                    int value=Integer.parseInt(result.getContents().substring(9));
                    int old_value=db.getBucks();
                    int new_value=old_value+value;
                    db.updateBucks(new_value);
                    db.setEventHistory("event2");
                }
                else
                    Toast.makeText(GeometricDumbsheradsActivity.this, "Scan correct QR Code", Toast.LENGTH_SHORT).show();
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
                Intent myIntent = new Intent(GeometricDumbsheradsActivity.this, RulesActivity.class);
                GeometricDumbsheradsActivity.this.startActivity(myIntent);
                break;

            case R.id.home:
                Intent myIntent4 = new Intent(GeometricDumbsheradsActivity.this, MainActivity.class);
                GeometricDumbsheradsActivity.this.startActivity(myIntent4);
                break;
            case R.id.mydata:
                Intent myIntentproblem=new Intent(GeometricDumbsheradsActivity.this,MyDataActivity.class);
                GeometricDumbsheradsActivity.this.startActivity(myIntentproblem);
                break;
            case R.id.event_history:
                Intent myIntent5 = new Intent(GeometricDumbsheradsActivity.this, HistoryActivity.class);
                GeometricDumbsheradsActivity.this.startActivity(myIntent5);
                break;
            case R.id.contact:
                Intent myIntent6 = new Intent(GeometricDumbsheradsActivity.this, ContactActivity.class);
                GeometricDumbsheradsActivity.this.startActivity(myIntent6);
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(GeometricDumbsheradsActivity.this, MainActivity.class);
        GeometricDumbsheradsActivity.this.startActivity(myIntent);
        //super.onBackPressed();
    }

}
