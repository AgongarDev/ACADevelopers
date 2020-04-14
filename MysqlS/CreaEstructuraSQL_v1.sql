create schema if not exists EntreCulturasDB_v1;
use EntreCulturasDB_v1;

create table ADMINISTRACIONES (
	id_sede smallint not null auto_increment,
    nombre varchar(32) not null,
    direccion varchar(100),
    telefono integer,
    correo varchar(100),
    num_empleados smallint,
    primary key (id_sede),
    unique key nombre_sedeUK (nombre));
    
create table SOCIOS (
	id_socio int not null auto_increment,
    dni varchar(9) not null,
    nombre varchar(32) not null,
    apellido varchar(32) not null,
    direccion varchar(100),
    telefono integer,
    fecha_inicio date,
    fecha_fin date,
    cargo varchar(10),
    correo varchar(100),
    cuota dec,
    estado boolean,
    pass smallint,
    tipo_cuota enum("ANUAL", "TRIM", "MES"),
    sede smallint,
    primary key (id_socio),
    unique key dni_socioUK (dni),
    foreign key FK_sede_vinculada_socio(sede) references ADMINISTRACIONES (id_sede)
    on update cascade
    on delete cascade);
    

    
    