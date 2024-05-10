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
@Table(name = "parametrossms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametrossms.findAll", query = "SELECT p FROM Parametrossms p")
    , @NamedQuery(name = "Parametrossms.findByIdparametro", query = "SELECT p FROM Parametrossms p WHERE p.idparametro = :idparametro")
    , @NamedQuery(name = "Parametrossms.findByHost", query = "SELECT p FROM Parametrossms p WHERE p.host = :host")
    , @NamedQuery(name = "Parametrossms.findByPuerto", query = "SELECT p FROM Parametrossms p WHERE p.puerto = :puerto")
    , @NamedQuery(name = "Parametrossms.findByProtocolo", query = "SELECT p FROM Parametrossms p WHERE p.protocolo = :protocolo")
    , @NamedQuery(name = "Parametrossms.findByNombre", query = "SELECT p FROM Parametrossms p WHERE p.nombre = :nombre")})
public class Parametrossms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Idparametro")
    private Long idparametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "host")
    private String host;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "puerto")
    private String puerto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "protocolo")
    private String protocolo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;

    public Parametrossms() {
    }

    public Parametrossms(Long idparametro) {
        this.idparametro = idparametro;
    }

    public Parametrossms(Long idparametro, String host, String puerto, String protocolo, String nombre) {
        this.idparametro = idparametro;
        this.host = host;
        this.puerto = puerto;
        this.protocolo = protocolo;
        this.nombre = nombre;
    }

    public Long getIdparametro() {
        return idparametro;
    }

    public void setIdparametro(Long idparametro) {
        this.idparametro = idparametro;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparametro != null ? idparametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametrossms)) {
            return false;
        }
        Parametrossms other = (Parametrossms) object;
        if ((this.idparametro == null && other.idparametro != null) || (this.idparametro != null && !this.idparametro.equals(other.idparametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Parametrossms[ idparametro=" + idparametro + " ]";
    }
    
}
