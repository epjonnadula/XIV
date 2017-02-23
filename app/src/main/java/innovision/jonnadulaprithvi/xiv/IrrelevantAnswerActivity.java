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

public class IrrelevantAnswerActivity extends AppCompatActivity {

    private TextView textView_activity_irrelevant_answer;
    private Button button_activity_irrelevant_answer;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irrelevant_answer);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHelper(this);
        textView_activity_irrelevant_answer=(TextView)findViewById(R.id.textView_activity_irrelevant_answer);
        button_activity_irrelevant_answer=(Button)findViewById(R.id.button_activity_irrelevant_answer);
        textView_activity_irrelevant_answer.setMovementMethod(ScrollingMovementMethod.getInstance());

        String s="Player is given two tennis balls to juggle them or a table tennis ball and bat with which he should continuously hit the ball into air. While doing this he is asked some questions for which he should spontaneously answer only the irrelevant answers.For example “What is the name of prime minister of India?” The answer should not relate to name.20 bucks are given for correct answer.";
        textView_activity_irrelevant_answer.setText(s);

        final Activity activity=this;
        button_activity_irrelevant_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.getEventHistory("event5")<5)
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
                        Toast.makeText(IrrelevantAnswerActivity.this, "Sorry. No enough bucks.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(IrrelevantAnswerActivity.this, "Maximum limit exceeded", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null)
        {
            if (result.getContents()==null)
                Toast.makeText(IrrelevantAnswerActivity.this, "You cancelled the scan", Toast.LENGTH_SHORT).show();

            else
            {
                if (result.getContents().substring(0,9).equals("Jonnadula")&&result.getContents().substring(9,10).equals("-"))
                {
                    Intent myIntent = new Intent(IrrelevantAnswerActivity.this, FinalGameActivity.class);
                    IrrelevantAnswerActivity.this.startActivity(myIntent);
                    String qr_result=result.getContents();
                    qr_result=qr_result.substring(10);
                    Toast.makeText(IrrelevantAnswerActivity.this, qr_result+" bucks detected.", Toast.LENGTH_SHORT).show();
                    int value=Integer.parseInt(result.getContents().substring(9));
                    int old_value=db.getBucks();
                    int new_value=old_value+value;
                    db.updateBucks(new_value);
                    db.setEventHistory("event5");
                }
                else
                    Toast.makeText(IrrelevantAnswerActivity.this, "Scan correct QR Code", Toast.LENGTH_SHORT).show();
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
                Intent myIntent = new Intent(IrrelevantAnswerActivity.this, RulesActivity.class);
                IrrelevantAnswerActivity.this.startActivity(myIntent);
                break;

            case R.id.home:
                Intent myIntent4 = new Intent(IrrelevantAnswerActivity.this, MainActivity.class);
                IrrelevantAnswerActivity.this.startActivity(myIntent4);
                break;
            case R.id.mydata:
                Intent myIntentproblem=new Intent(IrrelevantAnswerActivity.this,MyDataActivity.class);
                IrrelevantAnswerActivity.this.startActivity(myIntentproblem);
                break;
            case R.id.event_history:
                Intent myIntent5 = new Intent(IrrelevantAnswerActivity.this, HistoryActivity.class);
                IrrelevantAnswerActivity.this.startActivity(myIntent5);
                break;
            case R.id.contact:
                Intent myIntent6 = new Intent(IrrelevantAnswerActivity.this, ContactActivity.class);
                IrrelevantAnswerActivity.this.startActivity(myIntent6);
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(IrrelevantAnswerActivity.this, MainActivity.class);
        IrrelevantAnswerActivity.this.startActivity(myIntent);
        //super.onBackPressed();
    }
}
