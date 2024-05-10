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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guiovanny
 */
@Entity
@Table(name = "recaudopromo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recaudopromo.findAll", query = "SELECT r FROM Recaudopromo r")
    , @NamedQuery(name = "Recaudopromo.findByIdrecpromocion", query = "SELECT r FROM Recaudopromo r WHERE r.idrecpromocion = :idrecpromocion")
    , @NamedQuery(name = "Recaudopromo.findByPromocionFinicial", query = "SELECT r FROM Recaudopromo r WHERE r.promocionFinicial = :promocionFinicial")
    , @NamedQuery(name = "Recaudopromo.findByPromocionFfinal", query = "SELECT r FROM Recaudopromo r WHERE r.promocionFfinal = :promocionFfinal")})
public class Recaudopromo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrecpromocion")
    private Integer idrecpromocion;
    @Column(name = "promocion_finicial")
    @Temporal(TemporalType.DATE)
    private Date promocionFinicial;
    @Column(name = "promocion_ffinal")
    @Temporal(TemporalType.DATE)
    private Date promocionFfinal;
    @JoinColumn(name = "idpromociones", referencedColumnName = "idpromociones")
    @ManyToOne(optional = false)
    private Promociones idpromociones;
    @JoinColumn(name = "idrecaudo", referencedColumnName = "idrecaudo")
    @ManyToOne(optional = false)
    private Recaudo idrecaudo;

    public Recaudopromo() {
    }

    public Recaudopromo(Integer idrecpromocion) {
        this.idrecpromocion = idrecpromocion;
    }

    public Integer getIdrecpromocion() {
        return idrecpromocion;
    }

    public void setIdrecpromocion(Integer idrecpromocion) {
        this.idrecpromocion = idrecpromocion;
    }

    public Date getPromocionFinicial() {
        return promocionFinicial;
    }

    public void setPromocionFinicial(Date promocionFinicial) {
        this.promocionFinicial = promocionFinicial;
    }

    public Date getPromocionFfinal() {
        return promocionFfinal;
    }

    public void setPromocionFfinal(Date promocionFfinal) {
        this.promocionFfinal = promocionFfinal;
    }

    public Promociones getIdpromociones() {
        return idpromociones;
    }

    public void setIdpromociones(Promociones idpromociones) {
        this.idpromociones = idpromociones;
    }

    public Recaudo getIdrecaudo() {
        return idrecaudo;
    }

    public void setIdrecaudo(Recaudo idrecaudo) {
        this.idrecaudo = idrecaudo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrecpromocion != null ? idrecpromocion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recaudopromo)) {
            return false;
        }
        Recaudopromo other = (Recaudopromo) object;
        if ((this.idrecpromocion == null && other.idrecpromocion != null) || (this.idrecpromocion != null && !this.idrecpromocion.equals(other.idrecpromocion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Recaudopromo[ idrecpromocion=" + idrecpromocion + " ]";
    }
    
}
