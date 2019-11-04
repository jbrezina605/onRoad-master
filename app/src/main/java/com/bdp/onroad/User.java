
package com.bdp.onroad;
    public class User
    {

        // attributes given by user
        private String mName;
        private String mEmail;
        /* TODO: DEEPANKAR, Should we even store the passwords.
           TODO:            I mean Firebase and google has it secured
           TODO:            we can just use User's email for accessing data stored in DB.
         */
        // private String mPassword;
        private String mContactNumber;
//   private UserVehicle mUserVehicle;
//   private int mAge;
//   private String mSex;

        // attributes given by user potential driver
        // private String mLicense;

        //attributes given by us!
//   private int mDriverRating;
//   private int mRiderRating;
//   private float mTupons;
//   private float mTupees;
        // TODO: Add User Profile Photo

        // Constructor :
        User(String name, String email, String contactnumber)
        {
            mName=name;
//        mAge=age;
//        mSex=sex;
            mEmail=email;
//        mPassword=password;
            mContactNumber=contactnumber;
//        mUserVehicle=uservehicle;

        }
        // Setters:
//    public void setLicense(String license)
//    {
//        mLicense=license;
//    }
//
//    public void setDriverRating(int driverRating)
//    {
//        mDriverRating = driverRating;
//    }
//
//    public void setRiderRating(int riderRating)
//    {
//        mRiderRating = riderRating;
//    }
//
//    public void setTupons(float tupons)
//    {
//        mTupons = tupons;
//    }
//
//    public void setTupees(float tupees)
//    {
//        mTupees = tupees;
//    }


        // Getters:


        public String getmName()
        {
            return mName;
        }

        public String getmEmail()
        {
            return mEmail;
        }

        //   public String getPassword()
//    {
//        return mPassword;
//    }

        public String getmContactNumber()
        {
            return mContactNumber;
        }

//    public UserVehicle getUserVehicle()
//    {
//        return mUserVehicle;
//    }
//
//    public int getAge()
//    {
//        return mAge;
//    }
//
//    public String getSex()
//    {
//        return mSex;
//    }
//
//    public String getLicense()
//    {
//        return mLicense;
//    }
//
//    public int getDriverRating()
//    {
//        return mDriverRating;
//    }
//
//    public int getRiderRating()
//    {
//        return mRiderRating;
//    }
//
//    public float getTupons()
//    {
//        return mTupons;
//    }
//
//    public float getTupees()
//    {
//        return mTupees;
//    }
    }


