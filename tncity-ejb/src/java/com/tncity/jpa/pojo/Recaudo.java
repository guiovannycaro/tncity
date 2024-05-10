/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guiovanny
 */
@Entity
@Table(name = "recaudo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recaudo.findAll", query = "SELECT r FROM Recaudo r")
    , @NamedQuery(name = "Recaudo.findByIdrecaudo", query = "SELECT r FROM Recaudo r WHERE r.idrecaudo = :idrecaudo")
    , @NamedQuery(name = "Recaudo.findByValor", query = "SELECT r FROM Recaudo r WHERE r.valor = :valor")
    , @NamedQuery(name = "Recaudo.findByEstado", query = "SELECT r FROM Recaudo r WHERE r.estado = :estado")
    , @NamedQuery(name = "Recaudo.findByLog", query = "SELECT r FROM Recaudo r WHERE r.log = :log")
    , @NamedQuery(name = "Recaudo.findByCreatedAt", query = "SELECT r FROM Recaudo r WHERE r.createdAt = :createdAt")
    , @NamedQuery(name = "Recaudo.findByCheckTelefoniaAt", query = "SELECT r FROM Recaudo r WHERE r.checkTelefoniaAt = :checkTelefoniaAt")
    , @NamedQuery(name = "Recaudo.findByEstablecimiento", query = "SELECT r FROM Recaudo r WHERE r.establecimiento = :establecimiento")
    , @NamedQuery(name = "Recaudo.findByCodPago", query = "SELECT r FROM Recaudo r WHERE r.codPago = :codPago")
    , @NamedQuery(name = "Recaudo.findByTokenWtnpagos", query = "SELECT r FROM Recaudo r WHERE r.tokenWtnpagos = :tokenWtnpagos")
    , @NamedQuery(name = "Recaudo.findByTelefonosms", query = "SELECT r FROM Recaudo r WHERE r.telefonosms = :telefonosms")
    , @NamedQuery(name = "Recaudo.findByCiudad", query = "SELECT r FROM Recaudo r WHERE r.ciudad = :ciudad")
    , @NamedQuery(name = "Recaudo.findByObservacion", query = "SELECT r FROM Recaudo r WHERE r.observacion = :observacion")
    , @NamedQuery(name = "Recaudo.findByObservacionAdicional", query = "SELECT r FROM Recaudo r WHERE r.observacionAdicional = :observacionAdicional")
    , @NamedQuery(name = "Recaudo.findByPasareladia", query = "SELECT r FROM Recaudo r WHERE r.pasareladia = :pasareladia")
    , @NamedQuery(name = "Recaudo.findByValorcomision", query = "SELECT r FROM Recaudo r WHERE r.valorcomision = :valorcomision")
    , @NamedQuery(name = "Recaudo.findByNumtransaccion", query = "SELECT r FROM Recaudo r WHERE r.numtransaccion = :numtransaccion")
    , @NamedQuery(name = "Recaudo.findByValorpromocion", query = "SELECT r FROM Recaudo r WHERE r.valorpromocion = :valorpromocion")
    , @NamedQuery(name = "Recaudo.findByPromo", query = "SELECT r FROM Recaudo r WHERE r.promo = :promo")})
public class Recaudo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrecaudo")
    private Long idrecaudo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @Size(max = 2147483647)
    @Column(name = "log")
    private String log;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "check_telefonia_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkTelefoniaAt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "establecimiento")
    private String establecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "cod_pago")
    private String codPago;
    @Size(max = 2147483647)
    @Column(name = "token_wtnpagos")
    private String tokenWtnpagos;
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idrecaudo")
    private Collection<Transacciones> transaccionesCollection;
    @JoinColumn(name = "idbenefactor", referencedColumnName = "idbenefactor")
    @ManyToOne(optional = false)
    private Benefactor idbenefactor;
    @JoinColumn(name = "idbeneficiario", referencedColumnName = "idbeneficiario")
    @ManyToOne(optional = false)
    private Beneficiario idbeneficiario;
    @JoinColumn(name = "idpasarela", referencedColumnName = "idpasarela")
    @ManyToOne(optional = false)
    private Pasarelapago idpasarela;
    @OneToMany(mappedBy = "idrecaudo")
    private Collection<Cuentamovimiento> cuentamovimientoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idrecaudo")
    private Collection<Recaudopromo> recaudopromoCollection;

    public Recaudo() {
    }

    public Recaudo(Long idrecaudo) {
        this.idrecaudo = idrecaudo;
    }

    public Recaudo(Long idrecaudo, double valor, String estado, Date createdAt, String establecimiento, String codPago) {
        this.idrecaudo = idrecaudo;
        this.valor = valor;
        this.estado = estado;
        this.createdAt = createdAt;
        this.establecimiento = establecimiento;
        this.codPago = codPago;
    }

    public Long getIdrecaudo() {
        return idrecaudo;
    }

    public void setIdrecaudo(Long idrecaudo) {
        this.idrecaudo = idrecaudo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
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

    @XmlTransient
    public Collection<Transacciones> getTransaccionesCollection() {
        return transaccionesCollection;
    }

    public void setTransaccionesCollection(Collection<Transacciones> transaccionesCollection) {
        this.transaccionesCollection = transaccionesCollection;
    }

    public Benefactor getIdbenefactor() {
        return idbenefactor;
    }

    public void setIdbenefactor(Benefactor idbenefactor) {
        this.idbenefactor = idbenefactor;
    }

    public Beneficiario getIdbeneficiario() {
        return idbeneficiario;
    }

    public void setIdbeneficiario(Beneficiario idbeneficiario) {
        this.idbeneficiario = idbeneficiario;
    }

    public Pasarelapago getIdpasarela() {
        return idpasarela;
    }

    public void setIdpasarela(Pasarelapago idpasarela) {
        this.idpasarela = idpasarela;
    }

    @XmlTransient
    public Collection<Cuentamovimiento> getCuentamovimientoCollection() {
        return cuentamovimientoCollection;
    }

    public void setCuentamovimientoCollection(Collection<Cuentamovimiento> cuentamovimientoCollection) {
        this.cuentamovimientoCollection = cuentamovimientoCollection;
    }

    @XmlTransient
    public Collection<Recaudopromo> getRecaudopromoCollection() {
        return recaudopromoCollection;
    }

    public void setRecaudopromoCollection(Collection<Recaudopromo> recaudopromoCollection) {
        this.recaudopromoCollection = recaudopromoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrecaudo != null ? idrecaudo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recaudo)) {
            return false;
        }
        Recaudo other = (Recaudo) object;
        if ((this.idrecaudo == null && other.idrecaudo != null) || (this.idrecaudo != null && !this.idrecaudo.equals(other.idrecaudo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Recaudo[ idrecaudo=" + idrecaudo + " ]";
    }
    
}
