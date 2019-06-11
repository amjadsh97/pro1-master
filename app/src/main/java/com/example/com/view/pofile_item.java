package com.example.com.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Instant;

public class pofile_item extends AppCompatActivity {

    ImageView  img;
    TextView name;
    TextView Quantity;
    TextView priceInsid;
    TextView priceOutsid;

    Button mAdditem;
    Button cancal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pofile_item);

        img           = (ImageView) findViewById(R.id.Addimage);
        name          = (TextView)  findViewById(R.id.Add_name);
        Quantity      = (TextView)  findViewById(R.id.AddQuantity);
        priceInsid    = (TextView)  findViewById(R.id.AddPriceInsid);
        priceOutsid   = (TextView)  findViewById(R.id.AddpriceOutsid);

        mAdditem =(Button)findViewById(R.id.Add_item);
        cancal   =(Button)findViewById(R.id.Add_canal);


        mAdditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String aname     = name.getText().toString();
                int  aQuantity   = Integer.parseInt(Quantity.getText().toString());
                int apriceInsid  = Integer.parseInt(priceInsid.getText().toString());;
                int apriceOutsid = Integer.parseInt(priceOutsid.getText().toString());;


                Intent intent = new Intent(pofile_item.this, List_Group.class);
                intent.putExtra("name", aname);
                intent.putExtra("Quantity", aQuantity);
                intent.putExtra("priceInsid", apriceInsid);
                intent.putExtra("priceOutsid", apriceOutsid);

                startActivity(intent);

            }
        });


        cancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setView(view);
                builder.setTitle("Information input");
                builder.setMessage(" Enter Name the Group.");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                Toast.makeText(pofile_item.this, "group", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
