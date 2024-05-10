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
@Table(name = "modulos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modulos.findAll", query = "SELECT m FROM Modulos m")
    , @NamedQuery(name = "Modulos.findByIdmodulo", query = "SELECT m FROM Modulos m WHERE m.idmodulo = :idmodulo")
    , @NamedQuery(name = "Modulos.findByNombre", query = "SELECT m FROM Modulos m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Modulos.findByImagen", query = "SELECT m FROM Modulos m WHERE m.imagen = :imagen")
    , @NamedQuery(name = "Modulos.findByUrl", query = "SELECT m FROM Modulos m WHERE m.url = :url")
    , @NamedQuery(name = "Modulos.findByNombrevisual", query = "SELECT m FROM Modulos m WHERE m.nombrevisual = :nombrevisual")
    , @NamedQuery(name = "Modulos.findByEstado", query = "SELECT m FROM Modulos m WHERE m.estado = :estado")})
public class Modulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmodulo")
    private Integer idmodulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "imagen")
    private String imagen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombrevisual")
    private String nombrevisual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmodulo")
    private Collection<Permisos> permisosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmodulo")
    private Collection<Moduloxseccionxsubseccion> moduloxseccionxsubseccionCollection;

    public Modulos() {
    }

    public Modulos(Integer idmodulo) {
        this.idmodulo = idmodulo;
    }

    public Modulos(Integer idmodulo, String nombre, String imagen, String url, String nombrevisual, boolean estado) {
        this.idmodulo = idmodulo;
        this.nombre = nombre;
        this.imagen = imagen;
        this.url = url;
        this.nombrevisual = nombrevisual;
        this.estado = estado;
    }

    public Integer getIdmodulo() {
        return idmodulo;
    }

    public void setIdmodulo(Integer idmodulo) {
        this.idmodulo = idmodulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        hash += (idmodulo != null ? idmodulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modulos)) {
            return false;
        }
        Modulos other = (Modulos) object;
        if ((this.idmodulo == null && other.idmodulo != null) || (this.idmodulo != null && !this.idmodulo.equals(other.idmodulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Modulos[ idmodulo=" + idmodulo + " ]";
    }
    
}
