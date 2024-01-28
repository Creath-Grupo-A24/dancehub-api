create table creath_attractions(
    id                  uuid primary key,
    name                varchar,
    category_id         integer,
    description         varchar,
    time                timestamp,
    event_id            uuid,
    director_id         uuid,
    choreographer_id    uuid
);
alter table creath_attractions
    add constraint fk_category
        foreign key (category_id) references creath_categories;
alter table creath_attractions
    add constraint fk_event
        foreign key (event_id) references creath_events;
alter table creath_attractions
    add constraint fk_director
        foreign key (director_id) references creath_users;
alter table creath_attractions
    add constraint fk_choreographer
        foreign key (choreographer_id) references creath_users;



create table creath_users_attractions(
    user_id    uuid,
    attraction_id uuid
);

alter table creath_users_attractions
    add constraint fk_user
        foreign key (user_id) references creath_users;

alter table creath_users_attractions
    add constraint fk_attraction
        foreign key (attraction_id) references creath_attractions;