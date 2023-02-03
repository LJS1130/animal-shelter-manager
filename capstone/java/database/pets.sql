DROP TABLE IF EXISTS pets;

CREATE TABLE pets (
	pet_id SERIAL primary key,
	pet_name varchar(50) NOT NULL,
	pet_type varchar(200) NOT NULL,
	pet_age int,
	pet_sex varchar(10) NOT NULL,
	pet_breed varchar(50) NOT NULL,
	pet_size varchar(10) NOT NULL,
	is_good_with_kids BOOLEAN,
	is_good_with_dogs BOOLEAN,
	is_good_with_cats BOOLEAN,
	is_available BOOLEAN,
	image_link varchar(200)
);

INSERT INTO pets (pet_name, pet_type, pet_age, pet_sex, pet_breed, pet_size, is_good_with_kids, is_good_with_dogs, is_good_with_cats, is_available, image_link)
Values ('max','dog',10, 'female', 'domestic short hair', 'XXL', true, false, true, false, 'https://images.unsplash.com/photo-1587402092301-725e37c70fd8'),
('frank','cat',3, 'female', 'domestic short hair', 'XXL', true, false, true, true, 'https://images.unsplash.com/photo-1615789591457-74a63395c990'),
('belle','dog',4, 'female', 'domestic short hair', 'XXL', true, false, false, false, 'https://images.unsplash.com/photo-1587402092301-725e37c70fd8'),
('benny','dog',14, 'female', 'domestic short hair', 'XXL', true, false, true, true, 'https://images.unsplash.com/photo-1587402092301-725e37c70fd8'),
('tiger','cat',1, 'female', 'domestic short hair', 'XXL', true, false, true, false, 'https://images.unsplash.com/photo-1615789591457-74a63395c990'),
('Deer','dog',10, 'female', 'domestic short hair', 'XXL', true, false, true, true, 'https://images.unsplash.com/photo-1587402092301-725e37c70fd8'),
('Doodle','cat',2, 'female', 'domestic short hair', 'XXL', true, false, true, false, 'https://images.unsplash.com/photo-1670471198259-137eacb1a430?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2127&q=80'),
('Bear','dog',4, 'female', 'domestic short hair', 'XXL', true, false, false, true, 'https://images.unsplash.com/photo-1587402092301-725e37c70fd8'),
('Hawk','dog',14, 'male', 'mixed', 'XXL', true, false, true, false, 'https://images.unsplash.com/photo-1587402092301-725e37c70fd8'),
('Shark','cat',1, 'female', 'domestic short hair', 'XXL', true, false, true, true, 'https://images.unsplash.com/photo-1615789591457-74a63395c990');

select * from pets