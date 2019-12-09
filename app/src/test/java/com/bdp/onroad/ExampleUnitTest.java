package com.bdp.onroad;


import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
/*
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 3);
    }
*/
    RegisterActivity object= new RegisterActivity();
    Hike objectHike= new Hike("Jano", "00:12:23", "Kosice", "Humenne", "4", "0900123456", "driver@gmail.com");

    @Test
    public void testEmail_1() {
        assertEquals(true , object.isEmailValid("abc@gmail.com"));
    }

    @Test
    public void testHikeName_2() {
        assertEquals("Jano" , objectHike.getmName());
    }

    @Test
    public void testHikeStartingTime_3() {
        assertEquals("00:12:23" , objectHike.getmStartingTime());
    }

    @Test
    public void testHikeStartingPlace_4() {
        assertEquals("Kosice" , objectHike.getmStartingPlace());
    }

    @Test
    public void testHikeDestination_5() {
        assertEquals("Humenne" , objectHike.getmDestination());
    }

    @Test
    public void testHikeNoOfSeats_6() {
        assertEquals("4" , objectHike.getmNoOfSeats());
    }

    @Test
    public void testHikeContactNumber_7() {
        assertEquals("0900123456" , objectHike.getmContactNumber());
    }

    @Test
    public void testHikeDriverEmail_8() {
        assertEquals("driver@gmail.com" , objectHike.getmDriverEmail());
    }


}