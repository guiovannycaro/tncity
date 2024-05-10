/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.pojo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guiovanny
 */
@Entity
@Table(name = "vista_recaudo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaRecaudo.findAll", query = "SELECT v FROM VistaRecaudo v")
    , @NamedQuery(name = "VistaRecaudo.findByIdrecaudo", query = "SELECT v FROM VistaRecaudo v WHERE v.idrecaudo = :idrecaudo")
    , @NamedQuery(name = "VistaRecaudo.findByIdbenefactor", query = "SELECT v FROM VistaRecaudo v WHERE v.idbenefactor = :idbenefactor")
    , @NamedQuery(name = "VistaRecaudo.findByIdbeneficiario", query = "SELECT v FROM VistaRecaudo v WHERE v.idbeneficiario = :idbeneficiario")
    , @NamedQuery(name = "VistaRecaudo.findByIdpersona", query = "SELECT v FROM VistaRecaudo v WHERE v.idpersona = :idpersona")
    , @NamedQuery(name = "VistaRecaudo.findByNombreFamiliar", query = "SELECT v FROM VistaRecaudo v WHERE v.nombreFamiliar = :nombreFamiliar")
    , @NamedQuery(name = "VistaRecaudo.findByApellidoFamiliar", query = "SELECT v FROM VistaRecaudo v WHERE v.apellidoFamiliar = :apellidoFamiliar")
    , @NamedQuery(name = "VistaRecaudo.findByTipodocumentoFamiliar", query = "SELECT v FROM VistaRecaudo v WHERE v.tipodocumentoFamiliar = :tipodocumentoFamiliar")
    , @NamedQuery(name = "VistaRecaudo.findByNumdocumentoFamiliar", query = "SELECT v FROM VistaRecaudo v WHERE v.numdocumentoFamiliar = :numdocumentoFamiliar")
    , @NamedQuery(name = "VistaRecaudo.findByTelefonoFamiliar", query = "SELECT v FROM VistaRecaudo v WHERE v.telefonoFamiliar = :telefonoFamiliar")
    , @NamedQuery(name = "VistaRecaudo.findByCorreoFamilar", query = "SELECT v FROM VistaRecaudo v WHERE v.correoFamilar = :correoFamilar")
    , @NamedQuery(name = "VistaRecaudo.findByTd", query = "SELECT v FROM VistaRecaudo v WHERE v.td = :td")
    , @NamedQuery(name = "VistaRecaudo.findByPin", query = "SELECT v FROM VistaRecaudo v WHERE v.pin = :pin")
    , @NamedQuery(name = "VistaRecaudo.findByNombresApellidos", query = "SELECT v FROM VistaRecaudo v WHERE v.nombresApellidos = :nombresApellidos")
    , @NamedQuery(name = "VistaRecaudo.findByValor", query = "SELECT v FROM VistaRecaudo v WHERE v.valor = :valor")
    , @NamedQuery(name = "VistaRecaudo.findByEstado", query = "SELECT v FROM VistaRecaudo v WHERE v.estado = :estado")
    , @NamedQuery(name = "VistaRecaudo.findByLog", query = "SELECT v FROM VistaRecaudo v WHERE v.log = :log")
    , @NamedQuery(name = "VistaRecaudo.findByCreatedAt", query = "SELECT v FROM VistaRecaudo v WHERE v.createdAt = :createdAt")
    , @NamedQuery(name = "VistaRecaudo.findByCheckTelefoniaAt", query = "SELECT v FROM VistaRecaudo v WHERE v.checkTelefoniaAt = :checkTelefoniaAt")
    , @NamedQuery(name = "VistaRecaudo.findByEstablecimiento", query = "SELECT v FROM VistaRecaudo v WHERE v.establecimiento = :establecimiento")
    , @NamedQuery(name = "VistaRecaudo.findByCodPago", query = "SELECT v FROM VistaRecaudo v WHERE v.codPago = :codPago")
    , @NamedQuery(name = "VistaRecaudo.findByTokenWtnpagos", query = "SELECT v FROM VistaRecaudo v WHERE v.tokenWtnpagos = :tokenWtnpagos")
    , @NamedQuery(name = "VistaRecaudo.findByIdpasarela", query = "SELECT v FROM VistaRecaudo v WHERE v.idpasarela = :idpasarela")
    , @NamedQuery(name = "VistaRecaudo.findByTelefonosms", query = "SELECT v FROM VistaRecaudo v WHERE v.telefonosms = :telefonosms")
    , @NamedQuery(name = "VistaRecaudo.findByCiudad", query = "SELECT v FROM VistaRecaudo v WHERE v.ciudad = :ciudad")
    , @NamedQuery(name = "VistaRecaudo.findByObservacion", query = "SELECT v FROM VistaRecaudo v WHERE v.observacion = :observacion")
    , @NamedQuery(name = "VistaRecaudo.findByObservacionAdicional", query = "SELECT v FROM VistaRecaudo v WHERE v.observacionAdicional = :observacionAdicional")
    , @NamedQuery(name = "VistaRecaudo.findByPasareladia", query = "SELECT v FROM VistaRecaudo v WHERE v.pasareladia = :pasareladia")
    , @NamedQuery(name = "VistaRecaudo.findByValorcomision", query = "SELECT v FROM VistaRecaudo v WHERE v.valorcomision = :valorcomision")
    , @NamedQuery(name = "VistaRecaudo.findByNumtransaccion", query = "SELECT v FROM VistaRecaudo v WHERE v.numtransaccion = :numtransaccion")
    , @NamedQuery(name = "VistaRecaudo.findByValorpromocion", query = "SELECT v FROM VistaRecaudo v WHERE v.valorpromocion = :valorpromocion")
    , @NamedQuery(name = "VistaRecaudo.findByPromo", query = "SELECT v FROM VistaRecaudo v WHERE v.promo = :promo")})
public class VistaRecaudo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "idrecaudo")
    @Id
    private BigInteger idrecaudo;
    @Column(name = "idbenefactor")
    private BigInteger idbenefactor;
    @Column(name = "idbeneficiario")
    private BigInteger idbeneficiario;
    @Column(name = "idpersona")
    private BigInteger idpersona;
    @Size(max = 2147483647)
    @Column(name = "nombre_familiar")
    private String nombreFamiliar;
    @Size(max = 2147483647)
    @Column(name = "apellido_familiar")
    private String apellidoFamiliar;
    @Size(max = 2147483647)
    @Column(name = "tipodocumento_familiar")
    private String tipodocumentoFamiliar;
    @Column(name = "numdocumento_familiar")
    private BigInteger numdocumentoFamiliar;
    @Column(name = "telefono_familiar")
    private Long telefonoFamiliar;
    @Size(max = 2147483647)
    @Column(name = "correo_familar")
    private String correoFamilar;
    @Size(max = 2147483647)
    @Column(name = "td")
    private String td;
    @Size(max = 2147483647)
    @Column(name = "pin")
    private String pin;
    @Size(max = 2147483647)
    @Column(name = "nombres_apellidos")
    private String nombresApellidos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @Size(max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @Size(max = 2147483647)
    @Column(name = "log")
    private String log;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "check_telefonia_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkTelefoniaAt;
    @Size(max = 2147483647)
    @Column(name = "establecimiento")
    private String establecimiento;
    @Size(max = 2147483647)
    @Column(name = "cod_pago")
    private String codPago;
    @Size(max = 2147483647)
    @Column(name = "token_wtnpagos")
    private String tokenWtnpagos;
    @Column(name = "idpasarela")
    private Integer idpasarela;
    @Size(max = 2147483647)
    @Column(name = "telefonosms")
    private String telefonosms;
    @Size(max = 2147483647)
    @Column(name = "ciudad")
    private String ciudad;
    @Size(max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 2147483647)
    @Column(name = "observacion_adicional")
    private String observacionAdicional;
    @Size(max = 50)
    @Column(name = "pasareladia")
    private String pasareladia;
    @Column(name = "valorcomision")
    private Double valorcomision;
    @Size(max = 2147483647)
    @Column(name = "numtransaccion")
    private String numtransaccion;
    @Column(name = "valorpromocion")
    private Integer valorpromocion;
    @Size(max = 2147483647)
    @Column(name = "promo")
    private String promo;

    public VistaRecaudo() {
    }

    public BigInteger getIdrecaudo() {
        return idrecaudo;
    }

    public void setIdrecaudo(BigInteger idrecaudo) {
        this.idrecaudo = idrecaudo;
    }

    public BigInteger getIdbenefactor() {
        return idbenefactor;
    }

    public void setIdbenefactor(BigInteger idbenefactor) {
        this.idbenefactor = idbenefactor;
    }

    public BigInteger getIdbeneficiario() {
        return idbeneficiario;
    }

    public void setIdbeneficiario(BigInteger idbeneficiario) {
        this.idbeneficiario = idbeneficiario;
    }

    public BigInteger getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(BigInteger idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombreFamiliar() {
        return nombreFamiliar;
    }

    public void setNombreFamiliar(String nombreFamiliar) {
        this.nombreFamiliar = nombreFamiliar;
    }

    public String getApellidoFamiliar() {
        return apellidoFamiliar;
    }

    public void setApellidoFamiliar(String apellidoFamiliar) {
        this.apellidoFamiliar = apellidoFamiliar;
    }

    public String getTipodocumentoFamiliar() {
        return tipodocumentoFamiliar;
    }

    public void setTipodocumentoFamiliar(String tipodocumentoFamiliar) {
        this.tipodocumentoFamiliar = tipodocumentoFamiliar;
    }

    public BigInteger getNumdocumentoFamiliar() {
        return numdocumentoFamiliar;
    }

    public void setNumdocumentoFamiliar(BigInteger numdocumentoFamiliar) {
        this.numdocumentoFamiliar = numdocumentoFamiliar;
    }

    public Long getTelefonoFamiliar() {
        return telefonoFamiliar;
    }

    public void setTelefonoFamiliar(Long telefonoFamiliar) {
        this.telefonoFamiliar = telefonoFamiliar;
    }

    public String getCorreoFamilar() {
        return correoFamilar;
    }

    public void setCorreoFamilar(String correoFamilar) {
        this.correoFamilar = correoFamilar;
    }

    public String getTd() {
        return td;
    }

    public void setTd(String td) {
        this.td = td;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCheckTelefoniaAt() {
        return checkTelefoniaAt;
    }

    public void setCheckTelefoniaAt(Date checkTelefoniaAt) {
        this.checkTelefoniaAt = checkTelefoniaAt;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getCodPago() {
        return codPago;
    }

    public void setCodPago(String codPago) {
        this.codPago = codPago;
    }

    public String getTokenWtnpagos() {
        return tokenWtnpagos;
    }

    public void setTokenWtnpagos(String tokenWtnpagos) {
        this.tokenWtnpagos = tokenWtnpagos;
    }

    public Integer getIdpasarela() {
        return idpasarela;
    }

    public void setIdpasarela(Integer idpasarela) {
        this.idpasarela = idpasarela;
    }

    public String getTelefonosms() {
        return telefonosms;
    }

    public void setTelefonosms(String telefonosms) {
        this.telefonosms = telefonosms;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacionAdicional() {
        return observacionAdicional;
    }

    public void setObservacionAdicional(String observacionAdicional) {
        this.observacionAdicional = observacionAdicional;
    }

    public String getPasareladia() {
        return pasareladia;
    }

    public void setPasareladia(String pasareladia) {
        this.pasareladia = pasareladia;
    }

    public Double getValorcomision() {
        return valorcomision;
    }

    public void setValorcomision(Double valorcomision) {
        this.valorcomision = valorcomision;
    }

    public String getNumtransaccion() {
        return numtransaccion;
    }

    public void setNumtransaccion(String numtransaccion) {
        this.numtransaccion = numtransaccion;
    }

    public Integer getValorpromocion() {
        return valorpromocion;
    }

    public void setValorpromocion(Integer valorpromocion) {
        this.valorpromocion = valorpromocion;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }
    
}
