/************ Add: Sequences ***************/
CREATE DATABASE tncityDB;

-- CREACION DE SQUEMA PUBLICO EN CASO DE QUE NO EXISTA;

CREATE SCHEMA public AUTHORIZATION postgres;

COMMENT ON SCHEMA public IS 'standard public schema';

-- CREACION DE SQUEMA AUDITAR ;

CREATE SCHEMA auditar AUTHORIZATION postgres;

COMMENT ON SCHEMA public IS 'standard auditar schema';