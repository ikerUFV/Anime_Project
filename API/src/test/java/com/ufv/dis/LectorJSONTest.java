package com.ufv.dis;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LectorJSONTest {

    LectorJSON lector;

    @Before
    public void SetUp(){
        lector = new LectorJSON();
    }

    @Test
    public void testLeerJson() {
        assertNotNull(lector.leerJson().get(0).getTitle());
    }
}