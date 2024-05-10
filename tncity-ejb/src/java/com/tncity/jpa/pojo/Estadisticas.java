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
@Table(name = "estadisticas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadisticas.findAll", query = "SELECT e FROM Estadisticas e")
    , @NamedQuery(name = "Estadisticas.findByIdestadistica", query = "SELECT e FROM Estadisticas e WHERE e.idestadistica = :idestadistica")
    , @NamedQuery(name = "Estadisticas.findByTitulo", query = "SELECT e FROM Estadisticas e WHERE e.titulo = :titulo")
    , @NamedQuery(name = "Estadisticas.findByFechaInicio", query = "SELECT e FROM Estadisticas e WHERE e.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Estadisticas.findByFechaFin", query = "SELECT e FROM Estadisticas e WHERE e.fechaFin = :fechaFin")
    , @NamedQuery(name = "Estadisticas.findByIsFinalisado", query = "SELECT e FROM Estadisticas e WHERE e.isFinalisado = :isFinalisado")
    , @NamedQuery(name = "Estadisticas.findByIsCancelado", query = "SELECT e FROM Estadisticas e WHERE e.isCancelado = :isCancelado")
    , @NamedQuery(name = "Estadisticas.findByLog", query = "SELECT e FROM Estadisticas e WHERE e.log = :log")
    , @NamedQuery(name = "Estadisticas.findByCodestadistica", query = "SELECT e FROM Estadisticas e WHERE e.codestadistica = :codestadistica")
    , @NamedQuery(name = "Estadisticas.findByExel", query = "SELECT e FROM Estadisticas e WHERE e.exel = :exel")
    , @NamedQuery(name = "Estadisticas.findByPdf", query = "SELECT e FROM Estadisticas e WHERE e.pdf = :pdf")})
public class Estadisticas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestadistica")
    private Integer idestadistica;
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
    @Column(name = "codestadistica")
    private int codestadistica;
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

    public Estadisticas() {
    }

    public Estadisticas(Integer idestadistica) {
        this.idestadistica = idestadistica;
    }

    public Estadisticas(Integer idestadistica, String titulo, Date fechaInicio, Date fechaFin, boolean isFinalisado, boolean isCancelado, String log, int codestadistica, String exel, String pdf) {
        this.idestadistica = idestadistica;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.isFinalisado = isFinalisado;
        this.isCancelado = isCancelado;
        this.log = log;
        this.codestadistica = codestadistica;
        this.exel = exel;
        this.pdf = pdf;
    }

    public Integer getIdestadistica() {
        return idestadistica;
    }

    public void setIdestadistica(Integer idestadistica) {
        this.idestadistica = idestadistica;
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

    public int getCodestadistica() {
        return codestadistica;
    }

    public void setCodestadistica(int codestadistica) {
        this.codestadistica = codestadistica;
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
        hash += (idestadistica != null ? idestadistica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadisticas)) {
            return false;
        }
        Estadisticas other = (Estadisticas) object;
        if ((this.idestadistica == null && other.idestadistica != null) || (this.idestadistica != null && !this.idestadistica.equals(other.idestadistica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Estadisticas[ idestadistica=" + idestadistica + " ]";
    }
    
}
