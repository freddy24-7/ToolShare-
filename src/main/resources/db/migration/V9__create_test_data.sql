INSERT INTO users (id, username, password, role, token, create_time)
VALUES (100, 'Frank', 'password8', 'USER', 'testtoken100', now()),
       (101, 'Anne', 'password8', 'ADMIN', 'testtoken101', now()),
       (102, 'Marieke', 'password8', 'USER', 'testtoken102', now()),
       (103, 'Thijs', 'password8', 'USER', 'testtoken103', now()),
       (104, 'Frida', 'password8', 'USER', 'testtoken104', now());

INSERT INTO participant (id, email, first_name, last_name, mobile_number, photoURL, postcode, user_id)
VALUES (100, 'frank@gmail.com', 'frank', 'bakker', '0617455834', 'http://localhost:8080/download/4dc32ba1-cc46-45fa-bda1-b8d9c67e4ef2', '3543HZ', 100),
       (101, 'hanne@gmail.com', 'hanne', 'de groene', '0617455834', 'http://localhost:8080/download/dabab42b-b4f5-4a4b-8a03-5e2b8bf0e296', '3543BL', 101),
       (102, 'marieke@gmail.com', 'marieke', 'vonk', '0617455834', 'http://localhost:8080/download/dabab42b-b4f5-4a4b-8a03-5e2b8bf0e296', '3543BJ', 102),
       (103, 'thijs@gmail.com', 'thijs', 'bakker', '0617455834', 'http://localhost:8080/download/4dc32ba1-cc46-45fa-bda1-b8d9c67e4ef2', '3543BN', 103),
       (104, 'frida@gmail.com', 'frida', 'groene', '0617455834', 'http://localhost:8080/download/dabab42b-b4f5-4a4b-8a03-5e2b8bf0e296', '3543BS', 104);

INSERT INTO shareitem (item_id, description, item_name, photourl, id)
VALUES (100, 'cuts a plank', 'saw', 'www.photothings.com', 100),
       (101, 'plants a nail', 'hammer', 'www.photothings.com', 101),
       (102, 'makes a measurement', 'lint', 'www.photothings.com', 102),
       (103, 'plays music', 'radio', 'www.photothings.com', 103),
       (104, 'removes leaves', 'leaf blower', 'www.photothings.com', 104);





