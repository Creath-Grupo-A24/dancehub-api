Create table creath_events
(
    id          uuid primary key,
    name        varchar,
    description varchar,
    file_name   varchar,
    local       varchar,
    time        timestamp
);

Create table creath_categories
(
    id   uuid primary key,
    type varchar
);

INSERT INTO creath_categories values(uuid_generate_v4(), 'SOLO');
INSERT INTO creath_categories values(uuid_generate_v4(), 'DUO');
INSERT INTO creath_categories values(uuid_generate_v4(), 'TRIO');
INSERT INTO creath_categories values(uuid_generate_v4(), 'EQUIPE');

CREATE table creath_events_categories
(
    event_id    uuid,
    category_id uuid
);

alter table creath_events_categories
    add constraint fk_event
        foreign key (event_id) references creath_events;

alter table creath_events_categories
    add constraint fk_category
        foreign key (category_id) references creath_categories;

select *
from creath_events;
select *
from creath_events_categories;
select * from creath_categories;