


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
--INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (17,4,'id','id order');
--INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (18,1,'id','id user');
--INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (19,2,'id','id additional');
--INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (20,3,'id','id ingredient');
--INSERT INTO ATTRTYPE (ATTR_ID,ATTR_TYPE_ID,CODE,NAME) VALUES (21,5,'id','id pizza');
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


/*
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (1,null,1,'ivanov ivan ivanich',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (2,null,1,'petrov petr petrovich',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (3,null,1,'sidorov sergey sidorovich',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (4,null,2,'cola',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (5,null,2,'pepsi',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (6,null,3,'mushrooms',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (7,null,3,'chees',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (8,null,3,'chicken',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (9,null,3,'olives',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (10,null,3,'pepper',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (11,null,4,'order',NULL);
iNSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (12,null,5,'pizza1',NULL);
iNSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (13,null,5,'pizza2',NULL);
iNSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (14,null,5,'pizza3',NULL);
iNSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (15,null,4,'order',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (16,null,2,'cola',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (17,null,2,'cola',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (18,null,3,'mushrooms',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (19,null,3,'chees',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (20,null,3,'chees',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (21,null,3,'chees',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (22,null,3,'pepper',NULL);*/



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
INSERT INTO ATTRIBUTES values(1,1,'ivanov',null);
INSERT INTO ATTRIBUTES values(1,2,'petrtov',null);
INSERT INTO ATTRIBUTES values(1,3,'sidirov',null);
--INSERT INTO ATTRIBUTES values(18,1,'1',null);
--INSERT INTO ATTRIBUTES values(18,2,'2',null);
--INSERT INTO ATTRIBUTES values(18,3,'3',null);
INSERT INTO ATTRIBUTES values(2,1,'ivan',null);
INSERT INTO ATTRIBUTES values(2,2,'petr',null);
INSERT INTO ATTRIBUTES values(2,3,'sergey',null);
INSERT INTO ATTRIBUTES values(3,1,'ivanov',null);
INSERT INTO ATTRIBUTES values(3,2,'petrov',null);
INSERT INTO ATTRIBUTES values(3,3,'sidorov',null);
INSERT INTO ATTRIBUTES values(4,1,'12345',null);
INSERT INTO ATTRIBUTES values(4,2,'12345',null);
INSERT INTO ATTRIBUTES values(4,3,'12345',null);
INSERT INTO ATTRIBUTES values(5,1,'COOK',null);
INSERT INTO ATTRIBUTES values(5,2,'manager',null);
INSERT INTO ATTRIBUTES values(5,3,'COOK',null);
INSERT INTO ATTRIBUTES values(6,4,'cola',null);
INSERT INTO ATTRIBUTES values(6,5,'pepsi',null);
INSERT INTO ATTRIBUTES values(24,4,'11',null);
INSERT INTO ATTRIBUTES values(24,5,'11',null);
INSERT INTO ATTRIBUTES values(25,4,'cola.png',null);
INSERT INTO ATTRIBUTES values(25,5,'pepsi.png',null);
--INSERT INTO ATTRIBUTES values(19,14,'1',null);
--INSERT INTO ATTRIBUTES values(19,5,'2',null);
INSERT INTO ATTRIBUTES values(7,4,'6',null);
INSERT INTO ATTRIBUTES values(7,5,'5',null);
INSERT INTO ATTRIBUTES values(6,16,'cola',null);
INSERT INTO ATTRIBUTES values(6,17,'c0la',null);
INSERT INTO ATTRIBUTES values(24,16,'15',null);
INSERT INTO ATTRIBUTES values(24,17,'15',null);
--INSERT INTO ATTRIBUTES values(19,16,'3',null);
--INSERT INTO ATTRIBUTES values(19,17,'4',null);
INSERT INTO ATTRIBUTES values(7,16,'6',null);
INSERT INTO ATTRIBUTES values(7,17,'5',null);
INSERT INTO ATTRIBUTES values(25,16,'cola.png',null);
INSERT INTO ATTRIBUTES values(25,17,'pepsi.png',null);
INSERT INTO ATTRIBUTES values(8,6,'mushrooms',null);
INSERT INTO ATTRIBUTES values(8,7,'chess',null);
INSERT INTO ATTRIBUTES values(8,8,'chicken',null);
INSERT INTO ATTRIBUTES values(8,9,'olives',null);
INSERT INTO ATTRIBUTES values(8,10,'pepper',null);
--INSERT INTO ATTRIBUTES values(20,6,'1',null);
--INSERT INTO ATTRIBUTES values(20,7,'2',null);
--INSERT INTO ATTRIBUTES values(20,8,'3',null);
--INSERT INTO ATTRIBUTES values(20,9,'4',null);
--INSERT INTO ATTRIBUTES values(20,10,'5',null);
INSERT INTO ATTRIBUTES values(22,6,'12',null);
INSERT INTO ATTRIBUTES values(22,7,'12',null);
INSERT INTO ATTRIBUTES values(22,8,'12',null);
INSERT INTO ATTRIBUTES values(22,9,'12',null);
INSERT INTO ATTRIBUTES values(22,10,'12',null);
INSERT INTO ATTRIBUTES values(9,6,'5',null);
INSERT INTO ATTRIBUTES values(9,7,'10',null);
INSERT INTO ATTRIBUTES values(9,8,'3',null);
INSERT INTO ATTRIBUTES values(9,9,'2',null);
INSERT INTO ATTRIBUTES values(9,10,'1',null);
INSERT INTO ATTRIBUTES values(10,6,'50',null);
INSERT INTO ATTRIBUTES values(10,7,'100',null);
INSERT INTO ATTRIBUTES values(10,8,'100',null);
INSERT INTO ATTRIBUTES values(10,9,'50',null);
INSERT INTO ATTRIBUTES values(10,10,'50',null);
INSERT INTO ATTRIBUTES values(8,18,'mushrooms',null);
INSERT INTO ATTRIBUTES values(8,19,'chees',null);
INSERT INTO ATTRIBUTES values(8,20,'chees',null);
INSERT INTO ATTRIBUTES values(8,21,'chees',null);
INSERT INTO ATTRIBUTES values(8,22,'pepper',null);
--INSERT INTO ATTRIBUTES values(20,18,'6',null);
--INSERT INTO ATTRIBUTES values(20,19,'7',null);
--INSERT INTO ATTRIBUTES values(20,20,'8',null);
--INSERT INTO ATTRIBUTES values(20,21,'9',null);
--INSERT INTO ATTRIBUTES values(20,22,'10',null);
INSERT INTO ATTRIBUTES values(22,18,'13',null);
INSERT INTO ATTRIBUTES values(22,19,'13',null);
INSERT INTO ATTRIBUTES values(22,20,'13',null);
INSERT INTO ATTRIBUTES values(22,21,'13',null);
INSERT INTO ATTRIBUTES values(22,22,'13',null);
INSERT INTO ATTRIBUTES values(9,18,'5',null);
INSERT INTO ATTRIBUTES values(9,19,'10',null);
INSERT INTO ATTRIBUTES values(9,20,'10',null);
INSERT INTO ATTRIBUTES values(9,21,'10',null);
INSERT INTO ATTRIBUTES values(9,22,'1',null);
INSERT INTO ATTRIBUTES values(10,18,'50',null);
INSERT INTO ATTRIBUTES values(10,19,'100',null);
INSERT INTO ATTRIBUTES values(10,20,'100',null);
INSERT INTO ATTRIBUTES values(10,21,'100',null);
INSERT INTO ATTRIBUTES values(10,22,'50',null);
INSERT INTO ATTRIBUTES values(11,11,'12.05.13 17.00',null);
INSERT INTO ATTRIBUTES values(11,15,'12.05.13 18.00',null);
INSERT INTO ATTRIBUTES values(28,11,'IN_WORK',null);
INSERT INTO ATTRIBUTES values(28,15,'IN_WORK',null);
INSERT INTO ATTRIBUTES values(12,11,'12.05.13 18.50',null);
INSERT INTO ATTRIBUTES values(12,15,'12.05.13 20.00',null);
INSERT INTO ATTRIBUTES values(13,11,'50',null);
INSERT INTO ATTRIBUTES values(13,15,'70',null);
INSERT INTO ATTRIBUTES values(14,11,'5656465456456',null);
INSERT INTO ATTRIBUTES values(14,15,'456456456456',null);
--INSERT INTO ATTRIBUTES values(17,11,'1',null);
--INSERT INTO ATTRIBUTES values(17,15,'2',null);
INSERT INTO ATTRIBUTES values(15,12,'pizza1',null);
INSERT INTO ATTRIBUTES values(15,13,'pizza2',null);
INSERT INTO ATTRIBUTES values(15,14,'pizza3',null);
--INSERT INTO ATTRIBUTES values(21,12,'1',null);
--INSERT INTO ATTRIBUTES values(21,13,'2',null);
--INSERT INTO ATTRIBUTES values(21,14,'3',null);
INSERT INTO ATTRIBUTES values(23,12,'11',null);
INSERT INTO ATTRIBUTES values(23,13,'15',null);
--INSERT INTO ATTRIBUTES values(23,14,'3',null);
INSERT INTO ATTRIBUTES values(16,12,'30',null);
INSERT INTO ATTRIBUTES values(16,13,'40',null);
INSERT INTO ATTRIBUTES values(16,14,'50',null);
INSERT INTO ATTRIBUTES values(26,12,'chikenita_middle.png',null);
INSERT INTO ATTRIBUTES values(26,13,'pizza_middle.png',null);
INSERT INTO ATTRIBUTES values(26,14,'tanu_mini.png',null);
INSERT INTO ATTRIBUTES values(27,12,'Lorem ipsum dolor sit amet, consectetuer adipiscing elit',null);
INSERT INTO ATTRIBUTES values(27,13,'Aenean commodo ligula eget dolor. Aenean massa.',null);
INSERT INTO ATTRIBUTES values(27,14,'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.',null);*/



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
/*
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (24,11,4);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (24,11,5);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (22,12,6);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (22,12,7);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (22,12,8);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (22,12,9);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (22,12,10);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (23,11,12);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (23,15,13);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (24,15,16);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (24,15,17);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (22,13,18);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (22,13,19);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (22,13,20);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (22,13,21);
INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (22,13,22);*/




-- Функция для получения уникального id 

CREATE OR REPLACE FUNCTION get_id RETURN NUMBER
  AS
    v_ret NUMBER;
  BEGIN
  
  
  
    SELECT TO_CHAR(sysdate,'yyyymmddhh24miss') || TRUNC (DBMS_RANDOM.VALUE (0, 100))
      INTO v_ret
    FROM DUAL;
    RETURN v_ret;
  END;
  /
-- пример использования

--select get_id from dual







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
id:=get_id;
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
DBMS_LOCK.sleep(0.9);
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

id:=get_id;

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
DBMS_LOCK.sleep(0.9);
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
id:=get_id;

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
DBMS_LOCK.sleep(0.9);
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
    id_ out number) 
is
 id   NUMBER(20);
BEGIN
id:=get_id;
 insert all 
INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (id,null,4,'order',NULL)
INTO ATTRIBUTES values(11,id,starttime,null)
INTO ATTRIBUTES values(28,id,status,null)
INTO ATTRIBUTES values(12,id,endtime,null)
INTO ATTRIBUTES values(13,id,price,null)
INTO ATTRIBUTES values(14,id,phone,null)
select * from dual;
id_:=id;
DBMS_LOCK.sleep(0.9);
END addOrder;
/

--ua.opu.dl.pizzeria.model.Pizza

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









/*

---loadAllAdditional
  

  with tab1 as(  select att.value start_time,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='start_time' 
 ),
  
  tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where
     obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='price' 
 ),
 
   tab3 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='logo' 
 ),
    tab4 as (  select att.value orderId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='orderId' 
 )
 
 select tab1.id_ id,tab4.orderId orderId,tab1.name_ name,tab2.price price,tab3.logo logo from tab1,tab2,tab3,tab4
 where
 tab1.ID_=tab2.ID_ and
 tab2.ID_=tab3.ID_ and
 tab3.Id_=tab4.Id_ and

tab1.obtypeId=2 
 
 --- load allAdditionalfromOrderId
  with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='NAME' 
 ),
  
  tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where
     obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='price' 
 ),
 
   tab3 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='logo' 
 ),
    tab4 as (  select att.value orderId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='orderId' 
 ),
tab5 as
( select tab1.id_ id,tab4.orderId orderId,tab1.name_ name,tab2.price price,tab3.logo logo from tab1,tab2,tab3,tab4
 where
 tab1.ID_=tab2.ID_ and
 tab2.ID_=tab3.ID_ and
 tab3.Id_=tab4.Id_ and

tab1.obtypeId=2 )

select* from tab5 where orderId='1'
 
 
 
 --- load allingredientslfrompizzaId
 
  
   with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='name' 
 ),
  
  tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where
     obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='price' 
 ),
 
   tab3 as (  select att.value weight, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='weight' 
 ),
    tab4 as (  select att.value pizzaId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='pizzaId' 
 ),
     tab6 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='logo' 
 ),
     tab7 as (  select att.value description, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='description' 
 ),
tab5 as
( select tab1.id_ id,tab4.pizzaId pizzaId,tab1.name_ name,tab2.price price,tab3.weight weight,tab6.logo logo,tab7.description description from tab1,tab2,tab3,tab4,tab6,tab7
 where
 tab1.ID_=tab2.ID_ and
 tab2.ID_=tab3.ID_ and
 tab3.Id_=tab4.Id_ and
  tab4.Id_=tab6.Id_ and
   tab6.Id_=tab7.Id_ and

tab1.obtypeId=3 )

select* from tab5 where pizzaId='2014020223493399';
 
 


--load ingredientbyId

  
   with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='name' 
 ),
  
  tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where
     obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='price' 
 ),
 
   tab3 as (  select att.value weight, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='weight' 
 ),
    tab4 as (  select att.value pizzaId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='pizzaId' 
 ),
     tab6 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='logo' 
 ),
     tab7 as (  select att.value description, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='description' 
 )
select tab1.id_ id,tab4.pizzaId pizzaId,tab1.name_ name,tab2.price price,tab3.weight weight,tab6.logo logo,tab7.description description from tab1,tab2,tab3,tab4,tab6,tab7
 where
 tab1.ID_=tab2.ID_ and
 tab2.ID_=tab3.ID_ and
 tab3.Id_=tab4.Id_ and
  tab4.Id_=tab6.Id_ and
   tab6.Id_=tab7.Id_ and


tab1.obtypeId=3 and tab1.id_=2014020223493429;


--- Pizza loadByorderId
 
  
   with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='NAME' 
 ),
  
  tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where
     obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='price' 
 ),
 
   tab3 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='logo' 
 ),
    tab4 as (  select att.value orderId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='orderId' 
 ),
   tab44 as (  select att.value description, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='description' 
 ),
  tab6 as (  select att.value cook, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='cook' 
 ),
 
tab5 as
( select tab1.id_ id,tab1.name_ name,tab4.orderId orderId,tab2.price price,tab3.logo logo,tab44.description description,tab6.cook cook from tab1,tab2,tab3,tab4,tab44,tab6
 where
 tab1.ID_=tab2.ID_ and
 tab2.ID_=tab3.ID_ and
 tab3.Id_=tab4.Id_ and
 tab4.Id_=tab44.Id_ and
  tab44.Id_=tab6.Id_ and


tab1.obtypeId=5 )

select* from tab5 where orderId='0'
 
 




--- Pizza loadById
 
  
   with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='NAME' 
 ),
  
  tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where
     obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='price' 
 ),
 
   tab3 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='logo' 
 ),
    tab4 as (  select att.value orderId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='orderId' 
 ),
   tab44 as (  select att.value description, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='description' 
 ),
 tab6 as (  select att.value cook, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='cook' 
 )

 select tab1.id_ id,tab1.name_ name,tab4.orderId orderId,tab2.price price,tab3.logo logo,tab44.description description,tab6.cook cook from tab1,tab2,tab3,tab4,tab44,tab6
 where
 tab1.ID_=tab2.ID_ and
 tab2.ID_=tab3.ID_ and
 tab3.Id_=tab4.Id_ and
 tab4.Id_=tab44.Id_ and
  tab44.Id_=tab6.Id_ and


tab1.obtypeId=5 and tab1.id_=2014020415454431

--select* from tab5 where orderId='11'


--loadOrderById

 with tab1 as(  select att.value start_time,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='start_time' 
 ),
  
  tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where
     obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='price' 
 ),
 
   tab3 as (  select att.value end_time, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='end_time' 
 ),
    tab4 as (  select att.value status, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='status' 
 ),
   tab44 as (  select att.value phone, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='phone' 
 )
 

 select tab1.id_ id,tab4.status status,tab1.start_time start_time,tab3.end_time end_time,tab2.price price,tab44.phone phone from tab1,tab2,tab3,tab4,tab44
 where
 tab1.ID_=tab2.ID_ and
 tab2.ID_=tab3.ID_ and
 tab3.Id_=tab4.Id_ and
 tab4.Id_=tab44.Id_ and


tab1.obtypeId=4 and tab1.id_=11


--loadOrderByPhone

 with tab1 as(  select att.value start_time,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='start_time' 
 ),
  
  tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where
     obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='price' 
 ),
 
   tab3 as (  select att.value end_time, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='end_time' 
 ),
    tab4 as (  select att.value status, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='status' 
 ),
   tab44 as (  select att.value phone, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where 
      obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and
ob.object_id = att.object_id and
att.attr_id=attr.attr_id and
attr.code='phone' 
 ),
 
tab5 as(
 select tab1.id_ id,tab4.status status,tab1.start_time start_time,tab3.end_time end_time,tab2.price price,tab44.phone phone from tab1,tab2,tab3,tab4,tab44
 where
 tab1.ID_=tab2.ID_ and
 tab2.ID_=tab3.ID_ and
 tab3.Id_=tab4.Id_ and
 tab4.Id_=tab44.Id_ and


tab1.obtypeId=4 )


select* from tab5 where phone='5656465456456'

*/
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 













