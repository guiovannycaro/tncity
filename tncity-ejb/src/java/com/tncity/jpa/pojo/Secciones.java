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
@Table(name = "secciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Secciones.findAll", query = "SELECT s FROM Secciones s")
    , @NamedQuery(name = "Secciones.findByIdseccion", query = "SELECT s FROM Secciones s WHERE s.idseccion = :idseccion")
    , @NamedQuery(name = "Secciones.findByNombre", query = "SELECT s FROM Secciones s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Secciones.findByNombrevisual", query = "SELECT s FROM Secciones s WHERE s.nombrevisual = :nombrevisual")
    , @NamedQuery(name = "Secciones.findByEstado", query = "SELECT s FROM Secciones s WHERE s.estado = :estado")})
public class Secciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idseccion")
    private Integer idseccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombrevisual")
    private String nombrevisual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idseccion")
    private Collection<Permisos> permisosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idseccion")
    private Collection<Moduloxseccionxsubseccion> moduloxseccionxsubseccionCollection;

    public Secciones() {
    }

    public Secciones(Integer idseccion) {
        this.idseccion = idseccion;
    }

    public Secciones(Integer idseccion, String nombre, String nombrevisual, boolean estado) {
        this.idseccion = idseccion;
        this.nombre = nombre;
        this.nombrevisual = nombrevisual;
        this.estado = estado;
    }

    public Integer getIdseccion() {
        return idseccion;
    }

    public void setIdseccion(Integer idseccion) {
        this.idseccion = idseccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombrevisual() {
        return nombrevisual;
    }

    public void setNombrevisual(String nombrevisual) {
        this.nombrevisual = nombrevisual;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Permisos> getPermisosCollection() {
        return permisosCollection;
    }

    public void setPermisosCollection(Collection<Permisos> permisosCollection) {
        this.permisosCollection = permisosCollection;
    }

    @XmlTransient
    public Collection<Moduloxseccionxsubseccion> getModuloxseccionxsubseccionCollection() {
        return moduloxseccionxsubseccionCollection;
    }

    public void setModuloxseccionxsubseccionCollection(Collection<Moduloxseccionxsubseccion> moduloxseccionxsubseccionCollection) {
        this.moduloxseccionxsubseccionCollection = moduloxseccionxsubseccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idseccion != null ? idseccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Secciones)) {
            return false;
        }
        Secciones other = (Secciones) object;
        if ((this.idseccion == null && other.idseccion != null) || (this.idseccion != null && !this.idseccion.equals(other.idseccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Secciones[ idseccion=" + idseccion + " ]";
    }
    
}
