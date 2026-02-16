package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegistrationDAO {

    private final DAOFactory daoFactory;

    public RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public boolean create(int studentid, int termid, int crn) {

        String sql = "INSERT INTO registration (studentid, termid, crn) VALUES (?, ?, ?)";
        boolean success = false;

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn != null && conn.isValid(2)) {

                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);

                success = (ps.executeUpdate() > 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean delete(int studentid, int termid, int crn) {

        String sql = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
        boolean success = false;

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn != null && conn.isValid(2)) {

                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);

                success = (ps.executeUpdate() > 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean delete(int studentid, int termid) {

        String sql = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
        boolean success = false;

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn != null && conn.isValid(2)) {

                ps.setInt(1, studentid);
                ps.setInt(2, termid);

                success = (ps.executeUpdate() > 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public String list(int studentid, int termid) {

        String sql = "SELECT * FROM registration WHERE studentid = ? AND termid = ? ORDER BY crn";
        String result = "[]";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn != null && conn.isValid(2)) {

                ps.setInt(1, studentid);
                ps.setInt(2, termid);

                try (ResultSet rs = ps.executeQuery()) {
                    result = DAOUtility.getResultSetAsJson(rs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}

