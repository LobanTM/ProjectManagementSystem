package com.lobantm.hw02.DAO;

import com.lobantm.hw02.model.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDAOImpl {
    private static Connection conn = ConnectionDB.getConnection();
    private static Statement st = null;
    private static ResultSet rs = null;

    private SkillDAOImpl() {}

    public static void create(String skill) {
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO skills (skill) VALUE (?)");
            st.setString(1, skill);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Skill read(int id) {
        Skill skill = new Skill();
        skill.setId(id);
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM skills WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next())
            skill.setNameSkill(rs.getString(2));
            st.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    public static void delete(int id) {
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM skills WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.err.println("skill is foreign key");
            //e.printStackTrace();
        }
    }

    public static void update(int id, String skillName) {
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE skills SET skill = ? WHERE id = ?");
            st.setString(1, skillName);
            st.setInt(2, id);
            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Skill> findAll() {
        List<Skill> list = new ArrayList<Skill>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM skills");
            while(rs.next()){
                Skill skillRes = new Skill(rs.getInt(1), rs.getString(2));
                list.add(skillRes);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
