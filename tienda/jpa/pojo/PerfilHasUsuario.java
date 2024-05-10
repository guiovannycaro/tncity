/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.jpa.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Desarrollador
 */
@Entity
@Table(name = "perfil_has_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilHasUsuario.findAll", query = "SELECT p FROM PerfilHasUsuario p")
    , @NamedQuery(name = "PerfilHasUsuario.findByIdpu", query = "SELECT p FROM PerfilHasUsuario p WHERE p.idpu = :idpu")})
public class PerfilHasUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "idpu")
    private String idpu;
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne(optional = false)
    private Perfil idperfil;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public PerfilHasUsuario() {
    }

    public PerfilHasUsuario(String idpu) {
        this.idpu = idpu;
    }

    public String getIdpu() {
        return idpu;
    }

    public void setIdpu(String idpu) {
        this.idpu = idpu;
    }

    public Perfil getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Perfil idperfil) {
        this.idperfil = idperfil;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpu != null ? idpu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilHasUsuario)) {
            return false;
        }
        PerfilHasUsuario other = (PerfilHasUsuario) object;
        if ((this.idpu == null && other.idpu != null) || (this.idpu != null && !this.idpu.equals(other.idpu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tienda.jpa.pojo.PerfilHasUsuario[ idpu=" + idpu + " ]";
    }
    
}
