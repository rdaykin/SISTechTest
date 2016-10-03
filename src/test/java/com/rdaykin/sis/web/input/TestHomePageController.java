package com.rdaykin.sis.web.input;

import org.junit.Test;
import org.mockito.Mock;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rob on 03/10/2016.
 */
public class TestHomePageController {

    HomePageController controller = new HomePageController();

    @Mock
    Map<String,Object> model;

    @Test
    public void testHomePageMethodReturnsHomepage(){
        String homepage = controller.index(model);
        assertEquals("homepage",homepage);
    }
}
