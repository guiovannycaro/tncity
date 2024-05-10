-- -----------------------------------------------------
-- TRIGERS
-- ------------------------------------------------------



-- -----------------------------------------------------
-- TRIGER barrio
-- ------------------------------------------------------

create function fn_log_audit_barrio() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_barrio()   OWNER TO desarrollo;

create trigger TBL_benefactor_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON barrio FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_barrio() ;





-- -----------------------------------------------------
-- TRIGER benefactor
-- ------------------------------------------------------

create function fn_log_audit_benefactor() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_benefactor()   OWNER TO desarrollo;

create trigger TBL_benefactor_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON benefactor FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_benefactor() ;



-- -----------------------------------------------------
-- TRIGER beneficiario
-- ------------------------------------------------------

create function fn_log_audit_beneficiario() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_beneficiario()   OWNER TO desarrollo;

create trigger TBL_beneficiario_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON beneficiario FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_beneficiario() ;







-- -----------------------------------------------------
-- TRIGER ciudad
-- ------------------------------------------------------

create function fn_log_audit_ciudad() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_ciudad()   OWNER TO desarrollo;

create trigger TBL_ciudad_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON ciudad FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_ciudad() ;



-- -----------------------------------------------------
-- TRIGER configuracion
-- ------------------------------------------------------

create function fn_log_audit_configuracion() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_configuracion()   OWNER TO desarrollo;

create trigger TBL_configuracion_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON configuracion FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_configuracion() ;



-- -----------------------------------------------------
-- TRIGER cuenta
-- ------------------------------------------------------

create function fn_log_audit_cuenta() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_cuenta()   OWNER TO desarrollo;

create trigger TBL_cuenta_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON cuenta FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_cuenta() ;




-- -----------------------------------------------------
-- TRIGER cuentamovimiento
-- ------------------------------------------------------

create function fn_log_audit_cuentamovimiento() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_cuentamovimiento()   OWNER TO desarrollo;

create trigger TBL_cuentamovimiento_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON cuentamovimiento FOR EACH ROW EXECUTE PROCEDURE  fn_log_audit_cuentamovimiento() ;




-- -----------------------------------------------------
-- TRIGER departamentoestado
-- ------------------------------------------------------

create function fn_log_audit_departamentoestado() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_departamentoestado()   OWNER TO desarrollo;

create trigger TBL_departamentoestado_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON departamentoestado FOR EACH ROW EXECUTE PROCEDURE  fn_log_audit_departamentoestado() ;






-- -----------------------------------------------------
-- TRIGER indicativospaises
-- ------------------------------------------------------

create function fn_log_audit_indicativospaises() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_indicativospaises()   OWNER TO desarrollo;

create trigger TBL_indicativospaises_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON indicativospaises FOR EACH ROW EXECUTE PROCEDURE  fn_log_audit_indicativospaises() ;



-- -----------------------------------------------------
-- TRIGER informex_histo
-- ------------------------------------------------------

create function fn_log_audit_informex_histo() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_informex_histo()   OWNER TO desarrollo;

create trigger TBL_INFORMEHISTO_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON informex_histo FOR EACH ROW EXECUTE PROCEDURE  fn_log_audit_informex_histo() ;









-- -----------------------------------------------------
-- TRIGER informex_permiso
-- ------------------------------------------------------

create function fn_log_audit_informex_permiso() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  ffn_log_audit_informex_permiso()  OWNER TO desarrollo;

create trigger TBL_INFORMEXPERMISOS_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON informex_permiso FOR EACH ROW EXECUTE PROCEDURE  fn_log_audit_informex_permiso() ;





-- -----------------------------------------------------
-- TRIGER localidad
-- ------------------------------------------------------

create function fn_log_audit_localidad() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_localidad() OWNER TO desarrollo;

create trigger TBL_LOCALIDAD_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON localidad FOR EACH ROW EXECUTE PROCEDURE  fn_log_audit_localidad();




-- -----------------------------------------------------
-- TRIGER MONEDA
-- ------------------------------------------------------

create function fn_log_audit_moneda() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_moneda() OWNER TO desarrollo;

create trigger TBL_MONEDA_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON moneda FOR EACH ROW EXECUTE PROCEDURE  fn_log_audit_moneda();

-- -----------------------------------------------------
-- TRIGER NOTIFICACION
-- ------------------------------------------------------

create function fn_log_audit_notificacion() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION fn_log_audit_notificacion() OWNER TO desarrollo;

create trigger TBL_NOTIFICACION_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON notificacion FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_notificacion();







-- -----------------------------------------------------
-- TRIGER PAiS
-- ------------------------------------------------------

create function fn_log_audit_pais() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION fn_log_audit_pais() OWNER TO desarrollo;

create trigger TBL_PARAMETROSSMS_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON pais FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_pais();






-- -----------------------------------------------------
-- TRIGER PARAMETROSSMS
-- ------------------------------------------------------

create function fn_log_audit_parametrossms() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_parametrossms() OWNER TO desarrollo;

create trigger TBL_PARAMETROSSMS_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON parametrossms FOR EACH ROW EXECUTE PROCEDURE  fn_log_audit_parametrossms();



-- -----------------------------------------------------
-- TRIGER PASARELAPAGO
-- ------------------------------------------------------

create function fn_log_audit_pasarelapago() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_pasarelapago() OWNER TO desarrollo;

create trigger TBL_PASARELAPAGO_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON pasarelapago FOR EACH ROW EXECUTE PROCEDURE  fn_log_audit_pasarelapago();


-- -----------------------------------------------------
-- TRIGER PASARELAS
-- ------------------------------------------------------

create function fn_log_audit_pasarelas() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION  fn_log_audit_pasarelas() OWNER TO desarrollo;

create trigger TBL_PASARELAS_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON pasarelas FOR EACH ROW EXECUTE PROCEDURE  fn_log_audit_pasarelas();


-- -----------------------------------------------------
-- TRIGER PERFIL
-- ------------------------------------------------------

create function fn_log_audit_perfil() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION fn_log_audit_perfil() OWNER TO desarrollo;

create trigger TBL_PERFIL_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON  perfil  FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_perfil();


-- -----------------------------------------------------
-- TRIGER PERFIL_FUNCIONALIDAD
-- ------------------------------------------------------

create function fn_log_audit_perfil_funcionalidad() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION fn_log_audit_perfil_funcionalidad() OWNER TO desarrollo;

create trigger TBL_PERFIL_FUNCIONALIDAD_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON  perfil_funcionalidad  FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_perfil_funcionalidad();


-- -----------------------------------------------------
-- TRIGER PERFIL_HAS_USUARIO
-- ------------------------------------------------------

create function fn_log_audit_perfil_has_usuario() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION fn_log_audit_perfil_has_usuario() OWNER TO desarrollo;

create trigger TBL_PERFILHASUSUARIO_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON  perfil_has_usuario  FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_perfil_has_usuario();






-- -----------------------------------------------------
-- TRIGER PERSONA
-- ------------------------------------------------------

create function fn_log_audit_persona() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION fn_log_audit_persona() OWNER TO desarrollo;

create trigger TBL_PERSONA_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON  persona  FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_persona();




-- -----------------------------------------------------
-- TRIGER RECAUDO
-- ------------------------------------------------------

create function fn_log_audit_recaudo() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION fn_log_audit_recaudo() OWNER TO desarrollo;

create trigger TBL_RECAUDO_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON  recaudo FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_recaudo();



-- -----------------------------------------------------
-- TRIGER TRANSACCIONES
-- ------------------------------------------------------

create function fn_log_audit_transacciones() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION fn_log_audit_transacciones() OWNER TO desarrollo;

create trigger TBL_TRANSACCIONES_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON transacciones FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_transacciones();


-- -----------------------------------------------------
-- TRIGERtransmensajetexto
-- ------------------------------------------------------

create function fn_log_audit_transmensajetexto() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION fn_log_audit_transmensajetexto() OWNER TO desarrollo;

create trigger TBL_TRANSMENSAJETEXTO_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON transmensajetexto FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_transmensajetexto();


-- -----------------------------------------------------
-- TRIGER USUARIO
-- ------------------------------------------------------

create function fn_log_audit_usuario() returns trigger 	AS
$$
begin
IF(TG_OP = 'DELETE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'D', row_to_json(OLD.*),NULL,NOW(),USER);
RETURN OLD;
ELSEIF (TG_OP = 'UPDATE') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'U',row_to_json(OLD.*),row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
ELSEIF(TG_OP = 'INSERT') THEN
INSERT INTO auditar.AUDITORIA ("NombreTabla","Operacion","ValorAnterior","NuevoValor","UpdateDate","USUARIO")
VALUES (TG_TABLE_NAME,'I',NULL,row_to_json(NEW.*),NOW(),USER);
RETURN NEW;
END IF;
RETURN NULL;
end
$$
Language plpgsql ;
ALTER FUNCTION fn_log_audit_usuario() OWNER TO desarrollo;

create trigger TBL_USUARIO_TB_AUDITORIA AFTER INSERT OR UPDATE OR DELETE
ON usuario FOR EACH ROW EXECUTE PROCEDURE fn_log_audit_usuario();