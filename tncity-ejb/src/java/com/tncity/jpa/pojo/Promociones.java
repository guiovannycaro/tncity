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
@Table(name = "promociones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promociones.findAll", query = "SELECT p FROM Promociones p")
    , @NamedQuery(name = "Promociones.findByIdpromociones", query = "SELECT p FROM Promociones p WHERE p.idpromociones = :idpromociones")
    , @NamedQuery(name = "Promociones.findByTipoValor", query = "SELECT p FROM Promociones p WHERE p.tipoValor = :tipoValor")
    , @NamedQuery(name = "Promociones.findByValorPromocion", query = "SELECT p FROM Promociones p WHERE p.valorPromocion = :valorPromocion")
    , @NamedQuery(name = "Promociones.findByFechaInicial", query = "SELECT p FROM Promociones p WHERE p.fechaInicial = :fechaInicial")
    , @NamedQuery(name = "Promociones.findByFechaFinal", query = "SELECT p FROM Promociones p WHERE p.fechaFinal = :fechaFinal")
    , @NamedQuery(name = "Promociones.findByEstado", query = "SELECT p FROM Promociones p WHERE p.estado = :estado")
    , @NamedQuery(name = "Promociones.findByCero", query = "SELECT p FROM Promociones p WHERE p.cero = :cero")
    , @NamedQuery(name = "Promociones.findByUno", query = "SELECT p FROM Promociones p WHERE p.uno = :uno")
    , @NamedQuery(name = "Promociones.findByDos", query = "SELECT p FROM Promociones p WHERE p.dos = :dos")
    , @NamedQuery(name = "Promociones.findByTres", query = "SELECT p FROM Promociones p WHERE p.tres = :tres")
    , @NamedQuery(name = "Promociones.findByCuatro", query = "SELECT p FROM Promociones p WHERE p.cuatro = :cuatro")
    , @NamedQuery(name = "Promociones.findByCinco", query = "SELECT p FROM Promociones p WHERE p.cinco = :cinco")
    , @NamedQuery(name = "Promociones.findBySeis", query = "SELECT p FROM Promociones p WHERE p.seis = :seis")
    , @NamedQuery(name = "Promociones.findBySiete", query = "SELECT p FROM Promociones p WHERE p.siete = :siete")
    , @NamedQuery(name = "Promociones.findByOcho", query = "SELECT p FROM Promociones p WHERE p.ocho = :ocho")
    , @NamedQuery(name = "Promociones.findByNueve", query = "SELECT p FROM Promociones p WHERE p.nueve = :nueve")
    , @NamedQuery(name = "Promociones.findByTipoAplicadesde", query = "SELECT p FROM Promociones p WHERE p.tipoAplicadesde = :tipoAplicadesde")
    , @NamedQuery(name = "Promociones.findByAplicadesde", query = "SELECT p FROM Promociones p WHERE p.aplicadesde = :aplicadesde")
    , @NamedQuery(name = "Promociones.findByValoraplica", query = "SELECT p FROM Promociones p WHERE p.valoraplica = :valoraplica")
    , @NamedQuery(name = "Promociones.findByObservacion", query = "SELECT p FROM Promociones p WHERE p.observacion = :observacion")})
public class Promociones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpromociones")
    private Integer idpromociones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tipo_valor")
    private String tipoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "valor_promocion")
    private String valorPromocion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cero")
    private boolean cero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uno")
    private boolean uno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dos")
    private boolean dos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tres")
    private boolean tres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuatro")
    private boolean cuatro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cinco")
    private boolean cinco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seis")
    private boolean seis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "siete")
    private boolean siete;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ocho")
    private boolean ocho;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nueve")
    private boolean nueve;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tipo_aplicadesde")
    private String tipoAplicadesde;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "aplicadesde")
    private String aplicadesde;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "valoraplica")
    private String valoraplica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "idpromocion", referencedColumnName = "idpromocion")
    @ManyToOne(optional = false)
    private Promocion idpromocion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpromociones")
    private Collection<Recaudopromo> recaudopromoCollection;

    public Promociones() {
    }

    public Promociones(Integer idpromociones) {
        this.idpromociones = idpromociones;
    }

    public Promociones(Integer idpromociones, String tipoValor, String valorPromocion, Date fechaInicial, Date fechaFinal, boolean estado, boolean cero, boolean uno, boolean dos, boolean tres, boolean cuatro, boolean cinco, boolean seis, boolean siete, boolean ocho, boolean nueve, String tipoAplicadesde, String aplicadesde, String valoraplica, String observacion) {
        this.idpromociones = idpromociones;
        this.tipoValor = tipoValor;
        this.valorPromocion = valorPromocion;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
        this.cero = cero;
        this.uno = uno;
        this.dos = dos;
        this.tres = tres;
        this.cuatro = cuatro;
        this.cinco = cinco;
        this.seis = seis;
        this.siete = siete;
        this.ocho = ocho;
        this.nueve = nueve;
        this.tipoAplicadesde = tipoAplicadesde;
        this.aplicadesde = aplicadesde;
        this.valoraplica = valoraplica;
        this.observacion = observacion;
    }

    public Integer getIdpromociones() {
        return idpromociones;
    }

    public void setIdpromociones(Integer idpromociones) {
        this.idpromociones = idpromociones;
    }

    public String getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
    }

    public String getValorPromocion() {
        return valorPromocion;
    }

    public void setValorPromocion(String valorPromocion) {
        this.valorPromocion = valorPromocion;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean getCero() {
        return cero;
    }

    public void setCero(boolean cero) {
        this.cero = cero;
    }

    public boolean getUno() {
        return uno;
    }

    public void setUno(boolean uno) {
        this.uno = uno;
    }

    public boolean getDos() {
        return dos;
    }

    public void setDos(boolean dos) {
        this.dos = dos;
    }

    public boolean getTres() {
        return tres;
    }

    public void setTres(boolean tres) {
        this.tres = tres;
    }

    public boolean getCuatro() {
        return cuatro;
    }

    public void setCuatro(boolean cuatro) {
        this.cuatro = cuatro;
    }

    public boolean getCinco() {
        return cinco;
    }

    public void setCinco(boolean cinco) {
        this.cinco = cinco;
    }

    public boolean getSeis() {
        return seis;
    }

    public void setSeis(boolean seis) {
        this.seis = seis;
    }

    public boolean getSiete() {
        return siete;
    }

    public void setSiete(boolean siete) {
        this.siete = siete;
    }

    public boolean getOcho() {
        return ocho;
    }

    public void setOcho(boolean ocho) {
        this.ocho = ocho;
    }

    public boolean getNueve() {
        return nueve;
    }

    public void setNueve(boolean nueve) {
        this.nueve = nueve;
    }

    public String getTipoAplicadesde() {
        return tipoAplicadesde;
    }

    public void setTipoAplicadesde(String tipoAplicadesde) {
        this.tipoAplicadesde = tipoAplicadesde;
    }

    public String getAplicadesde() {
        return aplicadesde;
    }

    public void setAplicadesde(String aplicadesde) {
        this.aplicadesde = aplicadesde;
    }

    public String getValoraplica() {
        return valoraplica;
    }

    public void setValoraplica(String valoraplica) {
        this.valoraplica = valoraplica;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Promocion getIdpromocion() {
        return idpromocion;
    }

    public void setIdpromocion(Promocion idpromocion) {
        this.idpromocion = idpromocion;
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
        hash += (idpromociones != null ? idpromociones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promociones)) {
            return false;
        }
        Promociones other = (Promociones) object;
        if ((this.idpromociones == null && other.idpromociones != null) || (this.idpromociones != null && !this.idpromociones.equals(other.idpromociones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Promociones[ idpromociones=" + idpromociones + " ]";
    }
    
}
