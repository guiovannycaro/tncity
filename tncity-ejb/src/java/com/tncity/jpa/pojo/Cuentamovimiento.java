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
@Table(name = "cuentamovimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuentamovimiento.findAll", query = "SELECT c FROM Cuentamovimiento c")
    , @NamedQuery(name = "Cuentamovimiento.findByIdmovimiento", query = "SELECT c FROM Cuentamovimiento c WHERE c.idmovimiento = :idmovimiento")
    , @NamedQuery(name = "Cuentamovimiento.findByValor", query = "SELECT c FROM Cuentamovimiento c WHERE c.valor = :valor")
    , @NamedQuery(name = "Cuentamovimiento.findByTipo", query = "SELECT c FROM Cuentamovimiento c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "Cuentamovimiento.findByObservaciones", query = "SELECT c FROM Cuentamovimiento c WHERE c.observaciones = :observaciones")
    , @NamedQuery(name = "Cuentamovimiento.findByPathAdjunto", query = "SELECT c FROM Cuentamovimiento c WHERE c.pathAdjunto = :pathAdjunto")
    , @NamedQuery(name = "Cuentamovimiento.findByEstado", query = "SELECT c FROM Cuentamovimiento c WHERE c.estado = :estado")
    , @NamedQuery(name = "Cuentamovimiento.findByObservacionesAnulacion", query = "SELECT c FROM Cuentamovimiento c WHERE c.observacionesAnulacion = :observacionesAnulacion")
    , @NamedQuery(name = "Cuentamovimiento.findByUpdatedAt", query = "SELECT c FROM Cuentamovimiento c WHERE c.updatedAt = :updatedAt")
    , @NamedQuery(name = "Cuentamovimiento.findByCreatedAt", query = "SELECT c FROM Cuentamovimiento c WHERE c.createdAt = :createdAt")})
public class Cuentamovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmovimiento")
    private Long idmovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 2147483647)
    @Column(name = "path_adjunto")
    private String pathAdjunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @Size(max = 2147483647)
    @Column(name = "observaciones_anulacion")
    private String observacionesAnulacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "idcuenta", referencedColumnName = "idcuenta")
    @ManyToOne(optional = false)
    private Cuenta idcuenta;
    @JoinColumn(name = "idrecaudo", referencedColumnName = "idrecaudo")
    @ManyToOne
    private Recaudo idrecaudo;
    @JoinColumn(name = "idusuario_created", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuarioCreated;
    @JoinColumn(name = "idusuario_updated", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuarioUpdated;

    public Cuentamovimiento() {
    }

    public Cuentamovimiento(Long idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Cuentamovimiento(Long idmovimiento, double valor, String tipo, String observaciones, String estado, Date updatedAt, Date createdAt) {
        this.idmovimiento = idmovimiento;
        this.valor = valor;
        this.tipo = tipo;
        this.observaciones = observaciones;
        this.estado = estado;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Long getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Long idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPathAdjunto() {
        return pathAdjunto;
    }

    public void setPathAdjunto(String pathAdjunto) {
        this.pathAdjunto = pathAdjunto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacionesAnulacion() {
        return observacionesAnulacion;
    }

    public void setObservacionesAnulacion(String observacionesAnulacion) {
        this.observacionesAnulacion = observacionesAnulacion;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Cuenta getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Cuenta idcuenta) {
        this.idcuenta = idcuenta;
    }

    public Recaudo getIdrecaudo() {
        return idrecaudo;
    }

    public void setIdrecaudo(Recaudo idrecaudo) {
        this.idrecaudo = idrecaudo;
    }

    public Usuario getIdusuarioCreated() {
        return idusuarioCreated;
    }

    public void setIdusuarioCreated(Usuario idusuarioCreated) {
        this.idusuarioCreated = idusuarioCreated;
    }

    public Usuario getIdusuarioUpdated() {
        return idusuarioUpdated;
    }

    public void setIdusuarioUpdated(Usuario idusuarioUpdated) {
        this.idusuarioUpdated = idusuarioUpdated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovimiento != null ? idmovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuentamovimiento)) {
            return false;
        }
        Cuentamovimiento other = (Cuentamovimiento) object;
        if ((this.idmovimiento == null && other.idmovimiento != null) || (this.idmovimiento != null && !this.idmovimiento.equals(other.idmovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Cuentamovimiento[ idmovimiento=" + idmovimiento + " ]";
    }
    
}
