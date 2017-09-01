package com.lobantm.hw02;

import com.lobantm.hw02.DAO.ConnectionDB;
import com.lobantm.hw02.DAO.SkillDAOImpl;
import com.lobantm.hw02.model.Skill;
import com.lobantm.hw02.view.Menu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
       Menu menu = new Menu();
       menu.main();
    }
}
