CREATE SEQUENCE public.ciudad_idciudad_seq INCREMENT BY 1 START 1;

CREATE SEQUENCE public.departamentoestado_iddepartamento_seq INCREMENT BY 1 START 1;

CREATE SEQUENCE public.pais_idpais_seq INCREMENT BY 1 START 1;

CREATE SEQUENCE public.perfil_idperfil_seq INCREMENT BY 1 START 1;

CREATE SEQUENCE public.persona_idpersona_seq INCREMENT BY 1 START 1;

CREATE SEQUENCE public.usuario_idusuario_seq INCREMENT BY 1 START 1;

CREATE SEQUENCE public.productos_idproducto_seq INCREMENT BY 1 START 1;

CREATE SEQUENCE public.bodegas_idbodega_seq INCREMENT BY 1 START 1;

CREATE SEQUENCE public.trasporte_idtrasporte_seq INCREMENT BY 1 START 1;

CREATE SEQUENCE public.vehiculas_idvehiculo_seq INCREMENT BY 1 START 1;

CREATE TABLE public.ciudad
(
	idciudad INTEGER DEFAULT nextval('ciudad_idciudad_seq'::regclass) NOT NULL,
	nombre VARCHAR NOT NULL,
	codoficial VARCHAR NULL,
	latitud DOUBLE PRECISION NULL,
	longitud DOUBLE PRECISION NULL,
	iddepartamento INTEGER NOT NULL,
	codpostal VARCHAR NULL
) WITHOUT OIDS;

/* Add Primary Key */
ALTER TABLE public.ciudad ADD CONSTRAINT ciudad_pkey
	PRIMARY KEY (idciudad);

/* Build Table Structure */
CREATE TABLE public.departamentoestado
(
	iddepartamento INTEGER DEFAULT nextval('departamentoestado_iddepartamento_seq'::regclass) NOT NULL,
	idpais INTEGER NOT NULL,
	codoficial VARCHAR NOT NULL,
	nombre VARCHAR NOT NULL,
	latitud DOUBLE PRECISION NULL,
	longitud DOUBLE PRECISION NULL
) WITHOUT OIDS;

/* Add Primary Key */
ALTER TABLE public.departamentoestado ADD CONSTRAINT departamentoestado_pkey
	PRIMARY KEY (iddepartamento);

/* Add Comments */
COMMENT ON COLUMN public.departamentoestado.iddepartamento IS 'Llave primaria';

COMMENT ON COLUMN public.departamentoestado.idpais IS 'Pais al que pertenece';

COMMENT ON COLUMN public.departamentoestado.codoficial IS 'Cod ificial del departamento';

COMMENT ON COLUMN public.departamentoestado.nombre IS 'Nombre del departamento';

COMMENT ON COLUMN public.departamentoestado.latitud IS 'Latitud del departamento';

COMMENT ON COLUMN public.departamentoestado.longitud IS 'Longitud del departamento';

COMMENT ON TABLE public.departamentoestado IS 'Registra los departamentos o estados de los paises';


/* Build Table Structure */
CREATE TABLE public.pais
(
	idpais INTEGER DEFAULT nextval('pais_idpais_seq'::regclass) NOT NULL,
	nombre VARCHAR NOT NULL,
	cod_oficial_iso VARCHAR NULL,
	iso2 VARCHAR NOT NULL,
	iso3 VARCHAR NOT NULL,
	latitud DOUBLE PRECISION NULL,
	longitud DOUBLE PRECISION NULL,
	codpostal VARCHAR NULL
) WITHOUT OIDS;

/* Add Primary Key */
ALTER TABLE public.pais ADD CONSTRAINT pais_pkey
	PRIMARY KEY (idpais);

/* Add Comments */
COMMENT ON COLUMN public.pais.idpais IS 'Llave primaria';

COMMENT ON COLUMN public.pais.nombre IS 'Nombre del país';

COMMENT ON COLUMN public.pais.cod_oficial_iso IS 'Cód oficial ISO';

COMMENT ON COLUMN public.pais.iso2 IS 'ISO 2';

COMMENT ON COLUMN public.pais.iso3 IS 'ISO 3';

COMMENT ON COLUMN public.pais.latitud IS 'Latitud';

COMMENT ON COLUMN public.pais.longitud IS 'Longitud';

COMMENT ON TABLE public.pais IS 'Se registran los paises';

CREATE TABLE public.perfil
(
	idperfil INTEGER DEFAULT nextval('perfil_idperfil_seq'::regclass) NOT NULL,
	nombre VARCHAR NOT NULL,
	observaciones TEXT NULL
) WITHOUT OIDS;

/* Add Primary Key */
ALTER TABLE public.perfil ADD CONSTRAINT perfil_pkey
	PRIMARY KEY (idperfil);

/* Add Comments */
COMMENT ON TABLE public.perfil IS 'Registra los perfiles del sistema';


CREATE TABLE public.persona
(
	idpersona BIGINT DEFAULT nextval('persona_idpersona_seq'::regclass) NOT NULL,
	nombres VARCHAR NOT NULL,
	apellidos VARCHAR NOT NULL,
	tipodocumento VARCHAR NOT NULL,
	numdocumento NUMERIC(20, 0) NOT NULL,
	num_telefono NUMERIC(15, 0) NULL,
	email VARCHAR NULL,
	direccion TEXT NULL,
	idusuario_updated INTEGER NULL,
	updated_at TIMESTAMP NOT NULL,
	idusuario_created INTEGER NULL,
	created_at TIMESTAMP NOT NULL,
	num_telefono2 NUMERIC(10, 0) NULL,
	idciudad INTEGER NULL,
	imagen VARCHAR NULL
) WITHOUT OIDS;

/* Add Primary Key */
ALTER TABLE public.persona ADD CONSTRAINT pkpersona
	PRIMARY KEY (idpersona);

/* Add Comments */
COMMENT ON COLUMN public.persona.idpersona IS 'Llave primaria';

COMMENT ON COLUMN public.persona.nombres IS 'Primer y segundo nombre';

COMMENT ON COLUMN public.persona.apellidos IS 'Primer y segundo apellido';

COMMENT ON COLUMN public.persona.tipodocumento IS 'Tipo de documento de identidad';

COMMENT ON COLUMN public.persona.numdocumento IS 'Número de documento de identidad';

COMMENT ON COLUMN public.persona.num_telefono IS 'Número de teléfono';

COMMENT ON COLUMN public.persona.email IS 'Correo electrónico';

COMMENT ON COLUMN public.persona.direccion IS 'Dirección de residencia';

COMMENT ON COLUMN public.persona.idusuario_updated IS 'Usuario que actualiza el registro';

COMMENT ON COLUMN public.persona.updated_at IS 'Fecha de actualización';

COMMENT ON COLUMN public.persona.idusuario_created IS 'Usuario que crea el registro';

COMMENT ON COLUMN public.persona.created_at IS 'Fecha de creación de registro';

COMMENT ON COLUMN public.persona.num_telefono2 IS 'Teléfono 2';

COMMENT ON TABLE public.persona IS 'Registra todas las personas que tienen que ver con el sistema de una u otra forma.';

/* Add Unique Constraints */
ALTER TABLE public.persona
	ADD CONSTRAINT persona_numdocumento_key UNIQUE (numdocumento);

/* Add Indexes */
CREATE UNIQUE INDEX persona_numdocumento_uniq ON public.persona USING btree (numdocumento);



CREATE TABLE public.usuario
(
	idusuario INTEGER DEFAULT nextval('usuario_idusuario_seq'::regclass) NOT NULL,
	idpersona BIGINT NOT NULL,
	username VARCHAR NOT NULL,
	contrasena TEXT NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	is_activo BOOL NOT NULL,
	is_cambiarcontrasena BOOL NULL
) WITHOUT OIDS;

/* Add Primary Key */
ALTER TABLE public.usuario ADD CONSTRAINT usuario_pkey
	PRIMARY KEY (idusuario);

/* Add Comments */
COMMENT ON COLUMN public.usuario.idusuario IS 'Llave primaria';

COMMENT ON COLUMN public.usuario.idpersona IS 'Persona que es usuario';

COMMENT ON COLUMN public.usuario.username IS 'Login';

COMMENT ON COLUMN public.usuario.contrasena IS 'Contraseña de acceso';

COMMENT ON COLUMN public.usuario.updated_at IS 'Fecha de actualización';

COMMENT ON COLUMN public.usuario.is_activo IS 'Indica si esta o no activo el usuario';

COMMENT ON TABLE public.usuario IS 'Registra los usuarios que tienen acceso al sistema';

/* Add Unique Constraints */
ALTER TABLE public.usuario
	ADD CONSTRAINT usuario_idpersona_key UNIQUE (idpersona);

ALTER TABLE public.usuario
	ADD CONSTRAINT usuario_username_key UNIQUE (username);

/* Add Indexes */
CREATE UNIQUE INDEX usuario_idpersona_idx ON public.usuario USING btree (idpersona);

CREATE UNIQUE INDEX usuario_username_uniq ON public.usuario USING btree (username);
