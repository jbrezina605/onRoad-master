package com.bdp.onroad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UserRiderActivity extends BaseActivity
{
    String mContact_Number;
    Date date1 = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String date = dateFormat.format(date1);
    private AutoCompleteTextView mDestinationSearchText;
    private HikeListAdapter mAdapter;
    private DatabaseReference mDatabaseRefrence;
    private ListView mHikeListView;
    public String Destination;
   // private LinearLayout mSingleHikeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        Destination=extras.getString("destination");

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_user_rider, null, false);
        dl.addView(contentView, 0);

        mDatabaseRefrence= FirebaseDatabase.getInstance().getReference();
        mHikeListView=findViewById(R.id.hike_list_view);
        mDestinationSearchText=(AutoCompleteTextView)findViewById(R.id.rider_destination);
//        mHikeListView=(ListView) findViewById(R.id.hike_list_view);
        //mSingleHikeContainer=(LinearLayout)findViewById(R.id.singleHikeInfoContainer);

        mHikeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // getting hike object from the ListView's tapped Item
                Hike hike= mAdapter.getItem(position);
                Log.d("hey","You tapped on object with name:"+hike.getmName());

                Log.d("hey","on tapping bubble, driver we got was on email:"+hike.getmDriverEmail());

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String name = user.getDisplayName();
                String email= user.getEmail();
//                String mContact_Number;
                DatabaseReference db=FirebaseDatabase.getInstance().getReference();

                db=db.child("Users").child(alterToMakeFBPath(email)).child("mContactNumber");
                db.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        mContact_Number=dataSnapshot.getValue(String.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {

                    }
                });

                Hitch myNewHitch= new Hitch(name,email,mContact_Number,hike.getmDriverEmail());

                mDatabaseRefrence.child("Hitches").child(date).child(alterToMakeFBPath(hike.getmDriverEmail())).push().setValue(myNewHitch);

            }
        });

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

    @Override
    public void onStart()
    {
        super.onStart();
        mAdapter = new HikeListAdapter(this, mDatabaseRefrence,Destination);
        mHikeListView.setAdapter(mAdapter);

    }

    @Override
    public void onStop()
    {
        super.onStop();
        mAdapter.cleanUp();
        // TODO: Remove the Firebase event listener on the adapter.

    }
//    public void searchDestination(View v)
//    {
//        String destination=mDestinationSearchText.getText().toString();
//        if(destination=="")
//        {
//            new AlertDialog.Builder(this)
//                    .setTitle("Waoh")
//                    .setMessage("Enter Something to Search!")
//                    .setPositiveButton(android.R.string.ok,null)
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .show();
//        }
//        else
//        {
//            mAdapter = new HikeListAdapter(this, mDatabaseRefrence,destination);
//            mHikeListView.setAdapter(mAdapter);
//        }
//
//    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(UserRiderActivity.this, UserTypeActivity.class));
        finish();
    }
}