drop table quiz_result_emotion;
drop table quiz_result;
drop table quiz_attempt;
drop table subcategory;

create table quiz_attempt
(
    id         int auto_increment primary key,
    created_at datetime
);

create table quiz_result
(
    id         int auto_increment primary key,
    quiz_id    int,
    attempt_id int,
    score      int,
    foreign key (quiz_id) references quiz (id),
    foreign key (attempt_id) references quiz_attempt (id)
);


create table quiz_result_emotion
(
    quiz_result_id int,
    emotion_id     int,
    foreign key (quiz_result_id) references quiz_result (id),
    foreign key (emotion_id) references emotion (id)
);


create table subcategory
(
    id          int auto_increment primary key,
    emotion_id  int,
    description nvarchar(32),
    weight      int,
    foreign key (emotion_id) references emotion (id)
)