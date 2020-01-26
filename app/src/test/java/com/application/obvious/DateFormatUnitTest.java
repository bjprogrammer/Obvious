package com.application.obvious;

import com.application.obvious.utils.HelperFunctions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DateFormatUnitTest {
    @Test
    public void date_isCorrect() {
        String actualDate = HelperFunctions.formatDate("2019-01-26");
        assertEquals("26-01-2019", actualDate);
    }
}