alter table biplan.document
    add column if not exists was_read bool not null default false ;