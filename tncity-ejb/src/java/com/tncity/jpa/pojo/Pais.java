/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.pojo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guiovanny
 */
@Entity
@Table(name = "pais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p")
    , @NamedQuery(name = "Pais.findByIdpais", query = "SELECT p FROM Pais p WHERE p.idpais = :idpais")
    , @NamedQuery(name = "Pais.findByNombre", query = "SELECT p FROM Pais p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Pais.findByCodOficialIso", query = "SELECT p FROM Pais p WHERE p.codOficialIso = :codOficialIso")
    , @NamedQuery(name = "Pais.findByIso2", query = "SELECT p FROM Pais p WHERE p.iso2 = :iso2")
    , @NamedQuery(name = "Pais.findByIso3", query = "SELECT p FROM Pais p WHERE p.iso3 = :iso3")
    , @NamedQuery(name = "Pais.findByLatitud", query = "SELECT p FROM Pais p WHERE p.latitud = :latitud")
    , @NamedQuery(name = "Pais.findByLongitud", query = "SELECT p FROM Pais p WHERE p.longitud = :longitud")
    , @NamedQuery(name = "Pais.findByCodpostal", query = "SELECT p FROM Pais p WHERE p.codpostal = :codpostal")})
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpais")
    private Integer idpais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "cod_oficial_iso")
    private String codOficialIso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "iso2")
    private String iso2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "iso3")
    private String iso3;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitud")
    private Double latitud;
    @Column(name = "longitud")
    private Double longitud;
    @Size(max = 2147483647)
    @Column(name = "codpostal")
    private String codpostal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpais")
    private Collection<Departamentoestado> departamentoestadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpais")
    private Collection<Indicativospaises> indicativospaisesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpais")
    private Collection<Moneda> monedaCollection;

    public Pais() {
    }

    public Pais(Integer idpais) {
        this.idpais = idpais;
    }

    public Pais(Integer idpais, String nombre, String iso2, String iso3) {
        this.idpais = idpais;
        this.nombre = nombre;
        this.iso2 = iso2;
        this.iso3 = iso3;
    }

    public Integer getIdpais() {
        return idpais;
    }

    public void setIdpais(Integer idpais) {
        this.idpais = idpais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodOficialIso() {
        return codOficialIso;
    }

    public void setCodOficialIso(String codOficialIso) {
        this.codOficialIso = codOficialIso;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    @XmlTransient
    public Collection<Departamentoestado> getDepartamentoestadoCollection() {
        return departamentoestadoCollection;
    }

    public void setDepartamentoestadoCollection(Collection<Departamentoestado> departamentoestadoCollection) {
        this.departamentoestadoCollection = departamentoestadoCollection;
    }

    @XmlTransient
    public Collection<Indicativospaises> getIndicativospaisesCollection() {
        return indicativospaisesCollection;
    }

    public void setIndicativospaisesCollection(Collection<Indicativospaises> indicativospaisesCollection) {
        this.indicativospaisesCollection = indicativospaisesCollection;
    }

    @XmlTransient
    public Collection<Moneda> getMonedaCollection() {
        return monedaCollection;
    }

    public void setMonedaCollection(Collection<Moneda> monedaCollection) {
        this.monedaCollection = monedaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpais != null ? idpais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.idpais == null && other.idpais != null) || (this.idpais != null && !this.idpais.equals(other.idpais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Pais[ idpais=" + idpais + " ]";
    }
    
}
