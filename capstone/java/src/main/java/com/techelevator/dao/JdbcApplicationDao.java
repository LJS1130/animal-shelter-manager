package com.techelevator.dao;

import com.techelevator.model.Application;
import com.techelevator.model.ApplicationDTO;
import com.techelevator.model.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.RowSet;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcApplicationDao implements ApplicationDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcApplicationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return list of all applications in database ordered by time registered
     */
    @Override
    public List<Application> getAllApplications() {
        String sql = "SELECT * FROM application ORDER BY time_registered;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        return createListOfApplications(rowSet);
    }

    /**
     * @param statusId
     * @return list of applications by status ordered by time registered: A for approved, P for pending, R for rejected.
     */
    @Override
    public List<Application> getApplicationsByStatus(String statusId) {
        if (statusId == null) throw new IllegalArgumentException("Status ID cannot be null");
        String sql = "SELECT * FROM application a " +
                " JOIN status s ON a.status_id " +
                " WHERE status_id = ? " +
                " ORDER BY time_registered";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, statusId);
        return createListOfApplications(rowSet);
    }

    /**
     * Note: This method is to be modified or deleted based on required functions of the app.
     *
     * @param userId
     * @return list of applications by user ID ordered by time registered.
     */
    @Override
    public List<Application> getApplicationsByUserId(int userId) {
        if (userId == 0) throw new IllegalArgumentException("User ID cannot be 0");
        String sql = "SELECT * FROM application a " +
                " JOIN contact c on a.contact_id = c.contact_id " +
                " JOIN user u on c.contact_id = u.contact_id " +
                " WHERE user_id = ?" +
                " ORDER BY time_registered";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        return createListOfApplications(rowSet);
    }

    /**
     *
     * @param applicationId : application ID to update status ID
     * @param newStatusId : new status
     */
    @Override
    public void updateStatus(int applicationId, String newStatusId) {
        String sql1 = "UPDATE application SET status_id = ? WHERE application_id = ?";
        jdbcTemplate.update(sql1, newStatusId, applicationId);
        String sql2 = "SELECT user_id FROM shelter_user s JOIN contact c ON" +
                " s.contact_id = c.contact_id JOIN application a ON c.contact_id = a.contact_id WHERE a.application_id = ?";
        int userId = jdbcTemplate.queryForObject(sql2, int.class, applicationId);
        if (newStatusId.equals("A")) {
            String sql3 = "UPDATE shelter_user SET user_role = 'ROLE_VOLUNTEER' WHERE user_id = ?";
            jdbcTemplate.update(sql3, userId);
        }
    }

    /**
     * Inserts application and contact into db
     *
     * @param applicationDTO
     */
    @Override
    public void createApp(ApplicationDTO applicationDTO, Principal principal) {
        String sql0 = "UPDATE application a SET status_id = 'R' FROM contact c " +
                "JOIN shelter_user s ON c.contact_id = s.contact_id WHERE status_id = 'P' AND username = ? " +
                "AND a.contact_id = c.contact_id";
        jdbcTemplate.update(sql0, principal.getName());
        String sql1 = "INSERT INTO contact (contact_name, phone, email, city, state, age, social_link) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING contact_id;";
        Contact contact = applicationDTO.getContact();
        int contactId = jdbcTemplate.queryForObject(sql1, int.class, contact.getContactName(), contact.getPhone(),
                contact.getEmail(), contact.getCity(), contact.getState(), contact.getAge(), contact.getSocialLink());
        String sql2 = "INSERT INTO application (contact_id, status_id, weekly_hours, is_day, preferred_animal, reason) " +
                " VALUES (?, ?, ?, ?, ?, ?) RETURNING application_id";
        Application application = applicationDTO.getApplication();
        int applicationId = jdbcTemplate.queryForObject(sql2, int.class, contactId, 'P', application.getWeeklyHours(),
                application.isDay(), application.getPreferredAnimal(), application.getReason());
        String sql3 = "UPDATE shelter_user SET contact_id = ? WHERE username = ?";
        jdbcTemplate.update(sql3, contactId, principal.getName());
    }

    /**
     * Inserts contact info to contact table in database.
     *
     * @param statusId
     */
    @Override
    public List<ApplicationDTO> listApplicationDTO(String statusId) {
        String sql = "SELECT c.contact_name contact_name, c.phone phone, c.email email, c.city city, c.state state, c.age age," +
                " c.social_link social_link, a.application_id application_id, a.contact_id contact_id, a.status_id status_id," +
                " a.weekly_hours weekly_hours, a.is_day is_day, a.preferred_animal preferred_animal, a.reason reason, a.time_registered time_registered" +
                " FROM contact c JOIN application a ON c.contact_id=a.contact_id WHERE a.status_id = ? ORDER BY a.time_registered";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, statusId);
        List<ApplicationDTO> applicationDTOs = new ArrayList<>();
        while (rowSet.next()) {
            applicationDTOs.add(mapRowToApplicationDTO(rowSet));
        }
        return applicationDTOs;
    }


    /**
     * @param rowSet
     * @return List of application objects
     */
    private List<Application> createListOfApplications(SqlRowSet rowSet) {
        List<Application> allContacts = new ArrayList<>();
        while (rowSet.next()) {
            Application application = mapRowToApplication(rowSet);
            allContacts.add(application);
        }
        return allContacts;
    }

    /**
     * Mapping each row of SQL result to applicationDTO object including application and contact
     *
     * @param rowSet
     * @return application
     */
    private ApplicationDTO mapRowToApplicationDTO(SqlRowSet rowSet) {
        Application application = new Application(
                rowSet.getInt("application_id"),
                rowSet.getInt("contact_id"),
                rowSet.getString("status_id"),
                rowSet.getInt("weekly_hours"),
                rowSet.getBoolean("is_day"),
                rowSet.getString("preferred_animal"),
                rowSet.getString("reason"),
                rowSet.getTimestamp("time_registered").toLocalDateTime());
        Contact contact = new Contact(
                rowSet.getInt("contact_id"),
                rowSet.getString("contact_name"),
                rowSet.getString("phone"),
                rowSet.getString("email"),
                rowSet.getString("city"),
                rowSet.getString("state"),
                rowSet.getInt("age"),
                rowSet.getString("social_link"));
        return new ApplicationDTO(application,contact);
    }

    /**
     * Mapping each row of SQL result to application object.
     *
     * @param rowSet
     * @return application
     */
    private Application mapRowToApplication(SqlRowSet rowSet) {
        return new Application(
                rowSet.getInt("application_id"),
                rowSet.getInt("contact_id"),
                rowSet.getString("status_id"),
                rowSet.getInt("weekly_hours"),
                rowSet.getBoolean("is_day"),
                rowSet.getString("preferred_animal"),
                rowSet.getString("reason"),
                rowSet.getTimestamp("time_registered").toLocalDateTime());
    }
}
