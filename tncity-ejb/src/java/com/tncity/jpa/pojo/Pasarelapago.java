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
@Table(name = "pasarelapago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pasarelapago.findAll", query = "SELECT p FROM Pasarelapago p")
    , @NamedQuery(name = "Pasarelapago.findByIdpasarela", query = "SELECT p FROM Pasarelapago p WHERE p.idpasarela = :idpasarela")
    , @NamedQuery(name = "Pasarelapago.findByNombre", query = "SELECT p FROM Pasarelapago p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Pasarelapago.findByConfigXml", query = "SELECT p FROM Pasarelapago p WHERE p.configXml = :configXml")
    , @NamedQuery(name = "Pasarelapago.findByUpdatedAt", query = "SELECT p FROM Pasarelapago p WHERE p.updatedAt = :updatedAt")
    , @NamedQuery(name = "Pasarelapago.findByComision", query = "SELECT p FROM Pasarelapago p WHERE p.comision = :comision")})
public class Pasarelapago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpasarela")
    private Integer idpasarela;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "config_xml")
    private String configXml;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "comision")
    private Double comision;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpasarela")
    private Collection<Recaudo> recaudoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpasarela")
    private Collection<Cuenta> cuentaCollection;
    @JoinColumn(name = "idusuario_updated", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuarioUpdated;

    public Pasarelapago() {
    }

    public Pasarelapago(Integer idpasarela) {
        this.idpasarela = idpasarela;
    }

    public Pasarelapago(Integer idpasarela, String nombre, String configXml, Date updatedAt) {
        this.idpasarela = idpasarela;
        this.nombre = nombre;
        this.configXml = configXml;
        this.updatedAt = updatedAt;
    }

    public Integer getIdpasarela() {
        return idpasarela;
    }

    public void setIdpasarela(Integer idpasarela) {
        this.idpasarela = idpasarela;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getConfigXml() {
        return configXml;
    }

    public void setConfigXml(String configXml) {
        this.configXml = configXml;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    @XmlTransient
    public Collection<Recaudo> getRecaudoCollection() {
        return recaudoCollection;
    }

    public void setRecaudoCollection(Collection<Recaudo> recaudoCollection) {
        this.recaudoCollection = recaudoCollection;
    }

    @XmlTransient
    public Collection<Cuenta> getCuentaCollection() {
        return cuentaCollection;
    }

    public void setCuentaCollection(Collection<Cuenta> cuentaCollection) {
        this.cuentaCollection = cuentaCollection;
    }

    public Usuario getIdusuarioUpdated() {
        return idusuarioUpdated;
    }

    public void setIdusuarioUpdated(Usuario idusuarioUpdated) {
        this.idusuarioUpdated = idusuarioUpdated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpasarela != null ? idpasarela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pasarelapago)) {
            return false;
        }
        Pasarelapago other = (Pasarelapago) object;
        if ((this.idpasarela == null && other.idpasarela != null) || (this.idpasarela != null && !this.idpasarela.equals(other.idpasarela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Pasarelapago[ idpasarela=" + idpasarela + " ]";
    }
    
}
