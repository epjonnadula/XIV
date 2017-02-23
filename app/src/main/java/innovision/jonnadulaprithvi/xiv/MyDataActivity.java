package innovision.jonnadulaprithvi.xiv;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyDataActivity extends AppCompatActivity {

    private EditText mydata_name,mydata_id,mydata_college,mydata_contact,mydata_bucks;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHelper(this);
        Cursor c = db.getInfo();
        String name=String.valueOf(c.getString(0));
        String id=String.valueOf(c.getString(1));
        String college=String.valueOf(c.getString(2));
        String contact=String.valueOf(c.getString(3));
        String bucks=String.valueOf(db.getBucks());

        c.moveToFirst();
        mydata_name=(EditText)findViewById(R.id.mydata_name);
        mydata_id=(EditText)findViewById(R.id.mydata_id);
        mydata_college=(EditText)findViewById(R.id.mydata_college);
        mydata_contact=(EditText)findViewById(R.id.mydata_contact);
        mydata_bucks=(EditText)findViewById(R.id.mydata_bucks);

        mydata_name.setText(name);
        mydata_id.setText(id);
        mydata_college.setText(college);
        mydata_contact.setText(contact);
        mydata_bucks.setText(bucks);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/tahomabd.ttf");

        mydata_name.setTypeface(custom_font);
        mydata_id.setTypeface(custom_font);
        mydata_college.setTypeface(custom_font);
        mydata_contact.setTypeface(custom_font);
        mydata_bucks.setTypeface(custom_font);



    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(MyDataActivity.this, MainActivity.class);
        MyDataActivity.this.startActivity(myIntent);
        //super.onBackPressed();
    }
}
