whenever error continue;
connect to 'coffeeShopData' user 'sysadm' using 'sysadm';

create ident admin as user using 'adm1n';
grant schema to admin;
grant databank to admin;

create ident programmer as user identified by 'pr0grammer';
create ident shopper as user identified by 'sh0pper';

-- These should be tighter, but this is just an example
grant schema to programmer;
grant schema to shopper;

disconnect;

connect to 'coffeeShopData' user 'admin' using 'adm1n';
create databank catalog in 'catalog.dbf';
create schema catalog authorization admin;
create schema orders authorization admin;
create schema people authorization admin;
