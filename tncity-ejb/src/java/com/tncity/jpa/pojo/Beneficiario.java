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
@Table(name = "beneficiario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Beneficiario.findAll", query = "SELECT b FROM Beneficiario b")
    , @NamedQuery(name = "Beneficiario.findByIdbeneficiario", query = "SELECT b FROM Beneficiario b WHERE b.idbeneficiario = :idbeneficiario")
    , @NamedQuery(name = "Beneficiario.findByTd", query = "SELECT b FROM Beneficiario b WHERE b.td = :td")
    , @NamedQuery(name = "Beneficiario.findByPin", query = "SELECT b FROM Beneficiario b WHERE b.pin = :pin")
    , @NamedQuery(name = "Beneficiario.findByNombresApellidos", query = "SELECT b FROM Beneficiario b WHERE b.nombresApellidos = :nombresApellidos")
    , @NamedQuery(name = "Beneficiario.findByUpdatedAt", query = "SELECT b FROM Beneficiario b WHERE b.updatedAt = :updatedAt")})
public class Beneficiario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbeneficiario")
    private Long idbeneficiario;
    @Size(max = 2147483647)
    @Column(name = "td")
    private String td;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pin")
    private String pin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombres_apellidos")
    private String nombresApellidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbeneficiario")
    private Collection<Recaudo> recaudoCollection;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne
    private Persona idpersona;
    @JoinColumn(name = "idusuario_updated", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuarioUpdated;

    public Beneficiario() {
    }

    public Beneficiario(Long idbeneficiario) {
        this.idbeneficiario = idbeneficiario;
    }

    public Beneficiario(Long idbeneficiario, String pin, String nombresApellidos, Date updatedAt) {
        this.idbeneficiario = idbeneficiario;
        this.pin = pin;
        this.nombresApellidos = nombresApellidos;
        this.updatedAt = updatedAt;
    }

    public Long getIdbeneficiario() {
        return idbeneficiario;
    }

    public void setIdbeneficiario(Long idbeneficiario) {
        this.idbeneficiario = idbeneficiario;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @XmlTransient
    public Collection<Recaudo> getRecaudoCollection() {
        return recaudoCollection;
    }

    public void setRecaudoCollection(Collection<Recaudo> recaudoCollection) {
        this.recaudoCollection = recaudoCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbeneficiario != null ? idbeneficiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beneficiario)) {
            return false;
        }
        Beneficiario other = (Beneficiario) object;
        if ((this.idbeneficiario == null && other.idbeneficiario != null) || (this.idbeneficiario != null && !this.idbeneficiario.equals(other.idbeneficiario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Beneficiario[ idbeneficiario=" + idbeneficiario + " ]";
    }
    
}
