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
@Table(name = "reportes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reportes.findAll", query = "SELECT r FROM Reportes r")
    , @NamedQuery(name = "Reportes.findByIdreporte", query = "SELECT r FROM Reportes r WHERE r.idreporte = :idreporte")
    , @NamedQuery(name = "Reportes.findByNombre", query = "SELECT r FROM Reportes r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "Reportes.findByImagen", query = "SELECT r FROM Reportes r WHERE r.imagen = :imagen")
    , @NamedQuery(name = "Reportes.findByUrl", query = "SELECT r FROM Reportes r WHERE r.url = :url")
    , @NamedQuery(name = "Reportes.findByNombrevisual", query = "SELECT r FROM Reportes r WHERE r.nombrevisual = :nombrevisual")
    , @NamedQuery(name = "Reportes.findByEstado", query = "SELECT r FROM Reportes r WHERE r.estado = :estado")
    , @NamedQuery(name = "Reportes.findByCreatedAt", query = "SELECT r FROM Reportes r WHERE r.createdAt = :createdAt")})
public class Reportes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreporte")
    private Integer idreporte;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Reportes() {
    }

    public Reportes(Integer idreporte) {
        this.idreporte = idreporte;
    }

    public Reportes(Integer idreporte, String nombre, String imagen, String url, String nombrevisual, boolean estado, Date createdAt) {
        this.idreporte = idreporte;
        this.nombre = nombre;
        this.imagen = imagen;
        this.url = url;
        this.nombrevisual = nombrevisual;
        this.estado = estado;
        this.createdAt = createdAt;
    }

    public Integer getIdreporte() {
        return idreporte;
    }

    public void setIdreporte(Integer idreporte) {
        this.idreporte = idreporte;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
        hash += (idreporte != null ? idreporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reportes)) {
            return false;
        }
        Reportes other = (Reportes) object;
        if ((this.idreporte == null && other.idreporte != null) || (this.idreporte != null && !this.idreporte.equals(other.idreporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Reportes[ idreporte=" + idreporte + " ]";
    }
    
}
