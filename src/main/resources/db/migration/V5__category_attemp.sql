drop table quiz_result;
drop table users;


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
    emotion_id int,
    score      int,
    foreign key (quiz_id) references quiz (id),
    foreign key (attempt_id) references quiz_attempt (id),
    foreign key (emotion_id) references emotion (id)
);

create table subcategory
(
    id          int auto_increment primary key,
    emotion_id  int,
    description nvarchar(32),
    weight      int,
    foreign key (emotion_id) references emotion (id)
);