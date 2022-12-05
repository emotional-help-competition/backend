INSERT INTO emotion (id, description)
VALUES (1, 'shocked'),
       (2, 'stunned'),
       (3, 'amazed'),
       (4, 'hatred'),
       (5, 'lonely'),
       (6, 'annoyed');


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


INSERT INTO category(emotion_id, description, weight)
VALUES (5, 'sadness', 0.5),
       (1, 'surprise', 0.5);

INSERT INTO quiz_attempt(created_at)
VALUES ('2022-10-15 11:15:10');

INSERT INTO quiz_result(quiz_id, attempt_id, emotion_id, score)
VALUES (1, 1, 1, 3)