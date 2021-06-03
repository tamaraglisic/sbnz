insert into authority (role) values ('ROLE_ADMIN');
insert into authority (role) values ('ROLE_REGISTERED_USER');
insert into authority (role) values ('ROLE_INSTRUCTOR');

insert into users (type, email, password, verified) values ('admin','admin@gmail.com','$2a$10$RVzuprKddsjdq6P8QWmqF.sCj2uYPIUlbFVB.b7tJ9RdFNOOBNoXO' ,true);

insert into users_authority (user_id, authority_id) values (1, 1);

insert into ski_resort (name, description, country, lift_price, gondola_price, season_starts, season_ends, group_count, ticket_deposit, capacity) values('Kopaonik','Skijaliste u Srbiji', 'Srbija', 4500, 1000, '2021-03-01 22:24:52', '2021-08-01 22:24:52', 20, 500, 10256);

insert into ticket_user (user_type, counts, single_ticket_price) values ('ODRASLI', 2, 100);
insert into ticket_user (user_type, counts, single_ticket_price) values ('DECA', 2, 100);
insert into ticket_user (user_type) values ('SENIOR');

insert into tickets (bill, initial_price, transport_type, type_ticket, using_start, using_end, using_period, ski_resort_id) values (12364,200, 'ZICARA', 'PORODICNA', '2021-05-25 22:24:52', '2021-05-30 22:24:52', 'DNEVNA', 1);

insert into tickets_ticket_users(tickets_id, ticket_users_id) values(1,1);
insert into tickets_ticket_users(tickets_id, ticket_users_id) values(1,2);