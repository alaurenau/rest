package org.lavr.repository;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(locations = {"classpath:test-context.xml"})
public abstract class AbstractIntegrationTest extends AbstractTestNGSpringContextTests {

    @BeforeMethod
    public void setUp() throws Exception {
//        fail();
    }

}

