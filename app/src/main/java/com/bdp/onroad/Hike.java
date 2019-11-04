package com.bdp.onroad;

public class Hike
{

   private String mName,mStartingTime,mStartingPlace,mDestination,mNoOfSeats,mContactNumber,mDriverEmail;

    public Hike(String name, String startingTime, String startingPlace, String destination, String noOfSeats, String contactNumber,String driveremail)
    {
        this.mName = name;
        this.mStartingTime = startingTime;
        this.mStartingPlace = startingPlace;
        this.mDestination = destination;
        this.mNoOfSeats = noOfSeats;
        this.mContactNumber=contactNumber;
        this.mDriverEmail=driveremail;

    }
    // for firebase an empty constructor

    public Hike() { }

    // getters

    public String getmName()
    {
        return mName;
    }

    public String getmStartingTime()
    {
        return mStartingTime;
    }

    public String getmStartingPlace()
    {
        return mStartingPlace;
    }

    public String getmDestination()
    {
        return mDestination;
    }

    public String getmNoOfSeats()
    {
        return mNoOfSeats;
    }

    public String getmContactNumber()
    {
        return mContactNumber;
    }

    public String getmDriverEmail() {return mDriverEmail;}
}
