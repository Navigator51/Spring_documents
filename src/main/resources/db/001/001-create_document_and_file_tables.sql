create schema if not exists biplan;

create table if not exists biplan.document (
    id bigserial, 
    creation_date timestamp, 
    modify_date timestamp, 
    status varchar(255), 
    creator_id bigint, 
    sender_id bigint,     primary key (id));

create table if not exists biplan.file (
    id bigserial, 
    creation_date timestamp, 
    file_type varchar(255), 
    modify_date timestamp, 
    name varchar(255), 
    document_id bigint, primary key (id));

create table if not exists biplan.document_recipient (
    document_id bigint not null, 
    recipient_id bigint not null)