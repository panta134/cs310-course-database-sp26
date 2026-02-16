package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SectionDAO {

    private static final String QUERY =
        "SELECT * FROM section WHERE termid = ? AND subjectid = ? AND num = ? ORDER BY crn";

    private DAOFactory factory;

    public SectionDAO(DAOFactory factory) {
        this.factory = factory;
    }

    public String findSectionData(int termId, String subjectId, String courseNumber) {

        String resultJson = "[]";

        try (Connection connection = factory.getConnection()) {

            if (connection == null || !connection.isValid(2)) {
                return resultJson;
            }

            try (PreparedStatement statement = connection.prepareStatement(QUERY)) {

                statement.setInt(1, termId);
                statement.setString(2, subjectId);
                statement.setString(3, courseNumber);

                try (ResultSet resultSet = statement.executeQuery()) {
                    resultJson = DAOUtility.getResultSetAsJson(resultSet);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultJson;
    }
}
