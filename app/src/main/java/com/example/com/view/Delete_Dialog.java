package com.example.com.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

public class Delete_Dialog extends AppCompatDialogFragment {

    SQLiteDatabase LDatabase;
    private Context Context;
    long id;

    public void SetLong(Context context, long id) {
        this.Context = context;
        this.id = id;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Information ");
        builder.setMessage(" Are you sure you want to delete the entire group data?");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ItemaAdpater LitemAdpter = null;
                DataBaseSQLite dbHelper = new DataBaseSQLite(Context);
                LDatabase = dbHelper.getWritableDatabase();
                LDatabase.delete(GroupContantDB.GroupEntry.TABLE_NAME,
                        GroupContantDB.GroupEntry._ID + "=" + id, null);
                LitemAdpter.swapCursor(getAllItems());
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        return builder.create();
    }


    private Cursor getAllItems() {
        return LDatabase.query(
                GroupContantDB.GroupEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                GroupContantDB.GroupEntry.COLUMN_TIMESTAMP + " DESC"
        );

    }
}
