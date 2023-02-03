package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcContactDao implements ContactDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcContactDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Get all existing contacts from contacts table
     *
     * @return List<Contact> list of all contacts
     */
    @Override
    public List<Contact> getAllContacts() {
        String sql = "SELECT * FROM contact c JOIN shelter_user s ON c.contact_id = s.contact_id WHERE user_role = 'ROLE_VOLUNTEER' OR user_role = 'ROLE_ADMIN'";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        return createListOfContacts(rowSet);
    }

    /**
     * Get contacts by role,
     *
     * @param role: Admin or Volunteer
     * @return List<Contact> list of contacts by role.
     */
    @Override
    public List<Contact> getContactsByRole(String role) {
        if (role == null) throw new IllegalArgumentException("Role cannot be null");
        String sql = "SELECT * FROM contact c " +
                " JOIN user u ON c.contact_id = u.contact_id JOIN application a ON a.contact_id = c.contact_id " +
                " WHERE status_id = 'A' (role = 'USER_VOLUNTEER' OR role = 'USER_ADMIN')";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        return createListOfContacts(rowSet);
    }



    /**
     * Inserts contact info to contact table in database.
     *
     * @param contact
     */
    @Override
    public Long create(Contact contact) {
        String sql = "INSERT INTO contact (contact_name, phone, email, city, state, age, social_link) " +
                " VALUES (?, ?, ?, ?, ?, ?) RETURNING contact_id;";
        return jdbcTemplate.queryForObject(sql, Long.class, contact.getContactName(), contact.getPhone(),
                contact.getEmail(), contact.getCity(), contact.getState(), contact.getAge(), contact.getSocialLink());
    }

    @Override
    public List<ContactDTO> getListOfContactDTO() {
        String sql = "SELECT u.user_id, u.user_role, c.contact_id, c.contact_name, c.phone, c.email, c.city, c.state, c.age, c.social_link " +
                " FROM shelter_user u JOIN contact c ON u.contact_id = c.contact_id " +
                " WHERE user_role = 'ROLE_ADMIN' OR user_role = 'ROLE_VOLUNTEER'";
        List<ContactDTO> listOfContactDTO = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            listOfContactDTO.add(mapRowToContactDTO(rowSet));
        }
        return listOfContactDTO;
    }



    /**
     * @param rowSet
     * @return List of contact objects
     */
    private List<Contact> createListOfContacts(SqlRowSet rowSet) {
        List<Contact> allContacts = new ArrayList<>();
        while (rowSet.next()) {
            Contact contact = mapRowToContact(rowSet);
            allContacts.add(contact);
        }
        return allContacts;
    }

    /**
     * Mapping each row of SQL result to contact object.
     *
     * @param rowSet
     * @return contact
     */
    private Contact mapRowToContact(SqlRowSet rowSet) {
        return new Contact(
                rowSet.getInt("contact_id"),
                rowSet.getString("contact_name"),
                rowSet.getString("phone"),
                rowSet.getString("email"),
                rowSet.getString("city"),
                rowSet.getString("state"),
                rowSet.getInt("age"),
                rowSet.getString("social_link"));
    }

    private ContactDTO mapRowToContactDTO(SqlRowSet rowSet) {
        User user = new User(
                rowSet.getInt("user_id"),
                rowSet.getString("user_role"));
        Contact contact = new Contact(
                rowSet.getInt("contact_id"),
                rowSet.getString("contact_name"),
                rowSet.getString("phone"),
                rowSet.getString("email"),
                rowSet.getString("city"),
                rowSet.getString("state"),
                rowSet.getInt("age"),
                rowSet.getString("social_link"));
        return new ContactDTO(user,contact);
    }
}
