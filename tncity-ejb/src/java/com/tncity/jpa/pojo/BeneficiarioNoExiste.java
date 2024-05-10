/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guiovanny
 */
@Entity
@Table(name = "beneficiarioNoExiste")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BeneficiarioNoExiste.findAll", query = "SELECT b FROM BeneficiarioNoExiste b")
    , @NamedQuery(name = "BeneficiarioNoExiste.findByIdbeneficiarione", query = "SELECT b FROM BeneficiarioNoExiste b WHERE b.idbeneficiarione = :idbeneficiarione")
    , @NamedQuery(name = "BeneficiarioNoExiste.findByTd", query = "SELECT b FROM BeneficiarioNoExiste b WHERE b.td = :td")
    , @NamedQuery(name = "BeneficiarioNoExiste.findByPin", query = "SELECT b FROM BeneficiarioNoExiste b WHERE b.pin = :pin")
    , @NamedQuery(name = "BeneficiarioNoExiste.findByNombresApellidos", query = "SELECT b FROM BeneficiarioNoExiste b WHERE b.nombresApellidos = :nombresApellidos")
    , @NamedQuery(name = "BeneficiarioNoExiste.findByUpdateAt", query = "SELECT b FROM BeneficiarioNoExiste b WHERE b.updateAt = :updateAt")})
public class BeneficiarioNoExiste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbeneficiarione")
    private Long idbeneficiarione;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
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
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @JoinColumn(name = "tariffgroupname", referencedColumnName = "idciudad")
    @ManyToOne(optional = false)
    private Ciudad tariffgroupname;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private Persona idpersona;
    @JoinColumn(name = "idusuario_updated", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuarioUpdated;

    public BeneficiarioNoExiste() {
    }

    public BeneficiarioNoExiste(Long idbeneficiarione) {
        this.idbeneficiarione = idbeneficiarione;
    }

    public BeneficiarioNoExiste(Long idbeneficiarione, String td, String pin, String nombresApellidos, Date updateAt) {
        this.idbeneficiarione = idbeneficiarione;
        this.td = td;
        this.pin = pin;
        this.nombresApellidos = nombresApellidos;
        this.updateAt = updateAt;
    }

    public Long getIdbeneficiarione() {
        return idbeneficiarione;
    }

    public void setIdbeneficiarione(Long idbeneficiarione) {
        this.idbeneficiarione = idbeneficiarione;
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

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Ciudad getTariffgroupname() {
        return tariffgroupname;
    }

    public void setTariffgroupname(Ciudad tariffgroupname) {
        this.tariffgroupname = tariffgroupname;
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
        hash += (idbeneficiarione != null ? idbeneficiarione.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeneficiarioNoExiste)) {
            return false;
        }
        BeneficiarioNoExiste other = (BeneficiarioNoExiste) object;
        if ((this.idbeneficiarione == null && other.idbeneficiarione != null) || (this.idbeneficiarione != null && !this.idbeneficiarione.equals(other.idbeneficiarione))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.BeneficiarioNoExiste[ idbeneficiarione=" + idbeneficiarione + " ]";
    }
    
}
