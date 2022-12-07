create table appointment_type(
    id int auto_increment primary key,
    description nvarchar(32)
);

alter table appointment drop column description_dummy;
alter table appointment add column (
    appointment_type_id int references appointment_type(id),
    description nvarchar(128),
    icon varchar(2048),
    link varchar(2048)
);
