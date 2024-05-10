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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guiovanny
 */
@Entity
@Table(name = "moduloxseccionxsubseccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moduloxseccionxsubseccion.findAll", query = "SELECT m FROM Moduloxseccionxsubseccion m")
    , @NamedQuery(name = "Moduloxseccionxsubseccion.findByIdmodseccionsubseccion", query = "SELECT m FROM Moduloxseccionxsubseccion m WHERE m.idmodseccionsubseccion = :idmodseccionsubseccion")})
public class Moduloxseccionxsubseccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmodseccionsubseccion")
    private Integer idmodseccionsubseccion;
    @JoinColumn(name = "idmodulo", referencedColumnName = "idmodulo")
    @ManyToOne(optional = false)
    private Modulos idmodulo;
    @JoinColumn(name = "idseccion", referencedColumnName = "idseccion")
    @ManyToOne(optional = false)
    private Secciones idseccion;
    @JoinColumn(name = "idsubseccion", referencedColumnName = "idsubseccion")
    @ManyToOne(optional = false)
    private Subsecciones idsubseccion;

    public Moduloxseccionxsubseccion() {
    }

    public Moduloxseccionxsubseccion(Integer idmodseccionsubseccion) {
        this.idmodseccionsubseccion = idmodseccionsubseccion;
    }

    public Integer getIdmodseccionsubseccion() {
        return idmodseccionsubseccion;
    }

    public void setIdmodseccionsubseccion(Integer idmodseccionsubseccion) {
        this.idmodseccionsubseccion = idmodseccionsubseccion;
    }

    public Modulos getIdmodulo() {
        return idmodulo;
    }

    public void setIdmodulo(Modulos idmodulo) {
        this.idmodulo = idmodulo;
    }

    public Secciones getIdseccion() {
        return idseccion;
    }

    public void setIdseccion(Secciones idseccion) {
        this.idseccion = idseccion;
    }

    public Subsecciones getIdsubseccion() {
        return idsubseccion;
    }

    public void setIdsubseccion(Subsecciones idsubseccion) {
        this.idsubseccion = idsubseccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmodseccionsubseccion != null ? idmodseccionsubseccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moduloxseccionxsubseccion)) {
            return false;
        }
        Moduloxseccionxsubseccion other = (Moduloxseccionxsubseccion) object;
        if ((this.idmodseccionsubseccion == null && other.idmodseccionsubseccion != null) || (this.idmodseccionsubseccion != null && !this.idmodseccionsubseccion.equals(other.idmodseccionsubseccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Moduloxseccionxsubseccion[ idmodseccionsubseccion=" + idmodseccionsubseccion + " ]";
    }
    
}
