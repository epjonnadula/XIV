package innovision.jonnadulaprithvi.xiv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eshwar Prithvi on 16-Aug-16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String database_name="xiv.db";
    public static final String info_table="info_table";
    public static final String terms_condition="terms_condition";
    public static final String bucks_table="bucks_table";
    public static final String event_history="event_history";
    public static final String col1="name";
    public static final String col2="id";
    public static final String col3="college";
    public static final String col4="contact";


    public DatabaseHelper(Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table event_history "+
                "(event0 int,event1 int,event2 int,event3 int,event4 int,event5 int,event6 int,event7 int,event8 int,event9 int,event10 int,event11 int,event12 int,event13 int)"
        );
        ContentValues contentValues2=new ContentValues();
        contentValues2.put("event0", "0");
        contentValues2.put("event1", "0");
        contentValues2.put("event2", "0");
        contentValues2.put("event3", "0");
        contentValues2.put("event4", "0");
        contentValues2.put("event5", "0");
        contentValues2.put("event6", "0");
        contentValues2.put("event7", "0");
        contentValues2.put("event8", "0");
        contentValues2.put("event9", "0");
        contentValues2.put("event10", "0");
        contentValues2.put("event11", "0");
        contentValues2.put("event12", "0");
        contentValues2.put("event13", "0");
        db.insert(event_history, null, contentValues2);
        db.execSQL(
                "create table info_table " +
                        "(name text,id text,college text,contact text)"
        );
        db.execSQL(
                "create table bucks_table "+
                        "(bucks int)"
        );
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("bucks", "0");
        db.insert(bucks_table, null, contentValues1);
        db.execSQL(
                "create table terms_condition " +
                        "(value int)"
        );
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", "0");
        db.insert(terms_condition, null, contentValues);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+info_table);
        db.execSQL("DROP TABLE IF EXISTS " + terms_condition);
        db.execSQL("DROP TABLE IF EXISTS " +bucks_table);
        db.execSQL("DROP TABLE IF EXISTS "+event_history);

        onCreate(db);
    }
    public boolean insertInfo (String name,String id,String college, String contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("id", id);
        contentValues.put("college", college);
        contentValues.put("contact", contact);

        long a=db.insert(info_table, null, contentValues);
        if(a == -1)
            return false;
        else
            return true;
    }

    public Cursor getInfo()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] allColumns = new String[]{col1,col2,col3,col4};
        Cursor c = db.query(info_table, allColumns, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;

    }


    public void updateValue(int value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE terms_condition SET value = "+value);
    }
    public int getValue()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] allColumns = new String[]{"value"};
        Cursor c = db.query(terms_condition, allColumns, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();

        }
        return Integer.parseInt(c.getString(0));

    }

    public void updateBucks(int bucks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE bucks_table SET bucks = "+bucks);
    }

    public int getBucks()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] allColumns = new String[]{"bucks"};
        Cursor c = db.query(bucks_table, allColumns, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();

        }
        return Integer.parseInt(c.getString(0));

    }
    public int getEventHistory(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT "+name+" FROM "+event_history, null);
        c.moveToFirst();
        return Integer.parseInt(c.getString(0));
    }

    public void setEventHistory(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int value=getEventHistory(name)+1;
        db.execSQL("UPDATE event_history SET "+name+"="+value);
    }
}
