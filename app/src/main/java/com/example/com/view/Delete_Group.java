package com.example.com.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;

import java.util.ArrayList;

public class Delete_Group extends AppCompatActivity implements ItemaAdpater.OnItemClickListener {

    private RecyclerView mrecyclerView;
    private ItemaAdpater mitemAdpter;
    private SQLiteDatabase mDatabase;
    private ImageView NameGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__group);

        getSupportActionBar().setTitle(" Delete Group");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mrecyclerView = (RecyclerView) findViewById(R.id.Delete_Screen);

        NameGroup     = (ImageView)      findViewById(R.id.Bimg_group);

        DataBaseSQLite dbHelper = new DataBaseSQLite(this);
        mDatabase = dbHelper.getWritableDatabase();


        mitemAdpter =new ItemaAdpater(this, getAllItems() ,5,this);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerView.setAdapter(mitemAdpter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id=(long) viewHolder.itemView.getTag();

                Delete_Dialog Dialog = new Delete_Dialog();
                Dialog.SetLong(Delete_Group.this,id);
                Dialog.show(getSupportFragmentManager(), "example dialog");
            }
        }).attachToRecyclerView(mrecyclerView);




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

    private void removeItem(long id) {
        mDatabase.delete(GroupContantDB.GroupEntry.TABLE_NAME,
                GroupContantDB.GroupEntry._ID + "=" + id, null);
        mitemAdpter.swapCursor(getAllItems());
    }



    @Override
    public void onItemClick(int item) {


    }


}
