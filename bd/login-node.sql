
create database registro;
 use registro;

 create table controlEquipos(
 id int  primary key auto_increment,
 Nserie varchar(200),
 marca varchar(50),
 modelo varchar(80),
 Etecnicas varchar(250),
 descripcion varchar(250),
 estado varchar(80),
 fechaCompra date
 );
 

 select * from controlEquipos;

insert into  controlEquipos (Nserie,marca,modelo,Etecnicas,descripcion,estado,fechaCompra) values('202301','hp','core i5','procesador intel','le falta procesador','inactivo','2023-12-01');

update controlEquipos set Nserie='',marca='',modelo='',Etecnicas='',descripcion='',estado='',fechaCompra='' where id='1';

-- delete from controlEquipos where id='1';
 