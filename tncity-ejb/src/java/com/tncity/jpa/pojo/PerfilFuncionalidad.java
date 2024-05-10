/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.pojo;

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
 * @author guiovanny
 */
@Entity
@Table(name = "perfil_funcionalidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilFuncionalidad.findAll", query = "SELECT p FROM PerfilFuncionalidad p")
    , @NamedQuery(name = "PerfilFuncionalidad.findByIdpf", query = "SELECT p FROM PerfilFuncionalidad p WHERE p.idpf = :idpf")
    , @NamedQuery(name = "PerfilFuncionalidad.findByFuncionalidadId", query = "SELECT p FROM PerfilFuncionalidad p WHERE p.funcionalidadId = :funcionalidadId")})
public class PerfilFuncionalidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "idpf")
    private String idpf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "funcionalidad_id")
    private int funcionalidadId;
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne(optional = false)
    private Perfil idperfil;

    public PerfilFuncionalidad() {
    }

    public PerfilFuncionalidad(String idpf) {
        this.idpf = idpf;
    }

    public PerfilFuncionalidad(String idpf, int funcionalidadId) {
        this.idpf = idpf;
        this.funcionalidadId = funcionalidadId;
    }

    public String getIdpf() {
        return idpf;
    }

    public void setIdpf(String idpf) {
        this.idpf = idpf;
    }

    public int getFuncionalidadId() {
        return funcionalidadId;
    }

    public void setFuncionalidadId(int funcionalidadId) {
        this.funcionalidadId = funcionalidadId;
    }

    public Perfil getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Perfil idperfil) {
        this.idperfil = idperfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpf != null ? idpf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilFuncionalidad)) {
            return false;
        }
        PerfilFuncionalidad other = (PerfilFuncionalidad) object;
        if ((this.idpf == null && other.idpf != null) || (this.idpf != null && !this.idpf.equals(other.idpf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.PerfilFuncionalidad[ idpf=" + idpf + " ]";
    }
    
}
