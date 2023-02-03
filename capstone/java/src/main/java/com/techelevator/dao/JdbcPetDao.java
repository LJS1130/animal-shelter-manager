package com.techelevator.dao;


import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPetDao implements PetDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Pet getPet(int id) {
        if (id == 0) throw new IllegalArgumentException("ID cannot be null");


        String sql = "SELECT * FROM pets WHERE pet_id = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);

        if (rs.next()) {
            return mapRowToPet(rs);
        }
        return null;
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> allPets = new ArrayList<>();
        String sql = "SELECT * FROM pets ORDER BY time_added";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);

        while (rs.next()) {
            Pet pet = mapRowToPet(rs);
            allPets.add(pet);
        }
        return allPets;
    }

    @Override
    public void submitPet(Pet pet) {
        String sql = "INSERT INTO pets (pet_name, pet_type, pet_age, pet_sex, pet_breed, pet_size, " +
                " is_good_with_kids, is_good_with_dogs, is_good_with_cats, is_available, image_link) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, pet.getName(), pet.getType(), pet.getAge(), pet.getSex(), pet.getBreed(), pet.getSize(),
                pet.isGoodWithKids(), pet.isGoodWithDogs(), pet.isGoodWithCats(), pet.isAvailable(), pet.getImageLink());
    }

    @Override
    public void updatePet(Pet pet, int id) {
        String sql = "UPDATE pets SET pet_name = ?, pet_type = ?, pet_age = ?, pet_sex = ?, pet_breed = ?, pet_size = ?, " +
                " is_good_with_kids = ?, is_good_with_dogs = ?, is_good_with_cats = ?, is_available = ?, image_link = ?, user_id = ? WHERE pet_id = ?";
        jdbcTemplate.update(sql, pet.getName(), pet.getType(), pet.getAge(), pet.getSex(), pet.getBreed(), pet.getSize(),
                pet.isGoodWithKids(), pet.isGoodWithDogs(), pet.isGoodWithCats(), pet.isAvailable(), pet.getImageLink(), pet.getUserId(), id);
    }

    @Override
    public void updateAvailability(int id, boolean isAvailable) {
        Pet pet = getPet(id);
        String sql = "UPDATE pets SET is_available = ? WHERE pet_id = ?;";
        jdbcTemplate.update(sql, isAvailable, pet.getId());
    }

    @Override
    public void deletePet(int id) {
        String sql = "DELETE FROM pets WHERE pet_id = ?;";
        jdbcTemplate.update(sql, id);
    }

    private Pet mapRowToPet(SqlRowSet rs) {
        Pet pet = new Pet();
        pet.setId(rs.getInt("pet_id"));
        pet.setName(rs.getString("pet_name"));
        pet.setType(rs.getString("pet_type"));
        pet.setAge(rs.getInt("pet_age"));
        pet.setSex(rs.getString("pet_sex"));
        pet.setBreed(rs.getString("pet_breed"));
        pet.setSize(rs.getString("pet_size"));
        pet.setGoodWithKids(rs.getBoolean("is_good_with_kids"));
        pet.setGoodWithDogs(rs.getBoolean("is_good_with_dogs"));
        pet.setGoodWithCats(rs.getBoolean("is_good_with_cats"));
        pet.setAvailable(rs.getBoolean("is_available"));
        pet.setImageLink(rs.getString("image_link"));
        pet.setUserId(rs.getInt("user_id"));
        return pet;
    }
}
