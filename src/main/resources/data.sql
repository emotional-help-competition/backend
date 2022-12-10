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
VALUES (1, 'rabies', 5),
       (1, 'annoyance', 15),
       (1, 'contempt', 20),
       (1, 'hysteria', 25),
       (1, 'irritation', 35),
       (1, 'hate', 40),
       (1, 'indignation', 45),
       (1, 'resentment', 50),
       (1, 'envy', 70),
       (1, 'jealousy', 85),
       (1, 'vulnerability', 95),
       (1, 'fury', 100),


       (2, 'doubt', 5),
       (2, 'fright', 15),
       (2, 'numbness', 20),
       (2, 'suspicion', 25),
       (2, 'anxiety', 35),
       (2, 'dumbfoundedness', 40),
       (2, 'shame', 45),
       (2, 'humiliation', 50),
       (2, 'horror', 70),
       (2, 'guilt', 85),
       (2, 'confusion', 95),
       (2, 'despair', 100),


       (3, 'yearning', 15),
       (3, 'sorrow', 20),
       (3, 'laziness', 25),
       (3, 'heartache', 35),
       (3, 'bitterness', 40),
       (3, 'despair', 45),
       (3, 'pity', 50),
       (3, 'downtroddenness', 70),
       (3, 'boredom', 85),
       (3, 'hopelessness', 95),
       (3, 'disappointment', 100),


       (4, 'identity', 5),
       (4, 'sympathy', 15),
       (4, 'heat', 20),
       (4, 'confidence', 25),
       (4, 'tenderness', 35),
       (4, 'mutual assistance', 40),
       (4, 'humility', 50),
       (4, 'sincerity', 70),
       (4, 'sympathy', 85),
       (4, 'kindness', 95),
       (4, 'bliss', 100),


       (5, 'stunned', 5),
       (5, 'awe-struck', 20),
       (5, 'perplexed', 25),
       (5, 'touched', 40),
       (5, 'stimulated', 45),
       (5, 'astounded', 50),
       (5, 'shocked', 70),
       (5, 'speechless', 85),
       (5, 'confused', 95),
       (5, 'amazed', 100),


       (6, 'eager', 15),
       (6, 'amused', 20),
       (6, 'cheerful', 25),
       (6, 'enthusiastic', 35),
       (6, 'optimistic', 40),
       (6, 'hopeful', 45),
       (6, 'proud', 50),
       (6, 'satisfied', 70),
       (6, 'pleased', 85),
       (6, 'happy', 95),
       (6, 'blisful', 100),


       (7, 'brave', 35),
       (7, 'proud', 45),
       (7, 'loved', 70),
       (7, 'honored', 85),
       (7, 'appreciated', 95),
       (7, 'respected', 100),

       (8, 'powerless', 20),
       (8, 'anxious', 25),
       (8, 'ashamed', 40),
       (8, 'stressed', 70),
       (8, 'overwhelmed', 95),
       (8, 'guilty', 100),


       (9, 'rejected', 15),
       (9, 'submissive', 25),
       (9, 'embarrassed', 35),
       (9, 'insignificant', 85),
       (9, 'helpless', 95),
       (9, 'insecure', 100);


INSERT INTO quiz_attempt()
VALUES ();


INSERT INTO quiz_result(quiz_id, attempt_id, emotion_id, score)
VALUES (1, 1, 1, 50),
       (1, 1, 2, 60),
       (1, 1, 3, 70);