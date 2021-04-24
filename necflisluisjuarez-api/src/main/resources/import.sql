insert into clientes(id, nombre, edad, sexo, pais, fecha_de_creacion) values (1, 'luis', 20, 'masculino', 'guatemala', '23/04/2021');
insert into clientes(id, nombre, edad, sexo, pais, fecha_de_creacion) values (2, 'enrique', 19, 'masculino', 'mexicox', '22/04/2021');

insert into tarifas(id, nombre, descripcion, precio, fecha_de_creacion) values (1, 'mes', 'plan_con_vigencia_de_un_mes', '50', '23/04/2021');
insert into tarifas(id, nombre, descripcion, precio, fecha_de_creacion) values (2, 'año', 'plan_con_vigencia_de_un_año', '550', '25/04/2021');

insert into pagos(id, fecha, monto, numero_tarjeta, estado) values (1, '20/04/2021', '250', '0100305', 'Aceptado');
insert into pagos(id, fecha, monto, numero_tarjeta, estado) values (2, '18/04/2021', '200', '0305877', 'Rechazadp');