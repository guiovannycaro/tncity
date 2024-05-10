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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guiovanny
 */
@Entity
@Table(name = "permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p")
    , @NamedQuery(name = "Permisos.findByIdpermiso", query = "SELECT p FROM Permisos p WHERE p.idpermiso = :idpermiso")
    , @NamedQuery(name = "Permisos.findByConsulta", query = "SELECT p FROM Permisos p WHERE p.consulta = :consulta")
    , @NamedQuery(name = "Permisos.findByIngreso", query = "SELECT p FROM Permisos p WHERE p.ingreso = :ingreso")
    , @NamedQuery(name = "Permisos.findByEliminacion", query = "SELECT p FROM Permisos p WHERE p.eliminacion = :eliminacion")
    , @NamedQuery(name = "Permisos.findByEdicion", query = "SELECT p FROM Permisos p WHERE p.edicion = :edicion")
    , @NamedQuery(name = "Permisos.findByBuscar", query = "SELECT p FROM Permisos p WHERE p.buscar = :buscar")
    , @NamedQuery(name = "Permisos.findByModulo", query = "SELECT p FROM Permisos p WHERE p.modulo = :modulo")
    , @NamedQuery(name = "Permisos.findBySeccion", query = "SELECT p FROM Permisos p WHERE p.seccion = :seccion")
    , @NamedQuery(name = "Permisos.findBySubseccion", query = "SELECT p FROM Permisos p WHERE p.subseccion = :subseccion")})
public class Permisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpermiso")
    private Integer idpermiso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consulta")
    private boolean consulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ingreso")
    private boolean ingreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminacion")
    private boolean eliminacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "edicion")
    private boolean edicion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "buscar")
    private boolean buscar;
    @Column(name = "modulo")
    private Boolean modulo;
    @Column(name = "seccion")
    private Boolean seccion;
    @Column(name = "subseccion")
    private Boolean subseccion;
    @JoinColumn(name = "idmodulo", referencedColumnName = "idmodulo")
    @ManyToOne(optional = false)
    private Modulos idmodulo;
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne(optional = false)
    private Perfil idperfil;
    @JoinColumn(name = "idseccion", referencedColumnName = "idseccion")
    @ManyToOne(optional = false)
    private Secciones idseccion;
    @JoinColumn(name = "idsubsecccion", referencedColumnName = "idsubseccion")
    @ManyToOne(optional = false)
    private Subsecciones idsubsecccion;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Permisos() {
    }

    public Permisos(Integer idpermiso) {
        this.idpermiso = idpermiso;
    }

    public Permisos(Integer idpermiso, boolean consulta, boolean ingreso, boolean eliminacion, boolean edicion, boolean buscar) {
        this.idpermiso = idpermiso;
        this.consulta = consulta;
        this.ingreso = ingreso;
        this.eliminacion = eliminacion;
        this.edicion = edicion;
        this.buscar = buscar;
    }

    public Integer getIdpermiso() {
        return idpermiso;
    }

    public void setIdpermiso(Integer idpermiso) {
        this.idpermiso = idpermiso;
    }

    public boolean getConsulta() {
        return consulta;
    }

    public void setConsulta(boolean consulta) {
        this.consulta = consulta;
    }

    public boolean getIngreso() {
        return ingreso;
    }

    public void setIngreso(boolean ingreso) {
        this.ingreso = ingreso;
    }

    public boolean getEliminacion() {
        return eliminacion;
    }

    public void setEliminacion(boolean eliminacion) {
        this.eliminacion = eliminacion;
    }

    public boolean getEdicion() {
        return edicion;
    }

    public void setEdicion(boolean edicion) {
        this.edicion = edicion;
    }

    public boolean getBuscar() {
        return buscar;
    }

    public void setBuscar(boolean buscar) {
        this.buscar = buscar;
    }

    public Boolean getModulo() {
        return modulo;
    }

    public void setModulo(Boolean modulo) {
        this.modulo = modulo;
    }

    public Boolean getSeccion() {
        return seccion;
    }

    public void setSeccion(Boolean seccion) {
        this.seccion = seccion;
    }

    public Boolean getSubseccion() {
        return subseccion;
    }

    public void setSubseccion(Boolean subseccion) {
        this.subseccion = subseccion;
    }

    public Modulos getIdmodulo() {
        return idmodulo;
    }

    public void setIdmodulo(Modulos idmodulo) {
        this.idmodulo = idmodulo;
    }

    public Perfil getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Perfil idperfil) {
        this.idperfil = idperfil;
    }

    public Secciones getIdseccion() {
        return idseccion;
    }

    public void setIdseccion(Secciones idseccion) {
        this.idseccion = idseccion;
    }

    public Subsecciones getIdsubsecccion() {
        return idsubsecccion;
    }

    public void setIdsubsecccion(Subsecciones idsubsecccion) {
        this.idsubsecccion = idsubsecccion;
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
        hash += (idpermiso != null ? idpermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.idpermiso == null && other.idpermiso != null) || (this.idpermiso != null && !this.idpermiso.equals(other.idpermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Permisos[ idpermiso=" + idpermiso + " ]";
    }
    
}
