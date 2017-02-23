package innovision.jonnadulaprithvi.xiv;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Button register;
    private EditText name,id,college,contact;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        db=new DatabaseHelper(this);
        register=(Button)findViewById(R.id.register);
        name=(EditText)findViewById(R.id.name);
        id=(EditText)findViewById(R.id.id);
        college=(EditText)findViewById(R.id.college);
        contact=(EditText)findViewById(R.id.contact);

        if(db.getValue()==0) {

            //For commit the changes, Use either editor.commit(); or  editor.apply();.
            db.updateValue(1);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (name.getText().toString().equals("")||id.getText().toString().equals("")||
                            college.getText().toString().equals("")||contact.getText().toString().equals(""))
                        Toast.makeText(RegisterActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    else
                    {
                        if (db.insertInfo(name.getText().toString(),id.getText().toString(),college.getText().toString(),contact.getText().toString()));
                        Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
                        RegisterActivity.this.startActivity(myIntent);
                        Toast.makeText(RegisterActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                        db.updateBucks(1000);
                    }
                }
            });


        }
        else
        {
            Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
            RegisterActivity.this.startActivity(myIntent);
        }

    }
    public void onBackPressed() {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(RegisterActivity.this);
        a_builder.setMessage("Do you want to Close this App !!!")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.updateValue(0);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = a_builder.create();
        alert.setTitle("Alert");
        alert.show();
        //super.onBackPressed();
    }
}
