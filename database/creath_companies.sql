    Create TABLE CREATH_COMPANIES
(
    id       uuid primary key,
    name     varchar unique                    not null,
    owner_id uuid references creath_users (id) not null
);

comment on table CREATH_COMPANIES is 'Tabela das companhias' ;

select * from creath_companies;