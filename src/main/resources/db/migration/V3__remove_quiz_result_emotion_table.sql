drop table quiz_result_emotion;

drop table quiz_result;

create table users (
    id int primary key
);

create table quiz_result (
    id int auto_increment primary key,
    quiz_id int,
    user_id int,
    emotion_id int,
    score int,
    created_at datetime,
    foreign key (quiz_id) references quiz(id),
    foreign key (user_id) references users(id),
    foreign key (emotion_id) references emotion(id)
);