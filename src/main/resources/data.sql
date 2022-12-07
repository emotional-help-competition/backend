INSERT INTO emotion (id, description)
VALUES (1, 'joy'),
       (2, 'surprise'),
       (3, 'anger'),
       (4, 'disgust'),
       (5, 'fear'),
       (6, 'sadness');


INSERT INTO question (text, emotion_id)
VALUES ('Do you like to get a present?', 1),
       ('Your friend let you spend his money, do you feel surprised?', 2),
       ('Your irritation level when someone borrows your stuff', 3),
       ('Your brother ate a cake was laying on the floor, your disgust level', 4),
       ('A wolf is approaching you, your fear level', 5),
       ('Your grandmother`s cat died, do you feel sad?', 6);


INSERT INTO appointment (description)
VALUES ('from sad training'),
       ('increase joy training'),
       ('very good one from sad : )');


INSERT INTO recommendation (emotion_id, appointment_id, floor, ceil)
VALUES (6, 1, 0, 100),
       (6, 3, 0, 40),
       (1, 2, 0, 20);

INSERT INTO appointment_type (description)
VALUES ('general training'),
       ('psychologist');

INSERT INTO appointment (appointment_type_id, description, icon, link)
VALUES (1,
        'this is youtube general training; it is suitable for everyone',
        'https://www.imagenspng.com.br/wp-content/uploads/2020/10/among-us-icon-png-02.png',
        'https://www.youtube.com/watch?v=dQw4w9WgXcQ'),
       (2,
        'this is appointment with real psychologist; doctor is going to find different ways to your personality',
        'https://www.imagenspng.com.br/wp-content/uploads/2020/10/among-us-icon-png-02.png',
        'https://www.youtube.com/watch?v=dQw4w9WgXcQ');

