package com.example.com.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GroupDialog.GroupDialogListener, ItemaAdpater.OnItemClickListener {

    private RecyclerView mrecyclerView;
    private ItemaAdpater mitemAdpter;
    private SQLiteDatabase mDatabase;
    private ImageView NameGroup;
    private ArrayList<String> AllIist;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(" Main view");

        mrecyclerView = (RecyclerView) findViewById(R.id.main_Screen);
        fab           = (FloatingActionButton)findViewById(R.id.fab);
        NameGroup     = (ImageView)      findViewById(R.id.Bimg_group);

        DataBaseSQLite dbHelper = new DataBaseSQLite(this);
        mDatabase = dbHelper.getWritableDatabase();
        AllIist=new ArrayList<>();

        mitemAdpter =new ItemaAdpater(this, getAllItems() ,0,this);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerView.setAdapter(mitemAdpter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroupDialog exampleDialog = new GroupDialog();
                exampleDialog.show(getSupportFragmentManager(), "example dialog");
                Toast.makeText(MainActivity.this, "group", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Cursor getAllItems() {
        return mDatabase.query(
                GroupContantDB.GroupEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                GroupContantDB.GroupEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }

    private void insertDB(String NameGroup)
    {
        if (NameGroup.length() == 0 ) {
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(GroupContantDB.GroupEntry.COLUMN_NAME, NameGroup);
        cv.put(GroupContantDB.GroupEntry.COLUMN_TIMESTAMP, "5");

       mDatabase.insert(GroupContantDB.GroupEntry.TABLE_NAME, null, cv);
       mitemAdpter.swapCursor(getAllItems());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater flater= getMenuInflater();
        flater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent;
        switch( item.getItemId()){
            case R.id.list:
                Toast.makeText(this, "Manage the menu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.group:
                GroupDialog exampleDialog = new GroupDialog();
                exampleDialog.show(getSupportFragmentManager(), "example dialog");
                Toast.makeText(this, "group", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Deleting:
                myIntent = new Intent(MainActivity.this,Delete_Group.class);
                Toast.makeText(this, "Delete Item of List", Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
            return true;
            case R.id.Expense:
                Toast.makeText(this, "Expense", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.inside:
                 myIntent = new Intent(MainActivity.this,Inside_Expense.class);
                Toast.makeText(this, "inside Expense", Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
                return true;
            case R.id.Outside:
                 myIntent = new Intent(MainActivity.this,Outside_Expense.class);
                Toast.makeText(this, "Outside Expense", Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
                return true;
            case R.id.Profit:
                myIntent = new Intent(MainActivity.this,Net_profit.class);
                Toast.makeText(this, " Net profit", Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
                return true;
            case R.id.consumed:
                myIntent = new Intent(MainActivity.this,Most_consumed.class);
                Toast.makeText(this, "Most consumed", Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void applyTexts(String NameGroup) {
           insertDB(NameGroup);
    }

    @Override
    public void onItemClick(int item) {
      //  Toast.makeText(this, "Manage the menu", Toast.LENGTH_SHORT).show();

    }
}
