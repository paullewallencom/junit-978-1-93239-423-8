whenever error continue;

drop procedure catalog.addProduct;

drop table catalog.discount;
drop table catalog.discountDefinition;
drop table orders.orderItem;
drop table orders.orders;
drop table people.customer;
drop table catalog.beans;

create table catalog.beans (
    productId varchar(6) constraint catalog_beans_productIdNotNull not null,
    coffeeName varchar(40),
    unitPrice integer,
    primary key (productId),
    unique (coffeeName));

create table people.customer (
    customerId varchar(16) constraint people_customer_customerIdNotNull not null,
    name varchar(30),
    primary key (customerId));
    
create table orders.orders (
    orderId integer constraint orders_orders_orderIdNotNull not null,
    customerId varchar(16) constraint orders_orders_customerIdNotNull not null,
    primary key (orderId),
    foreign key (customerId) references people.customer (customerId));
    
create table orders.orderItem (
	orderItemId integer constraint orders_orderItem_orderItemIdNotNull not null,
    orderId integer constraint orders_orderItem_orderIdNotNull not null,
    productId varchar(6) constraint orders_orderItem_productIdNotNull not null,
    quantity integer,
    unitPricePromised integer,
    primary key (orderItemId),
    foreign key (productId) references catalog.beans (productId),
    foreign key (orderId) references orders.orders (orderId));

-- A flat superclass table

create table catalog.discountDefinition (
    discountDefinitionId integer constraint catalog_discountDefinition_discountDefinitionIdNotNull not null,
    typeName varchar(255),
    percentageOffSubtotal integer,
    primary key (discountDefinitionId));
   
create table catalog.discount (
    discountId integer constraint catalog_discount_discountIdNotNull not null,
    fromDate timestamp,
    toDate timestamp,
    suspended integer,
    discountDefinitionId integer,
    primary key (discountId),
    foreign key (discountDefinitionId) references catalog.discountDefinition (discountDefinitionId));
    
create procedure catalog.addProduct(in p_productId varchar(6), in p_coffeeName varchar(40), in p_unitPrice integer)
modifies sql data
begin
	insert into catalog.beans (productId, coffeeName, unitPrice) 
		values (p_productId, p_coffeeName, p_unitPrice)
; end
