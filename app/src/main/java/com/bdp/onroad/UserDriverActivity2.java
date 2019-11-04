package com.bdp.onroad;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bdp.onroad.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UserDriverActivity2 extends BaseActivity
{
    private HitchListAdapter mAdapter;
    private DatabaseReference mDatabaseRefrence;
    private ListView mHitchListView;
    private String destination;
    // private LinearLayout mSingleHikeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        destination=extras.getString("destination");

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_user_driver_2, null, false);
        dl.addView(contentView, 0);
        mDatabaseRefrence= FirebaseDatabase.getInstance().getReference();
        mHitchListView=(ListView) findViewById(R.id.hitch_list_view);
    }


    @Override
    public void onStart()
    {
        super.onStart();
        mAdapter=new HitchListAdapter(this,mDatabaseRefrence);
        mHitchListView.setAdapter(mAdapter);

    }

    @Override
    public void onStop()
    {
        super.onStop();
        mAdapter.cleanUp();
        // TODO: Remove the Firebase event listener on the adapter.

    }
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure you want to delete your request?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteRequest();
                        Intent intent = new Intent(UserDriverActivity2.this, UserTypeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void deleteRequest(){
        Date date1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date2 = dateFormat.format(date1);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email=user.getEmail();

        mDatabaseRefrence.child("Hikes").child(date2).child(alterToMakeFBPath(destination.replaceAll("\\s+","").toLowerCase())).child(alterToMakeFBPath(email)).removeValue();
        return;
    }


    private String alterToMakeFBPath(String str)
    {
        Log.d("hey","got here");
        String ret="";
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)=='.'||str.charAt(i)=='#'||str.charAt(i)=='$'||str.charAt(i)=='['||str.charAt(i)==']')
                ret+='_';

            else
                ret+=str.charAt(i);
        }
        return ret;
    }
}