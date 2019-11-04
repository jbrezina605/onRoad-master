package com.bdp.onroad;



import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HitchListAdapter extends BaseAdapter
{

    Date date1 = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String date = dateFormat.format(date1);
    private Activity mActivity;
    private DatabaseReference mDatabaseReference;

    private ArrayList<DataSnapshot> mSnapShotList;

    private ChildEventListener mChildEventListener=new ChildEventListener()
    {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
        {
            mSnapShotList.add(dataSnapshot);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
        {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot)
        {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
        {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError)
        {

        }
    };

    // Let us make some constructor!!!!!
    public HitchListAdapter(Activity activity, DatabaseReference ref)
    {
        mActivity=activity;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email=user.getEmail();
        mDatabaseReference=ref.child("Hitches").child(date).child(alterToMakeFBPath(email));
        mDatabaseReference.addChildEventListener(mChildEventListener);
        mSnapShotList= new ArrayList<>();
    }

    // Inner class to display rows better
    static class ViewHolder
    {
        TextView riderNameTextView,riderEmailTextView,ridercontactTextView;
        LinearLayout LinearHitchRow;
        LinearLayout.LayoutParams params;
    }

    @Override
    public int getCount()
    {
        return mSnapShotList.size();
    }

    @Override
    public Hitch getItem(int position)
    {
        DataSnapshot snapshot=mSnapShotList.get(position);
        return snapshot.getValue(Hitch.class);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.hitch_rows,parent,false);
            final ViewHolder holder= new ViewHolder();
            holder.riderNameTextView=(TextView) convertView.findViewById(R.id.riderName);
            holder.riderEmailTextView=(TextView)convertView.findViewById(R.id.riderEmail);
            holder.ridercontactTextView=(TextView)convertView.findViewById(R.id.riderContactNumber);

            holder.LinearHitchRow=(LinearLayout)convertView.findViewById(R.id.singleHitchInfoContainer);

            holder.params=(LinearLayout.LayoutParams)holder.riderEmailTextView.getLayoutParams();
            convertView.setTag(holder);
        }
        final Hitch hitch=getItem(position);
        final ViewHolder holder =(ViewHolder)convertView.getTag();

        setChatRowAppearance(holder);

        String ridername=hitch.getmName();
        holder.riderNameTextView.setText(ridername);

        String rideremail=hitch.getmEmail();
        holder.riderEmailTextView.setText(rideremail);

        String contact=hitch.getmContactNumber();
        holder.ridercontactTextView.setText(contact);

        return convertView;
    }

    private void setChatRowAppearance(ViewHolder holder)
    {
        holder.params.gravity= Gravity.START;
        holder.LinearHitchRow.setBackgroundResource(R.drawable.bubble2);
        holder.riderNameTextView.setLayoutParams(holder.params);
        holder.riderEmailTextView.setLayoutParams(holder.params);
        holder.ridercontactTextView.setLayoutParams(holder.params);
        holder.LinearHitchRow.setLayoutParams(holder.params);
    }
    public void cleanUp()
    {
        mDatabaseReference.removeEventListener(mChildEventListener);
    }

    // Helper Functions:
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