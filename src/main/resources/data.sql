INSERT INTO emotion (id, description)
VALUES (1, 'joy'),
       (2, 'surprise'),
       (3, 'anger'),
       (4, 'disgust'),
       (5, 'fear'),
       (6, 'sadness');


INSERT INTO question (text, emotion_id)
VALUES ('How do u feel today?', 1);
INSERT INTO question (text, emotion_id)
VALUES ('How do u feel yesterday?', 1);
INSERT INTO question (text, emotion_id)
VALUES ('How do u feel at 12:00?', 2);
INSERT INTO question (text, emotion_id)
VALUES ('How do u feel at 21:00?', 3);


INSERT INTO quiz(name, description, created_at)
VALUES ('anxiety test', 'anxiety test to check', '2022-10-15 11:15:10');
INSERT INTO quiz(name, description, created_at)
VALUES ('sad test', 'sad test to check', '2022-10-15 11:15:10');


INSERT INTO quiz_question (quiz_id, question_id)
VALUES (1, 1);
INSERT INTO quiz_question (quiz_id, question_id)
VALUES (1, 2);
INSERT INTO quiz_question (quiz_id, question_id)
VALUES (1, 3);
INSERT INTO quiz_question (quiz_id, question_id)
VALUES (1, 4);

INSERT INTO quiz_question (quiz_id, question_id)
VALUES (2, 1);
INSERT INTO quiz_question (quiz_id, question_id)
VALUES (2, 2);
INSERT INTO quiz_question (quiz_id, question_id)
VALUES (2, 4);

