


drop table OBJTYPE CASCADE CONSTRAINTS;
drop table ATTRTYPE CASCADE CONSTRAINTS;
drop table OBJECTS CASCADE CONSTRAINTS;
drop table ATTRIBUTES CASCADE CONSTRAINTS;
drop table OBJREFERENCE CASCADE CONSTRAINTS;

CREATE TABLE OBJTYPE
  (
    OBJECT_TYPE_ID NUMBER(20) NOT NULL ENABLE,
    PARENT_ID      NUMBER(20),
    CODE           VARCHAR2(20) NOT NULL UNIQUE,
    NAME           VARCHAR2(200 BYTE),
    DESCRIPTION    VARCHAR2(1000 BYTE),
    CONSTRAINT CON_OBJECT_TYPE_ID PRIMARY KEY (OBJECT_TYPE_ID),
    CONSTRAINT CON_PARENT_ID FOREIGN KEY (PARENT_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID) ON DELETE CASCADE ENABLE
  );

COMMENT ON TABLE OBJTYPE IS 'Таблица описаний объектных типов';
 
COMMENT ON COLUMN OBJTYPE.OBJECT_TYPE_ID IS 'Идентификатор объектного типа';
COMMENT ON COLUMN OBJTYPE.PARENT_ID IS 'ссылка на идентификатор родительского объектного типа';
COMMENT ON COLUMN OBJTYPE.CODE IS 'название объектного типа в английской кодировке';
COMMENT ON COLUMN OBJTYPE.NAME IS 'название объектного типа в национальной кодировке (для GUI)';
COMMENT ON COLUMN OBJTYPE.DESCRIPTION IS 'разверное описание объектного типа в национальной кодировке (для GUI)';




INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) VALUES (1,NULL,'User','СОТРУДНИК',NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) VALUES (2,null,'Additional','Дополнения',NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) VALUES (3,NULL,'Ingredient','игридиент',NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) VALUES (4,null,'Order','заказ',NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) VALUES (5,null,'pizza','пицца',NULL);










-- Пример команды создания таблицы типов атрибутов:
CREATE TABLE ATTRTYPE
  (
    ATTR_ID      NUMBER(20) NOT NULL ENABLE,
    ATTR_TYPE_ID NUMBER(20) NOT NULL ENABLE,
    CODE         VARCHAR2(20),
    NAME         VARCHAR2(200 BYTE),
    CONSTRAINT CON_ATTR_ID PRIMARY KEY (ATTR_ID),
    CONSTRAINT CON_ATTR_TYPE_ID FOREIGN KEY (ATTR_TYPE_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID) ENABLE
  );
 
COMMENT ON TABLE ATTRTYPE IS 'Таблица описаний атрибутных типов';
 
-- ПРИМЕРЫ ЗАПОЛНЕНИЯ ТАБЛИЦ:
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (1,1,'first_name','имя');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (2,1,'last_name','фамилия');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (3,1,'login','логин');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (4,1,'password','пароль');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (5,1,'warker','работа');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (6,2,'NAME','НАЗВАНИЕ');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (7,2,'price','цена');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (8,3,'name','ИМЯ');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (9,3,'price','цена');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (10,3,'weight','порция');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (11,4,'start_time','старт заказа');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (12,4,'end_time','конец заказа');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (13,4,'price','общая стоимость');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (14,4,'phone','телефон');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (15,5,'NAME','название');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (16,5,'price','цена');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (22,3,'pizzaId','id pizza for ingredients ');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (23,5,'orderId','id order for pizza');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (24,2,'orderId','id order for additional');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (25,2,'logo','отображение');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (26,5,'logo','отображение');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (27,5,'description','описание');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (28,4,'status','Status');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (29,4,'logo','Logo');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (30,4,'description','description');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (31,5,'cook','cook');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (32,4,'nameCustomer','nameCustomer');
INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (33,4,'address','address');








 -- Пример команды создания таблицы объектов:
CREATE TABLE OBJECTS
  (
    OBJECT_ID      NUMBER(20) NOT NULL ENABLE,
    PARENT_ID      NUMBER(20),
    OBJECT_TYPE_ID NUMBER(20) NOT NULL ENABLE,
    NAME           VARCHAR2(2000 BYTE),
    DESCRIPTION    VARCHAR2(4000 BYTE),
    CONSTRAINT CON_OBJECTS_ID PRIMARY KEY (OBJECT_ID),
    CONSTRAINT CON_PARENTS_ID FOREIGN KEY (PARENT_ID) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE DEFERRABLE ENABLE,
    CONSTRAINT CON_OBJ_TYPE_ID FOREIGN KEY (OBJECT_TYPE_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID) ENABLE);





-- Пример команды создания таблицы значений атрибутов:
CREATE TABLE ATTRIBUTES
  (
    ATTR_ID    NUMBER(20) NOT NULL ENABLE,
    OBJECT_ID  NUMBER(20) NOT NULL ENABLE,
    VALUE      VARCHAR2(4000 BYTE),
    DATE_VALUE DATE,
    CONSTRAINT CON_AOBJECT_ID FOREIGN KEY (OBJECT_ID) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE ENABLE,
    CONSTRAINT CON_AATTR_ID FOREIGN KEY (ATTR_ID) REFERENCES ATTRTYPE (ATTR_ID) ON DELETE CASCADE ENABLE
  );  







/*
COMMENT ON TABLE ATTRIBUTES IS 'Таблица описаний атрибутов экземпляров объектов';
COMMENT ON COLUMN ATTRIBUTES.VALUE IS 'Значение атрибута экземпляра объекта в виде строки или числа';
COMMENT ON COLUMN ATTRIBUTES.DATE_VALUE IS 'Значение атрибута экземпляра объекта в виде даты';
*/
-- Пример команды создания таблицы связей:
CREATE TABLE OBJREFERENCE
  (
    ATTR_ID   NUMBER(20) NOT NULL ENABLE,
    REFERENCE NUMBER(20) NOT NULL ENABLE,
    OBJECT_ID NUMBER(20) NOT NULL ENABLE,
    CONSTRAINT CON_REFERENCE FOREIGN KEY (REFERENCE) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE ENABLE,
    CONSTRAINT CON_ROBJECT_ID FOREIGN KEY (OBJECT_ID) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE ENABLE,
    CONSTRAINT CON_RATTR_ID FOREIGN KEY (ATTR_ID) REFERENCES ATTRTYPE (ATTR_ID) ON DELETE CASCADE ENABLE
  ); 

COMMENT ON TABLE OBJREFERENCE IS 'Таблица описаний связей между экземплярами объектов';
COMMENT ON COLUMN OBJREFERENCE.ATTR_ID IS 'Тип связи между экземплярами объектов как атрибутный тип';
COMMENT ON COLUMN OBJREFERENCE.OBJECT_ID IS 'Ссылка на экземпляр 1-го объекта в связи "простая ассоциация"';
COMMENT ON COLUMN OBJREFERENCE.REFERENCE IS 'Ссылка на экземпляр 2-го объекта в связи "простая ассоциация"';


/* При переходе от UML-диаграмме к ORM рекомендуется:
4) связь типа "простая ассоциация" представить в виде связи между OBJREFERENCE.OBJECT_ID и OBJREFERENCE.REFERENCE
*/


--тоблица связей может несовсем правильна



drop  SEQUENCE "ATIKIN"."SEQUENCE1";

-- Функция для получения уникального id 
 create SEQUENCE "ATIKIN"."SEQUENCE1" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 421 CACHE 20 NOORDER NOCYCLE ;






-- add additional
CREATE OR REPLACE
PROCEDURE addAdditional(
    name     IN VARCHAR2,
    order_id IN VARCHAR2,
    price    IN VARCHAR2,
    logo     IN VARCHAR2 )   
is
 
 id   NUMBER(20);


BEGIN
id:=SEQUENCE1.NEXTVAL;
 insert all 
INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (id,null,2,'additional',NULL)
INTO ATTRIBUTES values(24,id,order_id,null)
INTO ATTRIBUTES values(6,id,name,null)
INTO ATTRIBUTES values(7,id,price,null)
INTO ATTRIBUTES values(25,id,logo,null)
select * from dual;
if order_id>0 then
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (24,order_id,id);
end if;
END ;
/





--add ingredients
CREATE OR REPLACE
PROCEDURE addIngredients(
    pizza_id in varchar2,
    name  IN VARCHAR2,
    price IN VARCHAR2,
    weight in varchar2,
    logo in varchar2,
    description in varchar2
    )
is
 id   NUMBER(20);
BEGIN

id:=SEQUENCE1.NEXTVAL;

 insert all 
INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (id,null,3,'ingr',NULL)
INTO ATTRIBUTES values(8,id,name,null)
INTO ATTRIBUTES values(22,id,pizza_id,null)
INTO ATTRIBUTES values(9,id,price,null)
INTO ATTRIBUTES values(10,id,weight,null)
INTO ATTRIBUTES values(29,id,logo,null)
INTO ATTRIBUTES values(30,id,description,null)

select * from dual;
if pizza_id>0 then
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (22,pizza_id,id);
end if;
END addIngredients;
/


--add pizza
CREATE OR REPLACE
procedure addPizza(
     name  IN VARCHAR2,
    order_id in varchar2,
    price IN VARCHAR2,
    logo in varchar2,
    description in varchar2,
    cook in varchar2,
    id_ out number ) 
is

 id   NUMBER(20);
BEGIN
id:=SEQUENCE1.NEXTVAL;

 insert all 
INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (id,null,5,'pizza',NULL)
INTO ATTRIBUTES values(15,id,name,null)
INTO ATTRIBUTES values(23,id,order_id,null)
INTO ATTRIBUTES values(16,id,price,null)
INTO ATTRIBUTES values(26,id,logo,null)
INTO ATTRIBUTES values(27,id,description,null)
INTO ATTRIBUTES values(31,id,cook,null)
select * from dual;
id_:=id;
if order_id>0 then
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (23,order_id,id);
end if;
END addPizza;
/



--add order
CREATE OR REPLACE
procedure addOrder(
    starttime IN VARCHAR2,
    endtime in varchar2,
    price in varchar2,
    status in varchar2,
    phone in varchar2,
    nameCustomer in varchar2,
    address in varchar2,
    id_ out number
   ) 
is
 id   NUMBER(20);
BEGIN
id:=SEQUENCE1.NEXTVAL;
 insert all 
INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (id,null,4,'order',NULL)
INTO ATTRIBUTES values(11,id,starttime,null)
INTO ATTRIBUTES values(28,id,status,null)
INTO ATTRIBUTES values(12,id,endtime,null)
INTO ATTRIBUTES values(13,id,price,null)
INTO ATTRIBUTES values(14,id,phone,null)
INTO ATTRIBUTES values(32,id,nameCustomer,null)
INTO ATTRIBUTES values(33,id,address,null)
select * from dual;
id_:=id;
END addOrder;
/


--call addOrder('1033dsssf00','100331','23','IN_WORK','333','ssss','eee');

--update order
CREATE OR REPLACE
procedure UpdateOrder(
 id number,
    starttime IN VARCHAR2,
    endtime in varchar2,
    price in varchar2,
    status in varchar2,
    phone in varchar2,
    nameCustomer in varchar2,
    address in varchar2
    
    
   ) 
is

BEGIN

 update 
 ATTRIBUTES 
 set value=starttime
 WHERE object_id=id and attr_id=11; 
  update 
 ATTRIBUTES 
 set value=status
 WHERE object_id=id and attr_id=28; 
   update 
 ATTRIBUTES 
 set value=endtime
 WHERE object_id=id and attr_id=12; 
    update 
 ATTRIBUTES 
 set value=price
 WHERE object_id=id and attr_id=13; 
     update 
 ATTRIBUTES 
 set value=phone
 WHERE object_id=id and attr_id=14; 
      update 
 ATTRIBUTES 
 set value=nameCustomer
 WHERE object_id=id and attr_id=32; 
       update 
 ATTRIBUTES 
 set value=address
 WHERE object_id=id and attr_id=33; 
 
END UpdateOrder;
/

--add user
CREATE OR REPLACE
procedure addUser(
    first_name IN VARCHAR2,
    last_name in varchar2,
    login in varchar2,
    passsword in varchar2,
    warker in varchar2,
    idUser number) 
is
 id   NUMBER(20);
BEGIN
id:=SEQUENCE1.NEXTVAL;
 insert all 
INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (id,null,1,'user',NULL)
INTO ATTRIBUTES values(1,id,first_name,null)
INTO ATTRIBUTES values(2,id,last_name,null)
INTO ATTRIBUTES values(3,id,login,null)
INTO ATTRIBUTES values(4,id,passsword,null)
INTO ATTRIBUTES values(5,id,warker,null)
select * from dual;

END addUser;
/
--ua.opu.dl.pizzeria.model.Pizza
--Update user
CREATE OR REPLACE
procedure updateUser(
    first_name IN VARCHAR2,
    last_name in varchar2,
    login in varchar2,
    passsword in varchar2,
    warker in varchar2,
    id number) 
is
 
BEGIN
update ATTRIBUTES
set value=first_name WHERE object_id=id and attr_id=1;
update ATTRIBUTES
set value=last_name WHERE object_id=id and attr_id=2;
update ATTRIBUTES
set value=login WHERE object_id=id and attr_id=3;
update ATTRIBUTES
set value=passsword WHERE object_id=id and attr_id=4;
update ATTRIBUTES
set value=warker WHERE object_id=id and attr_id=5;
END updateUser;
/

CREATE OR REPLACE
procedure addd 
is
id number(20);
BEGIN

addpizza('pizza1','0','29','chikenita_middle.png','Lorem ipsum dolor sit amet  consectetuer adipiscing elit','Empty',id);

addIngredients(id,'Cheese','3','50','chees.png','Cheese');
addIngredients(id,'Cheese','3','50','chees.png','Cheese');
addIngredients(id,'Cheese','3','50','chees.png','Cheese');
addIngredients(id,'Ham','5','100','','hum');
addIngredients(id,'Ham','5','100','','hum');
addIngredients(id,'Vegetables','10','100','','Vegetables');

addpizza('pizza2','0','29','img_2.png','Aenean commodo ligula eget dolor. Aenean massa.','Empty',id);
addIngredients(id,'Cheese','3','50','chees.png','Cheese');
addIngredients(id,'Ham','5','100','','hum');
addIngredients(id,'Ham','5','100','','hum');
addIngredients(id,'Vegetables','10','100','','Vegetables');
addIngredients(id,'Vegetables','10','100','','Vegetables');
addIngredients(id,'Sauce','6','40','','Sauce');

addpizza('pizza3','0','35','pizza_middle.png','Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.','Empty',id);
addIngredients(id,'Cheese','3','50','chees.png','Cheese');
addIngredients(id,'Cheese','3','50','chees.png','Cheese');
addIngredients(id,'Cheese','3','50','chees.png','Cheese');
addIngredients(id,'Ham','5','100','','hum');
addIngredients(id,'Ham','5','100','','hum');
addIngredients(id,'Vegetables','10','100','','Vegetables');
addIngredients(id,'Vegetables','10','100','','Vegetables');
addIngredients(id,'Sauce','6','40','','Sauce');


addpizza('pizza4','0','47','tanu_mini.png','Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.','Empty',id);
addIngredients(id,'Cheese','3','50','chees.png','Cheese');
addIngredients(id,'Cheese','3','50','chees.png','Cheese');
addIngredients(id,'Cheese','3','50','chees.png','Cheese');
addIngredients(id,'Ham','5','100','','hum');
addIngredients(id,'Ham','5','100','','hum');
addIngredients(id,'Vegetables','10','100','','Vegetables');
addIngredients(id,'Vegetables','10','100','','Vegetables');
addIngredients(id,'Sauce','6','40','','Sauce');
addIngredients(id,'mushroom','4','50','mushroom.png','Meat');

addAdditional('cola','0','6','cola.png');
addAdditional('pepsi','0','8','pepsi.png');
addAdditional('gorchica','0','2','gorchica.png');

addIngredients(0,'Cheese','3','50','chees.png','Cheese');
addIngredients(0,'Meat','8','50','meat.png','Meat');
addIngredients(0,'mushroom','4','50','mushroom.png','Meat');
addIngredients(0,'cucumber','1','30','cucumber.png','Meat');
addIngredients(0,'red_pepper','3','50','red_pepper.png','Meat');





END addd;
/


call addd();

commit;


