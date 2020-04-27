create schema if not exists EntreCulturasDB_v1;
use EntreCulturasDB_v1;

create table if not exists ADMINISTRACIONES (
	id_sede smallint not null auto_increment,
    nombre varchar(32) not null,
    direccion varchar(100),
    telefono integer,
    correo varchar(100),
    num_empleados smallint,
    primary key (id_sede),
    unique key UK_nombre_sedeUK (nombre));
    
create table if not exists CONTACTOS (
	id_contacto int not null auto_increment,
    dni varchar(9) not null,
	pass varchar(100),
    nombre varchar(32) not null,
    apellido varchar(32) not null,
    direccion varchar(100),
    telefono integer,
    fecha_inicio date,
    fecha_fin date,
    cargo varchar(10),
    correo varchar(100),
    sede smallint,
    primary key (id_contacto),
    unique key dni_contactoUK (dni),
    foreign key FK_sede_vinculada(sede) references ADMINISTRACIONES (id_sede)
    on update cascade
    on delete cascade);
    
create table if not exists SOCIOS (
	id_socio int not null,
	cuota dec,
    estado boolean,
    tipo_cuota enum("ANUAL", "TRIM", "MES"),
    primary key (id_socio),
    foreign key FK_socio_contacto(id_socio) references CONTACTOS (id_contacto) 
    on update cascade on delete cascade);
    
create table if not exists PERSONAL (
	id_trabajador int not null auto_increment,
    salario_bruto dec,
    salario_neto dec,
    tipo_contrato varchar(32),
    primary key (id_trabajador),
    foreign key FK_personal_contacto(id_trabajador) references CONTACTOS (id_contacto)
    on update cascade on delete cascade);    
    
create table if not exists APORTADORES (
	id_aportador int not null auto_increment,
    tipo_aportador enum ("Institucion", "Empresa", "Particular", "Herencias"),
    primary key (id_aportador));
    
create table if not exists COLABORADORES (
	id_colaborador int not null auto_increment,
    nombre varchar(32) not null,
    direccion varchar(100),
    telefono integer,
    correo varchar(100),
    primary key (id_colaborador),
    unique key UK_nombre_colaborador (nombre));
    
create table if not exists PROYECTOS (
	id_proyecto int not null auto_increment,
    nombre varchar(64),
    fecha_inicio date,
    fecha_fin date,
    linea_accion enum ("COOP", "ACC", "FORT", "EDU"),
    sublinea_accion varchar(120),
    pais varchar(40),
    direccion varchar(100),
    socio_local int,
    financiador int,
    financiacion dec,
    primary key (id_proyecto),
    foreign key FK_financiador_proyecto (financiador) references APORTADORES (id_aportador)
    on update cascade on delete cascade,
    foreign key FK_socio_local_proyecto (socio_local) references COLABORADORES (id_colaborador)
    on update cascade on delete cascade);
    
create table if not exists ACCIONES (
	id_accion int not null auto_increment,
    nombre varchar(120),
    descripcion varchar(3000),
    primary key (id_accion));
    
create table if not exists PERSONAL_PROYECTO ( 
	id_trabajador int not null,
    id_proyecto int not null,
    primary key (id_trabajador, id_proyecto),
    foreign key FK_personal_proyecto (id_trabajador) references PERSONAL (id_trabajador) 
    on update cascade on delete restrict,
    foreign key FK_proyectos_trabajador (id_proyecto) references PROYECTOS (id_proyecto)
    on update cascade on delete restrict);
    
create table if not exists ACCIONES_PROYECTO (
	id_accion int not null,
    id_proyecto int not null,
    primary key (id_accion, id_proyecto),
    foreign key FK_accion_proyecto (id_accion) references ACCIONES (id_accion) 
    on update cascade on delete restrict,
    foreign key FK_proyecto_accion (id_proyecto) references PROYECTOS (id_proyecto)
    on update cascade on delete restrict);
    
    
    
    