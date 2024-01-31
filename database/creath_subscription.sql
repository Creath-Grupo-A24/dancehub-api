create table creath_subscription(
    id                  uuid primary key,
    name                varchar,
    category_id         integer,
    description         varchar,
    time                timestamp,
    event_id            uuid
);
alter table creath_subscription
    add constraint fk_category
        foreign key (category_id) references creath_categories;
alter table creath_subscription
    add constraint fk_event
        foreign key (event_id) references creath_events;



create table creath_users_subscription(
    user_id    uuid,
    subscription_id uuid
);

alter table creath_users_subscription
    add constraint fk_user
        foreign key (user_id) references creath_users;

alter table creath_users_subscription
    add constraint fk_subscription
        foreign key (subscription_id) references creath_subscription;