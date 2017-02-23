package innovision.jonnadulaprithvi.xiv;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    private ListView listView;
    private String names[] = {"Eshwar Prithvi Jonnadula","Anil Kumar Kasaragadda","Shashank Srivastava","Pudipeddi Pramod",
            "Vijay Teja","Abhinav Dantalapally","Kittu Kiran GV","NSSK Suraj","Velampudi Kishore","Balakrishna Routhu","Yaswanth Chimakurthi",
            "Surya Devraj","Sai Sampath Kedari","Hemanth Kumar Dasari"};


    private Integer imageid[]={R.drawable.eshwar_opt,R.drawable.anil_opt,R.drawable.shashank_opt,R.drawable.pramod_opt,
            R.drawable.vijay_opt,R.drawable.abhinav_opt,R.drawable.kittu_opt,R.drawable.suraj_opt,R.drawable.kishore_opt,R.drawable.bala_opt,R.drawable.yaswanth_opt,
            R.drawable.surya_opt,R.drawable.sampath_opt,R.drawable.hemanth_opt};

    private String post[] = {"App Developer","Coordinator","Coordinator","Coordinator","Coordinator","Coordinator",
            "Coordinator","Coordinator","Coordinator","Coordinator","Coordinator","Coordinator","Coordinator","Coordinator"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomerAdapter customerAdapter=new CustomerAdapter(this,names,post,imageid);
        listView = (ListView) findViewById(R.id.listViewcontact);
        listView.setAdapter(customerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 7077105393"));
                    startActivity(intent);
                }
                else if (position==1)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 9437723789"));
                    startActivity(intent);
                }
                else if (position==2)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 9437725597"));
                    startActivity(intent);
                }
                else if (position==3)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 9493884602"));
                    startActivity(intent);
                }
                else if (position==4)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 9437724492"));
                    startActivity(intent);
                }
                else if (position==5)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 9078827243"));
                    startActivity(intent);
                }
                else if (position==6)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 9490870310"));
                    startActivity(intent);
                }
                else if (position==7)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 8908581324"));
                    startActivity(intent);
                }
                else if (position==8)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 9437724539"));
                    startActivity(intent);
                }
                else if (position==9)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 9078827242"));
                    startActivity(intent);
                }
                else if (position==10)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 7702196154"));
                    startActivity(intent);
                }
                else if (position==11)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 8596049629"));
                    startActivity(intent);
                }
                else if (position==12)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 9437724478"));
                    startActivity(intent);
                }
                else if (position==13)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91 9083504397"));
                    startActivity(intent);
                }






            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(ContactActivity.this, MainActivity.class);
        ContactActivity.this.startActivity(myIntent);
        //super.onBackPressed();
    }
}