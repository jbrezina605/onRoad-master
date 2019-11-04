package com.bdp.onroad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.google.firebase.database.FirebaseDatabase;

public class activity_HikeSearch extends BaseActivity {
       private String destination;
       AutoCompleteTextView des1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_hike_search, null, false);
        dl.addView(contentView, 0);

        // NEW FROM HERE!!!
        des1= findViewById(R.id.rider_destination);

    }

    public void searchDestination(View v) {
        String destination = des1.getText().toString();
        if (destination == "") {
            new AlertDialog.Builder(this)
                    .setTitle("Waoh")
                    .setMessage("Enter Something to Search!")
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            super.onBackPressed();
            Intent intent=new Intent(activity_HikeSearch.this, UserRiderActivity.class);
            intent.putExtra("destination", destination);
            startActivity(intent);
            finish();
        }

        }
    }
