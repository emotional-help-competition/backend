create table appointment (
    id int auto_increment primary key,
    description_dummy nvarchar(128)
);

create table recommendation (
    id int auto_increment primary key,
    emotion_id int,
    appointment_id int,
    floor int,
    ceil int,
    foreign key (emotion_id) references emotion(id),
    foreign key (appointment_id) references appointment(id)
);