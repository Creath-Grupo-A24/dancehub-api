CREATE TABLE creath_events
(
    id          uuid primary key,
    name        varchar,
    description varchar,
    file_name   varchar,
    place       varchar,
    time        timestamp,
    finished    bool default false
);

CREATE TABLE creath_categories
(
    id   serial primary key,
    type varchar(30) not null
);

INSERT INTO creath_categories(type)
values ('SOLO');
INSERT INTO creath_categories(type)
values ('DUO');
INSERT INTO creath_categories(type)
values ('TRIO');
INSERT INTO creath_categories(type)
values ('EQUIPE');


CREATE TABLE creath_events_categories
(
    event_id    uuid,
    category_id integer
);

alter table creath_events_categories
    add constraint fk_event
        foreign key (event_id) references creath_events;

alter table creath_events_categories
    add constraint fk_category
        foreign key (category_id) references creath_categories;

comment on table creath_events_categories is 'Tabela de relacionamento entre eventos e categorias';
comment on table creath_events is 'Tabela de eventos';
comment on table creath_categories is 'Tabela de categorias';

select *
from creath_events;
select *
from creath_events_categories;
select *
from creath_categories;