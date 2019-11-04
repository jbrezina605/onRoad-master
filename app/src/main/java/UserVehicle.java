public class UserVehicle
{
   private String mModelName;
   private String mRegisterationNumber;
   private String mColor;
    // TODO : upload car image


   // Constructor:
   UserVehicle(String modelname, String regNum, String Color)
   {
       mModelName=modelname;
       mRegisterationNumber=regNum;
       mColor=Color;
   }

   // Getters:

    public String getModelName() {
        return mModelName;
    }

    public String getRegisterationNumber() {
        return mRegisterationNumber;
    }

    public String getColor() {
        return mColor;
    }
}
