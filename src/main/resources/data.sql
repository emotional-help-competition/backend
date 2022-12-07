INSERT INTO emotion (id, description)
VALUES (1, 'anger'),
       (2, 'fear'),
       (3, 'sadness'),
       (4, 'love'),
       (5, 'surprise'),
       (6, 'joy'),
       (7, 'accepted'),
       (8, 'afraid'),
       (9, 'scared');


INSERT INTO question (text, emotion_id)
VALUES ('Do you like to get a present?', 1),
       ('Your friend let you spend his money, do you feel surprised?', 2),
       ('Your irritation level when someone borrows your stuff', 3),
       ('Your brother ate a cake was laying on the floor, your disgust level', 4),
       ('A wolf is approaching you, your fear level', 5),
       ('Your grandmother`s cat died, do you feel sad?', 6);


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
        'https://www.youtube.com/watch?v=dQw4w9WgXcQ'),
       (2,
        'very good one from sad : )',
        'https://www.imagenspng.com.br/wp-content/uploads/2020/10/among-us-icon-png-02.png',
        'https://www.youtube.com/watch?v=dQw4w9WgXcQ');


INSERT INTO recommendation (emotion_id, appointment_id, floor, ceil)
VALUES (6, 1, 0, 100),
       (6, 3, 0, 40),
       (1, 2, 0, 20);


INSERT INTO quiz(name, description, created_at)
VALUES ('Quiz1', 'QuizDesc1', '2022-10-15 11:15:10'),
       ('Quiz2', 'QuizDesc2', '2022-10-15 11:15:10'),
       ('Quiz3', 'QuizDesc3', '2022-10-15 11:15:10');


INSERT INTO quiz_question(quiz_id, question_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 3),
       (2, 4),
       (2, 5);


INSERT INTO subcategory(emotion_id, description, weight)
VALUES (1, 'rabies', 1),
       (1, 'fury', 2),
       (1, 'hate', 3),
       (1, 'hysteria', 4),
       (1, 'irritation', 5),

       (2, 'despair', 1),
       (2, 'fright', 2),
       (2, 'numbness', 3),
       (2, 'suspicion', 4),
       (2, 'anxiety', 5),

       (3, 'bitterness', 1),
       (3, 'yearning', 2),
       (3, 'sorrow', 3),
       (3, 'laziness', 4),
       (3, 'pity', 5),

       (4, 'tenderness', 1),
       (4, 'heat', 2),
       (4, 'sympathy', 3),
       (4, 'bliss', 4),
       (4, 'confidence', 5),

       (5, 'stunned', 1),
       (5, 'confused', 2),
       (5, 'amazed', 3),
       (5, 'speechless', 4),
       (5, 'shocked', 5),

       (6, 'proud', 1),
       (6, 'optimistic', 2),
       (6, 'happy', 3),
       (6, 'cheerful', 4),
       (6, 'enthusiastic', 5),

       (7, 'loved', 1),
       (7, 'honored', 2),
       (7, 'appreciated', 3),
       (7, 'respected', 4),
       (7, 'brave', 5),

       (8, 'stressed', 1),
       (8, 'overwhelmed', 2),
       (8, 'powerless', 3),
       (8, 'anxious', 4),
       (8, 'guilty', 5),

       (9, 'helpless', 1),
       (9, 'rejected', 2),
       (9, 'insecure', 3),
       (9, 'submissive', 4),
       (9, 'embarrassed', 5);


INSERT INTO quiz_attempt()
VALUES ();


INSERT INTO quiz_result(quiz_id, attempt_id, emotion_id, score)
VALUES (1, 1, 1, 50),
       (1, 1, 2, 60),
       (1, 1, 3, 70);