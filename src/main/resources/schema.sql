create table employees
(
    id                    int not null auto_increment,
    firstName           varchar(50) not null,
    middleName           varchar(50) not null,
    lastName           varchar(50) not null,
    secondLastName           varchar(50) not null,
    age                    int not null,
    gender           varchar(50) not null,
    birthDate           varchar(50) not null,
    position           varchar(50) not null,
    primary key (id)
);