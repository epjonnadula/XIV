package innovision.jonnadulaprithvi.xiv;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class FinalGameActivity extends AppCompatActivity {

    DatabaseHelper db;
    private TextView final_game_bucks;
    private Button finish_game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_game);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        db = new DatabaseHelper(this);
        final_game_bucks=(TextView)findViewById(R.id.final_game_bucks);
        finish_game=(Button)findViewById(R.id.finish_game);
        final_game_bucks.setText(String.valueOf(db.getBucks()));
        final Activity activity=this;
        finish_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator=new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.initiateScan();
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null)
        {
            if (result.getContents()==null)
                Toast.makeText(FinalGameActivity.this, "You cancelled the scan", Toast.LENGTH_SHORT).show();

            else
            {
                String qr_result=result.getContents();
                if (qr_result.substring(0,9).equals("Jonnadula")&&qr_result.substring(9,10).equals("-"))
                {
                    qr_result=qr_result.substring(10);
                    Toast.makeText(FinalGameActivity.this, qr_result+" bucks detected", Toast.LENGTH_SHORT).show();
                    int value=Integer.parseInt(result.getContents().substring(9));
                    int old_value=db.getBucks();
                    int new_value=old_value+value;
                    db.updateBucks(new_value);
                    Intent myIntent = new Intent(FinalGameActivity.this, MainActivity.class);
                    FinalGameActivity.this.startActivity(myIntent);
                }
                else if (qr_result.substring(0,9).equals("Jonnadula")&&qr_result.substring(9,10).equals("+"))
                {
                    qr_result=qr_result.substring(10);
                    Toast.makeText(FinalGameActivity.this, qr_result+" bucks credited", Toast.LENGTH_SHORT).show();
                    int value=Integer.parseInt(result.getContents().substring(9));
                    int old_value=db.getBucks();
                    int new_value=old_value+value;
                    db.updateBucks(new_value);
                    Intent myIntent = new Intent(FinalGameActivity.this, MainActivity.class);
                    FinalGameActivity.this.startActivity(myIntent);
                }
                else
                    Toast.makeText(FinalGameActivity.this, "Scan correct QR Code", Toast.LENGTH_SHORT).show();
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }
    protected void onPause() {
        super.onPause();

        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);
    }

}
