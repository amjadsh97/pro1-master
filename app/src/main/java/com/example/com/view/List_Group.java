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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class List_Group extends AppCompatActivity implements GroupDialog.GroupDialogListener, ItemaAdpater.OnItemClickListener {

    private SQLiteDatabase LDatabase;
    private RecyclerView LrecyclerView;
    private ItemaAdpater LitemAdpter;
    FloatingActionButton fab;

    private ArrayList<Item> AllIist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__group);

        getSupportActionBar().setTitle("List Group");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        LrecyclerView = (RecyclerView) findViewById(R.id.list_Screen);
        fab           = (FloatingActionButton)findViewById(R.id.List_fab);

        DatdBasaSQlist dbHelper = new DatdBasaSQlist(this);
        LDatabase = dbHelper.getWritableDatabase();

        LitemAdpter =new ItemaAdpater(this,  getAllItems(),1,this);
        LrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LrecyclerView.setAdapter(LitemAdpter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(List_Group.this,pofile_item.class);
                Toast.makeText(List_Group.this, "Add item into list", Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
            }
        });
        if(getIntent().getExtras() != null) {

            Intent mov = getIntent();
            String name     = mov.getStringExtra("name");
            int  Quantity   = mov.getIntExtra("Quantity",0);
            int priceInsid  = mov.getIntExtra("priceInsid",0);
            int priceOutsid = mov.getIntExtra("priceOutsid",0);
            insertDB(name,Quantity,priceInsid,priceOutsid);
        }


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //Delete_Dialog Dialog = new Delete_Dialog();
                //Dialog.show(getSupportFragmentManager(), "example dialog");
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(LrecyclerView);

    }

    private Cursor getAllItems() {
        return LDatabase.query(
                listConatntDB.ListEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                listConatntDB.ListEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }

    private void removeItem(long id) {
        LDatabase.delete(listConatntDB.ListEntry.TABLE_NAME,
                listConatntDB.ListEntry._ID + "=" + id, null);
        LitemAdpter.swapCursor(getAllItems());
    }

    private void insertDB(String Name, int quantity, int priceInsid, int priceOutsid)
    {
        if (Name.length() == 0 ) {
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(listConatntDB.ListEntry.COLUMN_NAME, Name);
        cv.put(listConatntDB.ListEntry.COLUMN_Quantity, quantity);
        cv.put(listConatntDB.ListEntry.COLUMN_priceInsid, priceInsid);
        cv.put(listConatntDB.ListEntry.COLUMN_priceOutsid, priceOutsid);

        LDatabase.insert(listConatntDB.ListEntry.TABLE_NAME, null, cv);
        LitemAdpter.swapCursor(getAllItems());
    }

    @Override
    public void applyTexts(String NameGroup) {

    }

    @Override
    public void onItemClick(int item) {

    }
}
