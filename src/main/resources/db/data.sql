truncate table users cascade;
truncate table media cascade;

insert into users(id, email, password, created_at) values
(200, 'test0@email.com', 'password', '2024-06-04T15:03:28.739603300'),
(201, 'test1@email.com', 'password', '2024-06-04T15:03:28.739603300'),
(202, 'test2@email.com', 'password', '2024-06-04T15:03:28.739603300'),
(203, 'test3@email.com', 'password', '2024-06-04T15:03:28.739603300');

insert into media(id, category, description, url, created_at, uploader_id) values
(100, 'ACTION', 'media 1', 'https://www.cloudinary.com/media1', '2024-06-04T15:03:28.739603300', 201),
(101, 'COMEDY', 'media 2', 'https://www.cloudinary.com/media2', '2024-06-04T15:03:28.739603300', 200),
(102, 'STEP_MUM', 'media 3', 'https://www.cloudinary.com/media3', '2024-06-04T15:03:28.739603300', 200),
(103, 'DRAMA', 'media 4', 'https://www.cloudinary.com/media4', '2024-06-04T15:03:28.739603300', 203),
(104, 'ROMANCE', 'media 5', 'https://www.cloudinary.com/media5', '2024-06-04T15:03:28.739603300', 200);