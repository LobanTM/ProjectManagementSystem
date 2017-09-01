package com.lobantm.hw02.DAO;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ConnectionDBTest {
    private Connection connection = null;

    @Before
    public void beforeTest(){
        connection = ConnectionDB.getConnection();
    }

    @Test
    public void getConnection() throws Exception {
        assertNotNull(connection );
    }

    @Test
    public void closeConnection() throws Exception {
        ConnectionDB.closeConnection();
        assertTrue( connection.isClosed());
    }

}