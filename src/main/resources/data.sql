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
