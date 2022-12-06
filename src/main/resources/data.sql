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

       (2, 'despair', 2),
       (2, 'fright', 3),
       (2, 'numbness', 4),
       (2, 'suspicion', 5),
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