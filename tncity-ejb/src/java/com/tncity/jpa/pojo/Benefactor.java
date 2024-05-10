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
import javax.persistence.OneToOne;
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
@Table(name = "benefactor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Benefactor.findAll", query = "SELECT b FROM Benefactor b")
    , @NamedQuery(name = "Benefactor.findByIdbenefactor", query = "SELECT b FROM Benefactor b WHERE b.idbenefactor = :idbenefactor")
    , @NamedQuery(name = "Benefactor.findByUpdatedAt", query = "SELECT b FROM Benefactor b WHERE b.updatedAt = :updatedAt")
    , @NamedQuery(name = "Benefactor.findByUsername", query = "SELECT b FROM Benefactor b WHERE b.username = :username")
    , @NamedQuery(name = "Benefactor.findByConstrasena", query = "SELECT b FROM Benefactor b WHERE b.constrasena = :constrasena")
    , @NamedQuery(name = "Benefactor.findByIsActivo", query = "SELECT b FROM Benefactor b WHERE b.isActivo = :isActivo")
    , @NamedQuery(name = "Benefactor.findByIsCambiarcontrasena", query = "SELECT b FROM Benefactor b WHERE b.isCambiarcontrasena = :isCambiarcontrasena")
    , @NamedQuery(name = "Benefactor.findByCuenta", query = "SELECT b FROM Benefactor b WHERE b.cuenta = :cuenta")
    , @NamedQuery(name = "Benefactor.findByPerfil", query = "SELECT b FROM Benefactor b WHERE b.perfil = :perfil")
    , @NamedQuery(name = "Benefactor.findByPrimerarecarga", query = "SELECT b FROM Benefactor b WHERE b.primerarecarga = :primerarecarga")})
public class Benefactor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbenefactor")
    private Long idbenefactor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "constrasena")
    private String constrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_activo")
    private boolean isActivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_cambiarcontrasena")
    private boolean isCambiarcontrasena;
    @Column(name = "cuenta")
    private Boolean cuenta;
    @Size(max = 2147483647)
    @Column(name = "perfil")
    private String perfil;
    @Column(name = "primerarecarga")
    private Boolean primerarecarga;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private Persona idpersona;
    @JoinColumn(name = "idusuario_updated", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuarioUpdated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbenefactor")
    private Collection<Recaudo> recaudoCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idbenefactor")
    private Cuenta cuenta1;

    public Benefactor() {
    }

    public Benefactor(Long idbenefactor) {
        this.idbenefactor = idbenefactor;
    }

    public Benefactor(Long idbenefactor, Date updatedAt, String username, String constrasena, boolean isActivo, boolean isCambiarcontrasena) {
        this.idbenefactor = idbenefactor;
        this.updatedAt = updatedAt;
        this.username = username;
        this.constrasena = constrasena;
        this.isActivo = isActivo;
        this.isCambiarcontrasena = isCambiarcontrasena;
    }

    public Long getIdbenefactor() {
        return idbenefactor;
    }

    public void setIdbenefactor(Long idbenefactor) {
        this.idbenefactor = idbenefactor;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConstrasena() {
        return constrasena;
    }

    public void setConstrasena(String constrasena) {
        this.constrasena = constrasena;
    }

    public boolean getIsActivo() {
        return isActivo;
    }

    public void setIsActivo(boolean isActivo) {
        this.isActivo = isActivo;
    }

    public boolean getIsCambiarcontrasena() {
        return isCambiarcontrasena;
    }

    public void setIsCambiarcontrasena(boolean isCambiarcontrasena) {
        this.isCambiarcontrasena = isCambiarcontrasena;
    }

    public Boolean getCuenta() {
        return cuenta;
    }

    public void setCuenta(Boolean cuenta) {
        this.cuenta = cuenta;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Boolean getPrimerarecarga() {
        return primerarecarga;
    }

    public void setPrimerarecarga(Boolean primerarecarga) {
        this.primerarecarga = primerarecarga;
    }

    public Persona getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Persona idpersona) {
        this.idpersona = idpersona;
    }

    public Usuario getIdusuarioUpdated() {
        return idusuarioUpdated;
    }

    public void setIdusuarioUpdated(Usuario idusuarioUpdated) {
        this.idusuarioUpdated = idusuarioUpdated;
    }

    @XmlTransient
    public Collection<Recaudo> getRecaudoCollection() {
        return recaudoCollection;
    }

    public void setRecaudoCollection(Collection<Recaudo> recaudoCollection) {
        this.recaudoCollection = recaudoCollection;
    }

    public Cuenta getCuenta1() {
        return cuenta1;
    }

    public void setCuenta1(Cuenta cuenta1) {
        this.cuenta1 = cuenta1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbenefactor != null ? idbenefactor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Benefactor)) {
            return false;
        }
        Benefactor other = (Benefactor) object;
        if ((this.idbenefactor == null && other.idbenefactor != null) || (this.idbenefactor != null && !this.idbenefactor.equals(other.idbenefactor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Benefactor[ idbenefactor=" + idbenefactor + " ]";
    }
    
}
