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
@Table(name = "subsecciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subsecciones.findAll", query = "SELECT s FROM Subsecciones s")
    , @NamedQuery(name = "Subsecciones.findByIdsubseccion", query = "SELECT s FROM Subsecciones s WHERE s.idsubseccion = :idsubseccion")
    , @NamedQuery(name = "Subsecciones.findByNombre", query = "SELECT s FROM Subsecciones s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Subsecciones.findByImagen", query = "SELECT s FROM Subsecciones s WHERE s.imagen = :imagen")
    , @NamedQuery(name = "Subsecciones.findByUrl", query = "SELECT s FROM Subsecciones s WHERE s.url = :url")
    , @NamedQuery(name = "Subsecciones.findByNombrevisual", query = "SELECT s FROM Subsecciones s WHERE s.nombrevisual = :nombrevisual")
    , @NamedQuery(name = "Subsecciones.findByEstado", query = "SELECT s FROM Subsecciones s WHERE s.estado = :estado")})
public class Subsecciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsubseccion")
    private Integer idsubseccion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsubsecccion")
    private Collection<Permisos> permisosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsubseccion")
    private Collection<Moduloxseccionxsubseccion> moduloxseccionxsubseccionCollection;

    public Subsecciones() {
    }

    public Subsecciones(Integer idsubseccion) {
        this.idsubseccion = idsubseccion;
    }

    public Subsecciones(Integer idsubseccion, String nombre, String imagen, String url, String nombrevisual, boolean estado) {
        this.idsubseccion = idsubseccion;
        this.nombre = nombre;
        this.imagen = imagen;
        this.url = url;
        this.nombrevisual = nombrevisual;
        this.estado = estado;
    }

    public Integer getIdsubseccion() {
        return idsubseccion;
    }

    public void setIdsubseccion(Integer idsubseccion) {
        this.idsubseccion = idsubseccion;
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
        hash += (idsubseccion != null ? idsubseccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subsecciones)) {
            return false;
        }
        Subsecciones other = (Subsecciones) object;
        if ((this.idsubseccion == null && other.idsubseccion != null) || (this.idsubseccion != null && !this.idsubseccion.equals(other.idsubseccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Subsecciones[ idsubseccion=" + idsubseccion + " ]";
    }
    
}
