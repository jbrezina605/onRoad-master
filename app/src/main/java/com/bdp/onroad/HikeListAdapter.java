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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HikeListAdapter extends BaseAdapter
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
    public HikeListAdapter(Activity activity, DatabaseReference ref, String destination)
    {
        mActivity=activity;
        mDatabaseReference=ref.child("Hikes").child(date).child(alterToMakeFBPath(destination.replaceAll("\\s+","").toLowerCase()));
        mDatabaseReference.addChildEventListener(mChildEventListener);
        mSnapShotList= new ArrayList<>();
    }

    // Inner class to display rows better
    static class ViewHolder
    {
        TextView destinationTextView,timeTextView,seatNumberTextView,startPlaceTextView,driverNameTextView,contactTextView;
        LinearLayout LinearHikeRow;
        LinearLayout.LayoutParams params;
    }

    @Override
    public int getCount()
    {
        return mSnapShotList.size();
    }

    @Override
    public Hike getItem(int position)
    {
        DataSnapshot snapshot=mSnapShotList.get(position);
        return snapshot.getValue(Hike.class);
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
            convertView=inflater.inflate(R.layout.hike_rows,parent,false);
            final ViewHolder holder= new ViewHolder();
            holder.destinationTextView=(TextView) convertView.findViewById(R.id.destinationOfHike);
            holder.timeTextView=(TextView)convertView.findViewById(R.id.timeOfHike);
            holder.seatNumberTextView=(TextView)convertView.findViewById(R.id.numberOfSeatsOfHike);
            holder.startPlaceTextView=(TextView)convertView.findViewById(R.id.startingPlaceOfHike);
            holder.driverNameTextView=(TextView)convertView.findViewById(R.id.driverNameOfHike);
            holder.LinearHikeRow=(LinearLayout)convertView.findViewById(R.id.singleHikeInfoContainer);
            holder.contactTextView=(TextView)convertView.findViewById(R.id.contactNumberOfHike);
            holder.params=(LinearLayout.LayoutParams)holder.destinationTextView.getLayoutParams();
            convertView.setTag(holder);
        }
        final Hike hike=getItem(position);
        final ViewHolder holder =(ViewHolder)convertView.getTag();

        //boolean isMe=message.getAuthor().equals(mDisplayName);
        //setChatRowAppearance(isMe,holder);
        setChatRowAppearance(holder);

//        //
//        String author=message.getAuthor();
//        holder.authorName.setText(author);
//
//        String msg=message.getMessage();
//        holder.body.setText(msg);
        String destination=hike.getmDestination();
        holder.destinationTextView.setText(destination);

        String time=hike.getmStartingTime();
        holder.timeTextView.setText(time);

        String seats=hike.getmNoOfSeats();
        holder.seatNumberTextView.setText(seats);

        String startPlace = hike.getmStartingPlace();
        holder.startPlaceTextView.setText(startPlace);

        String driverName = hike.getmName();
        holder.driverNameTextView.setText(driverName);

        String contactNumber = hike.getmContactNumber();
        holder.contactTextView.setText(contactNumber);

        return convertView;
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


    private void setChatRowAppearance(ViewHolder holder)
    {
        holder.params.gravity= Gravity.START;
        holder.LinearHikeRow.setBackgroundResource(R.drawable.bubble1);
        holder.destinationTextView.setLayoutParams(holder.params);
        holder.timeTextView.setLayoutParams(holder.params);
        holder.seatNumberTextView.setLayoutParams(holder.params);
        holder.startPlaceTextView.setLayoutParams(holder.params);
        holder.driverNameTextView.setLayoutParams(holder.params);
        holder.contactTextView.setLayoutParams(holder.params);
        holder.LinearHikeRow.setLayoutParams(holder.params);
    }
    public void cleanUp()
    {
        mDatabaseReference.removeEventListener(mChildEventListener);
    }
}

