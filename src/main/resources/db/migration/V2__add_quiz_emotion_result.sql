drop table question;

create table quiz (
    id int auto_increment primary key,
    name nvarchar(32),
    description nvarchar(32),
    created_at datetime
);

create table emotion(
    id int auto_increment primary key,
    description nvarchar(32)
);

create table question (
    id int auto_increment primary key,
    text nvarchar(128),
    emotion_id int,
    foreign key (emotion_id) references emotion(id)
);

create table quiz_question (
    quiz_id int,
    question_id int,
    foreign key (quiz_id) references quiz(id),
    foreign key (question_id) references question(id)
);

create table quiz_result (
    id int auto_increment primary key,
    user_id int,
    created_at datetime
);

create table quiz_result_emotion (
    quiz_result_id int,
    emotion_id int,
    `value` int,
    foreign key (quiz_result_id) references quiz_result(id),
    foreign key (emotion_id) references emotion(id)
);