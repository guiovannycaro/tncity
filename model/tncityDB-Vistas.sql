-- -----------------------------------------------------
-- VISTAS
-- ------------------------------------------------------



-- -----------------------------------------------------
-- vista vista_recargas_webservices
-- ------------------------------------------------------

CREATE OR REPLACE VIEW public.vista_recargas_webservices AS 
 SELECT recaudo.idrecaudo AS idvista,
    persona.nombres,
    persona.apellidos,
    persona.tipodocumento AS tipo_documento,
    persona.numdocumento AS numero_documento,
    persona.num_telefono,
    persona.email,
    persona.direccion,
    ciudad.nombre AS ciudad_familiar,
    departamentoestado.nombre AS departamento_familiar,
    pais.nombre AS pais_familiar,
    beneficiario.td AS tipodocumento_ppl,
    beneficiario.pin AS numero_documento_ppl,
    beneficiario.nombres_apellidos AS nombre_ppl,
    recaudo.idrecaudo,
    recaudo.valor,
    recaudo.estado,
    recaudo.log,
    recaudo.created_at,
    recaudo.check_telefonia_at,
    recaudo.establecimiento,
    recaudo.cod_pago,
    recaudo.telefonosms,
    recaudo.observacion,
    recaudo.observacion_adicional,
    recaudo.ciudad AS ciudad_ppl,
    pasarelapago.nombre AS nombre_pasarela,
    transacciones.codtransaccion,
    transacciones.formapago,
    transacciones.franquicia,
    transacciones.descripcion,
    transacciones.referencia1,
    transacciones.fechapago,
    transacciones.numerorecibo,
    transacciones.codigo,
    transacciones.mensajeerror
   FROM recaudo
  
 join benefactor on recaudo.idbenefactor = benefactor.idbenefactor 
  join beneficiario on recaudo.idbeneficiario = beneficiario.idbeneficiario 
 join persona on benefactor.idpersona = persona.idpersona 
 join ciudad on persona.idciudad = ciudad.idciudad 
 join departamentoestado ON ciudad.iddepartamento = departamentoestado.iddepartamento 
 join pais on  departamentoestado.idpais = pais.idpais 
 join pasarelapago on recaudo.idpasarela = pasarelapago.idpasarela 
join  transacciones on recaudo.idrecaudo = transacciones.idrecaudo WHERE  recaudo.idpasarela <> 1

-- -----------------------------------------------------
-- vista vista_recargas_tnpagos
-- ------------------------------------------------------

CREATE OR REPLACE VIEW public.vista_recargas_tnpagos AS 
 SELECT recaudo.idrecaudo AS idvista,
    persona.nombres,
    persona.apellidos,
    persona.tipodocumento AS tipo_documento,
    persona.numdocumento AS numero_documento,
    persona.num_telefono,
    persona.email,
    persona.direccion,
    ciudad.nombre AS ciudad_familiar,
    departamentoestado.nombre AS departamento_familiar,
    pais.nombre AS pais_familiar,
    beneficiario.td AS tipodocumento_ppl,
    beneficiario.pin AS numero_documento_ppl,
    beneficiario.nombres_apellidos AS nombre_ppl,
    recaudo.idrecaudo,
    recaudo.valor,
    recaudo.estado,
    recaudo.log,
    recaudo.created_at,
    recaudo.check_telefonia_at,
    recaudo.establecimiento,
    recaudo.cod_pago,
    recaudo.telefonosms,
    recaudo.observacion,
    recaudo.observacion_adicional,
    recaudo.ciudad AS ciudad_ppl,
    pasarelapago.nombre AS nombre_pasarela,
    transacciones.codtransaccion,
    transacciones.formapago,
    transacciones.franquicia,
    transacciones.descripcion,
    transacciones.referencia1,
    transacciones.fechapago,
    transacciones.numerorecibo,
    transacciones.codigo,
    transacciones.mensajeerror
   FROM recaudo
  
 join benefactor on recaudo.idbenefactor = benefactor.idbenefactor 
  join beneficiario on recaudo.idbeneficiario = beneficiario.idbeneficiario 
 join persona on benefactor.idpersona = persona.idpersona 
 join ciudad on persona.idciudad = ciudad.idciudad 
 join departamentoestado ON ciudad.iddepartamento = departamentoestado.iddepartamento 
 join pais on  departamentoestado.idpais = pais.idpais 
 join pasarelapago on recaudo.idpasarela = pasarelapago.idpasarela 
join  transacciones on recaudo.idrecaudo = transacciones.idrecaudo WHERE  recaudo.idpasarela = 1
