create table languages (
    id int unsigned primary key auto_increment, /* primary key additionaly = not null  */
    welcomeMsg varchar(100) not null,
    code varchar(3) /* 3 is enough "en" "pl" "rus" */
);