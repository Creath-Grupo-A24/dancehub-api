CREATE TABLE CREATH_INVITES
(
    id         uuid primary key,
    company_id uuid references creath_companies (id),
    guest_id   uuid references creath_users (id),
    key        varchar,
    sent       bool default false
);

comment on table CREATH_INVITES is 'Tabela dos convites';