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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guiovanny
 */
@Entity
@Table(name = "transmensajetexto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transmensajetexto.findAll", query = "SELECT t FROM Transmensajetexto t")
    , @NamedQuery(name = "Transmensajetexto.findByIdmensajetxt", query = "SELECT t FROM Transmensajetexto t WHERE t.idmensajetxt = :idmensajetxt")
    , @NamedQuery(name = "Transmensajetexto.findByStatus", query = "SELECT t FROM Transmensajetexto t WHERE t.status = :status")
    , @NamedQuery(name = "Transmensajetexto.findByCantidadmensajes", query = "SELECT t FROM Transmensajetexto t WHERE t.cantidadmensajes = :cantidadmensajes")
    , @NamedQuery(name = "Transmensajetexto.findByValor", query = "SELECT t FROM Transmensajetexto t WHERE t.valor = :valor")
    , @NamedQuery(name = "Transmensajetexto.findByCantidadCaracteres", query = "SELECT t FROM Transmensajetexto t WHERE t.cantidadCaracteres = :cantidadCaracteres")
    , @NamedQuery(name = "Transmensajetexto.findByMensaje", query = "SELECT t FROM Transmensajetexto t WHERE t.mensaje = :mensaje")
    , @NamedQuery(name = "Transmensajetexto.findByIdconfirmacion", query = "SELECT t FROM Transmensajetexto t WHERE t.idconfirmacion = :idconfirmacion")})
public class Transmensajetexto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmensajetxt")
    private Integer idmensajetxt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "cantidadmensajes")
    private String cantidadmensajes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "valor")
    private String valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "cantidad_caracteres")
    private String cantidadCaracteres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "mensaje")
    private String mensaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "idconfirmacion")
    private String idconfirmacion;
    @JoinColumn(name = "transaccionid", referencedColumnName = "idtransaccion")
    @ManyToOne(optional = false)
    private Transacciones transaccionid;

    public Transmensajetexto() {
    }

    public Transmensajetexto(Integer idmensajetxt) {
        this.idmensajetxt = idmensajetxt;
    }

    public Transmensajetexto(Integer idmensajetxt, String status, String cantidadmensajes, String valor, String cantidadCaracteres, String mensaje, String idconfirmacion) {
        this.idmensajetxt = idmensajetxt;
        this.status = status;
        this.cantidadmensajes = cantidadmensajes;
        this.valor = valor;
        this.cantidadCaracteres = cantidadCaracteres;
        this.mensaje = mensaje;
        this.idconfirmacion = idconfirmacion;
    }

    public Integer getIdmensajetxt() {
        return idmensajetxt;
    }

    public void setIdmensajetxt(Integer idmensajetxt) {
        this.idmensajetxt = idmensajetxt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCantidadmensajes() {
        return cantidadmensajes;
    }

    public void setCantidadmensajes(String cantidadmensajes) {
        this.cantidadmensajes = cantidadmensajes;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCantidadCaracteres() {
        return cantidadCaracteres;
    }

    public void setCantidadCaracteres(String cantidadCaracteres) {
        this.cantidadCaracteres = cantidadCaracteres;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIdconfirmacion() {
        return idconfirmacion;
    }

    public void setIdconfirmacion(String idconfirmacion) {
        this.idconfirmacion = idconfirmacion;
    }

    public Transacciones getTransaccionid() {
        return transaccionid;
    }

    public void setTransaccionid(Transacciones transaccionid) {
        this.transaccionid = transaccionid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmensajetxt != null ? idmensajetxt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transmensajetexto)) {
            return false;
        }
        Transmensajetexto other = (Transmensajetexto) object;
        if ((this.idmensajetxt == null && other.idmensajetxt != null) || (this.idmensajetxt != null && !this.idmensajetxt.equals(other.idmensajetxt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Transmensajetexto[ idmensajetxt=" + idmensajetxt + " ]";
    }
    
}
