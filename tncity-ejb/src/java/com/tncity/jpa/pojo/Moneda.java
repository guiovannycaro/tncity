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
@Table(name = "moneda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moneda.findAll", query = "SELECT m FROM Moneda m")
    , @NamedQuery(name = "Moneda.findByIdmoneda", query = "SELECT m FROM Moneda m WHERE m.idmoneda = :idmoneda")
    , @NamedQuery(name = "Moneda.findByCodeiso4217", query = "SELECT m FROM Moneda m WHERE m.codeiso4217 = :codeiso4217")
    , @NamedQuery(name = "Moneda.findByNumiso4217", query = "SELECT m FROM Moneda m WHERE m.numiso4217 = :numiso4217")
    , @NamedQuery(name = "Moneda.findBySimbolo", query = "SELECT m FROM Moneda m WHERE m.simbolo = :simbolo")
    , @NamedQuery(name = "Moneda.findByValortrm", query = "SELECT m FROM Moneda m WHERE m.valortrm = :valortrm")
    , @NamedQuery(name = "Moneda.findByDescripcion", query = "SELECT m FROM Moneda m WHERE m.descripcion = :descripcion")})
public class Moneda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmoneda")
    private Integer idmoneda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "codeiso4217")
    private String codeiso4217;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "numiso4217")
    private String numiso4217;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "simbolo")
    private String simbolo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "valortrm")
    private String valortrm;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "idpais", referencedColumnName = "idpais")
    @ManyToOne(optional = false)
    private Pais idpais;

    public Moneda() {
    }

    public Moneda(Integer idmoneda) {
        this.idmoneda = idmoneda;
    }

    public Moneda(Integer idmoneda, String codeiso4217, String numiso4217, String simbolo, String valortrm) {
        this.idmoneda = idmoneda;
        this.codeiso4217 = codeiso4217;
        this.numiso4217 = numiso4217;
        this.simbolo = simbolo;
        this.valortrm = valortrm;
    }

    public Integer getIdmoneda() {
        return idmoneda;
    }

    public void setIdmoneda(Integer idmoneda) {
        this.idmoneda = idmoneda;
    }

    public String getCodeiso4217() {
        return codeiso4217;
    }

    public void setCodeiso4217(String codeiso4217) {
        this.codeiso4217 = codeiso4217;
    }

    public String getNumiso4217() {
        return numiso4217;
    }

    public void setNumiso4217(String numiso4217) {
        this.numiso4217 = numiso4217;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getValortrm() {
        return valortrm;
    }

    public void setValortrm(String valortrm) {
        this.valortrm = valortrm;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Pais getIdpais() {
        return idpais;
    }

    public void setIdpais(Pais idpais) {
        this.idpais = idpais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmoneda != null ? idmoneda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moneda)) {
            return false;
        }
        Moneda other = (Moneda) object;
        if ((this.idmoneda == null && other.idmoneda != null) || (this.idmoneda != null && !this.idmoneda.equals(other.idmoneda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Moneda[ idmoneda=" + idmoneda + " ]";
    }
    
}
