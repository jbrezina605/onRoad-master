package com.bdp.onroad;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTest {

    @Test
    public void test1()  {

        Hike test = mock(Hike.class);

        when(test.getmName()).thenReturn("Jano");

        String string= test.getmName();

        assertEquals(string, "Jano");
        assertEquals(Utilities.checkEmptyLetters(string), false);
    }
}
