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
@Table(name = "informes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Informes.findAll", query = "SELECT i FROM Informes i")
    , @NamedQuery(name = "Informes.findByIdinforme", query = "SELECT i FROM Informes i WHERE i.idinforme = :idinforme")
    , @NamedQuery(name = "Informes.findByTitulo", query = "SELECT i FROM Informes i WHERE i.titulo = :titulo")
    , @NamedQuery(name = "Informes.findByFechaInicio", query = "SELECT i FROM Informes i WHERE i.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Informes.findByFechaFin", query = "SELECT i FROM Informes i WHERE i.fechaFin = :fechaFin")
    , @NamedQuery(name = "Informes.findByIsFinalisado", query = "SELECT i FROM Informes i WHERE i.isFinalisado = :isFinalisado")
    , @NamedQuery(name = "Informes.findByIsCancelado", query = "SELECT i FROM Informes i WHERE i.isCancelado = :isCancelado")
    , @NamedQuery(name = "Informes.findByLog", query = "SELECT i FROM Informes i WHERE i.log = :log")
    , @NamedQuery(name = "Informes.findByCodinforme", query = "SELECT i FROM Informes i WHERE i.codinforme = :codinforme")
    , @NamedQuery(name = "Informes.findByExel", query = "SELECT i FROM Informes i WHERE i.exel = :exel")
    , @NamedQuery(name = "Informes.findByPdf", query = "SELECT i FROM Informes i WHERE i.pdf = :pdf")})
public class Informes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinforme")
    private Integer idinforme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_finalisado")
    private boolean isFinalisado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_cancelado")
    private boolean isCancelado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "log")
    private String log;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codinforme")
    private int codinforme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "exel")
    private String exel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pdf")
    private String pdf;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private Persona idpersona;

    public Informes() {
    }

    public Informes(Integer idinforme) {
        this.idinforme = idinforme;
    }

    public Informes(Integer idinforme, String titulo, Date fechaInicio, Date fechaFin, boolean isFinalisado, boolean isCancelado, String log, int codinforme, String exel, String pdf) {
        this.idinforme = idinforme;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.isFinalisado = isFinalisado;
        this.isCancelado = isCancelado;
        this.log = log;
        this.codinforme = codinforme;
        this.exel = exel;
        this.pdf = pdf;
    }

    public Integer getIdinforme() {
        return idinforme;
    }

    public void setIdinforme(Integer idinforme) {
        this.idinforme = idinforme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean getIsFinalisado() {
        return isFinalisado;
    }

    public void setIsFinalisado(boolean isFinalisado) {
        this.isFinalisado = isFinalisado;
    }

    public boolean getIsCancelado() {
        return isCancelado;
    }

    public void setIsCancelado(boolean isCancelado) {
        this.isCancelado = isCancelado;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public int getCodinforme() {
        return codinforme;
    }

    public void setCodinforme(int codinforme) {
        this.codinforme = codinforme;
    }

    public String getExel() {
        return exel;
    }

    public void setExel(String exel) {
        this.exel = exel;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public Persona getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Persona idpersona) {
        this.idpersona = idpersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinforme != null ? idinforme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Informes)) {
            return false;
        }
        Informes other = (Informes) object;
        if ((this.idinforme == null && other.idinforme != null) || (this.idinforme != null && !this.idinforme.equals(other.idinforme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Informes[ idinforme=" + idinforme + " ]";
    }
    
}
