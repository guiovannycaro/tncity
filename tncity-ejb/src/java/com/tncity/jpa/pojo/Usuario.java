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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario")
    , @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findByUpdatedAt", query = "SELECT u FROM Usuario u WHERE u.updatedAt = :updatedAt")
    , @NamedQuery(name = "Usuario.findByIsActivo", query = "SELECT u FROM Usuario u WHERE u.isActivo = :isActivo")
    , @NamedQuery(name = "Usuario.findByIsCambiarcontrasena", query = "SELECT u FROM Usuario u WHERE u.isCambiarcontrasena = :isCambiarcontrasena")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "contrasena")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_activo")
    private boolean isActivo;
    @Column(name = "is_cambiarcontrasena")
    private Boolean isCambiarcontrasena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuarioUpdated")
    private Collection<Configuracion> configuracionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuarioUpdated")
    private Collection<Benefactor> benefactorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private Collection<PerfilHasUsuario> perfilHasUsuarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuarioCreated")
    private Collection<Cuentamovimiento> cuentamovimientoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuarioUpdated")
    private Collection<Cuentamovimiento> cuentamovimientoCollection1;
    @OneToMany(mappedBy = "idusuarioCreated")
    private Collection<Persona> personaCollection;
    @OneToMany(mappedBy = "idusuarioUpdated")
    private Collection<Persona> personaCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuarioUpdated")
    private Collection<BeneficiarioNoExiste> beneficiarioNoExisteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private Collection<Reportes> reportesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private Collection<Permisos> permisosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuarioUpdated")
    private Collection<Beneficiario> beneficiarioCollection;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @OneToOne(optional = false)
    private Persona idpersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private Collection<Repoauditoria> repoauditoriaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuarioUpdated")
    private Collection<Pasarelapago> pasarelapagoCollection;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, String username, String contrasena, Date updatedAt, boolean isActivo) {
        this.idusuario = idusuario;
        this.username = username;
        this.contrasena = contrasena;
        this.updatedAt = updatedAt;
        this.isActivo = isActivo;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getIsActivo() {
        return isActivo;
    }

    public void setIsActivo(boolean isActivo) {
        this.isActivo = isActivo;
    }

    public Boolean getIsCambiarcontrasena() {
        return isCambiarcontrasena;
    }

    public void setIsCambiarcontrasena(Boolean isCambiarcontrasena) {
        this.isCambiarcontrasena = isCambiarcontrasena;
    }

    @XmlTransient
    public Collection<Configuracion> getConfiguracionCollection() {
        return configuracionCollection;
    }

    public void setConfiguracionCollection(Collection<Configuracion> configuracionCollection) {
        this.configuracionCollection = configuracionCollection;
    }

    @XmlTransient
    public Collection<Benefactor> getBenefactorCollection() {
        return benefactorCollection;
    }

    public void setBenefactorCollection(Collection<Benefactor> benefactorCollection) {
        this.benefactorCollection = benefactorCollection;
    }

    @XmlTransient
    public Collection<PerfilHasUsuario> getPerfilHasUsuarioCollection() {
        return perfilHasUsuarioCollection;
    }

    public void setPerfilHasUsuarioCollection(Collection<PerfilHasUsuario> perfilHasUsuarioCollection) {
        this.perfilHasUsuarioCollection = perfilHasUsuarioCollection;
    }

    @XmlTransient
    public Collection<Cuentamovimiento> getCuentamovimientoCollection() {
        return cuentamovimientoCollection;
    }

    public void setCuentamovimientoCollection(Collection<Cuentamovimiento> cuentamovimientoCollection) {
        this.cuentamovimientoCollection = cuentamovimientoCollection;
    }

    @XmlTransient
    public Collection<Cuentamovimiento> getCuentamovimientoCollection1() {
        return cuentamovimientoCollection1;
    }

    public void setCuentamovimientoCollection1(Collection<Cuentamovimiento> cuentamovimientoCollection1) {
        this.cuentamovimientoCollection1 = cuentamovimientoCollection1;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection1() {
        return personaCollection1;
    }

    public void setPersonaCollection1(Collection<Persona> personaCollection1) {
        this.personaCollection1 = personaCollection1;
    }

    @XmlTransient
    public Collection<BeneficiarioNoExiste> getBeneficiarioNoExisteCollection() {
        return beneficiarioNoExisteCollection;
    }

    public void setBeneficiarioNoExisteCollection(Collection<BeneficiarioNoExiste> beneficiarioNoExisteCollection) {
        this.beneficiarioNoExisteCollection = beneficiarioNoExisteCollection;
    }

    @XmlTransient
    public Collection<Reportes> getReportesCollection() {
        return reportesCollection;
    }

    public void setReportesCollection(Collection<Reportes> reportesCollection) {
        this.reportesCollection = reportesCollection;
    }

    @XmlTransient
    public Collection<Permisos> getPermisosCollection() {
        return permisosCollection;
    }

    public void setPermisosCollection(Collection<Permisos> permisosCollection) {
        this.permisosCollection = permisosCollection;
    }

    @XmlTransient
    public Collection<Beneficiario> getBeneficiarioCollection() {
        return beneficiarioCollection;
    }

    public void setBeneficiarioCollection(Collection<Beneficiario> beneficiarioCollection) {
        this.beneficiarioCollection = beneficiarioCollection;
    }

    public Persona getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Persona idpersona) {
        this.idpersona = idpersona;
    }

    @XmlTransient
    public Collection<Repoauditoria> getRepoauditoriaCollection() {
        return repoauditoriaCollection;
    }

    public void setRepoauditoriaCollection(Collection<Repoauditoria> repoauditoriaCollection) {
        this.repoauditoriaCollection = repoauditoriaCollection;
    }

    @XmlTransient
    public Collection<Pasarelapago> getPasarelapagoCollection() {
        return pasarelapagoCollection;
    }

    public void setPasarelapagoCollection(Collection<Pasarelapago> pasarelapagoCollection) {
        this.pasarelapagoCollection = pasarelapagoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Usuario[ idusuario=" + idusuario + " ]";
    }
    
}
