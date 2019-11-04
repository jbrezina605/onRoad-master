package com.bdp.onroad;

public class Hitch
{
    private String mName,mEmail,mContactNumber,mHikeId;

    //constructors
    public Hitch(String name, String email, String contactNumber, String hikeId)
    {
        mName = name;
        mEmail = email;
        mContactNumber = contactNumber;
        mHikeId= hikeId;
    }
    public Hitch() {}

    //getters


    public String getmName() {return mName;}

    public String getmEmail() {return mEmail;}

    public String getmContactNumber() {return mContactNumber;}

    public String getmHikeId() {return mHikeId;}
}

