package innovision.jonnadulaprithvi.xiv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listView;


    DatabaseHelper db;
    private String names[] = {"Last Pick","Dart","Geometric Dumbsherads","Brownian Tennis","Greedy Knight","Irrelevant Answer",
            "Tag Throw","Electric Fence","Pin The Tail","Pass The Bomb","Spoon Sault","Black Jack","Swing Hit",
            "Lucky Seven" };


    private Integer imageid[] = {R.mipmap.handpick,R.mipmap.dart,R.mipmap.geo,R.mipmap.browian,R.mipmap.knight,
            R.mipmap.answer,R.mipmap.tag,R.mipmap.elece,R.mipmap.pinthetail,R.mipmap.bombpass,
            R.mipmap.spoonf,R.mipmap.jackblack,R.mipmap.hitswing,R.mipmap.dice7
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DatabaseHelper(this);
        CustomList customList = new CustomList(this,  names,imageid);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(customList);
        //Toast.makeText(MainActivity.this, ""+db.getValue(), Toast.LENGTH_SHORT).show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i==0)
                {
                        Intent myIntent = new Intent(MainActivity.this, PaperConnectionActivity.class);
                        MainActivity.this.startActivity(myIntent);



                }
                else if (i==1)
                {
                        Intent myIntent = new Intent(MainActivity.this, DartActivity.class);
                        MainActivity.this.startActivity(myIntent);

                }
                else if (i==2)
                {
                        Intent myIntent = new Intent(MainActivity.this, GeometricDumbsheradsActivity.class);
                        MainActivity.this.startActivity(myIntent);

                }

                else if (i==3)
                {
                        Intent myIntent = new Intent(MainActivity.this, BrownianTennisActivity.class);
                        MainActivity.this.startActivity(myIntent);
                }
                else if (i==4)
                {
                        Intent myIntent = new Intent(MainActivity.this, GreedyKnightActivity.class);
                        MainActivity.this.startActivity(myIntent);

                }
                else if (i==5)
                {
                        Intent myIntent = new Intent(MainActivity.this, IrrelevantAnswerActivity.class);
                        MainActivity.this.startActivity(myIntent);

                }
                else if (i==6)
                {
                        Intent myIntent = new Intent(MainActivity.this, TagThrowActivity.class);
                        MainActivity.this.startActivity(myIntent);

                }
                else if (i==7)
                {
                        Intent myIntent = new Intent(MainActivity.this, ElectricFenceActivity.class);
                        MainActivity.this.startActivity(myIntent);

                }
                else if (i==8)
                {
                        Intent myIntent = new Intent(MainActivity.this, PinTheTailActivity.class);
                        MainActivity.this.startActivity(myIntent);

                }
                else if (i==9)
                {
                        Intent myIntent = new Intent(MainActivity.this, PassTheBombActivity.class);
                        MainActivity.this.startActivity(myIntent);

                }
                else if (i==10)
                {
                        Intent myIntent = new Intent(MainActivity.this, SpoonSaultActivity.class);
                        MainActivity.this.startActivity(myIntent);

                }
                else if (i==11)
                {
                        Intent myIntent = new Intent(MainActivity.this, BlackJackActivity.class);
                        MainActivity.this.startActivity(myIntent);

                }
                else if (i==12)
                {
                        Intent myIntent = new Intent(MainActivity.this, SwingHitActivity.class);
                        MainActivity.this.startActivity(myIntent);

                }
                else if (i==13)
                {
                        Intent myIntent = new Intent(MainActivity.this, LuckySevenActivity.class);
                        MainActivity.this.startActivity(myIntent);


                }

            }


        });


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
                Intent myIntent = new Intent(MainActivity.this, RulesActivity.class);
                MainActivity.this.startActivity(myIntent);
                break;

            case R.id.home:
                Intent myIntent4 = new Intent(MainActivity.this, MainActivity.class);
                MainActivity.this.startActivity(myIntent4);
                break;
            case R.id.mydata:
                Intent myIntentproblem=new Intent(MainActivity.this,MyDataActivity.class);
                MainActivity.this.startActivity(myIntentproblem);
                break;
            case R.id.event_history:
                Intent myIntent5 = new Intent(MainActivity.this, HistoryActivity.class);
                MainActivity.this.startActivity(myIntent5);
                break;
            case R.id.contact:
                Intent myIntent6 = new Intent(MainActivity.this, ContactActivity.class);
                MainActivity.this.startActivity(myIntent6);

        }
        return true;
    }
    @Override
    public void onBackPressed() {
        final Activity activity=new Activity();
        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
        a_builder.setMessage("Do you want to Close this App !!!")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            finishAffinity();
                        }
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
