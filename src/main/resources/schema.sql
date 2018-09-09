create table item_inventory
(
   id INTEGER NOT null ,
   item varchar (255) NOT null,
   sell_in INTEGER NOT null,
   quality INTEGER NOT null,
   add_date DATE,
   quality_change boolean,
   increment boolean,
   decrement boolean,
   double_increment boolean,
   double_decrement boolean,
   primary key(id)
);

