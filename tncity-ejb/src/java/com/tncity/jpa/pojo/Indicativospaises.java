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
@Table(name = "indicativospaises")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indicativospaises.findAll", query = "SELECT i FROM Indicativospaises i")
    , @NamedQuery(name = "Indicativospaises.findByIdindicativo", query = "SELECT i FROM Indicativospaises i WHERE i.idindicativo = :idindicativo")
    , @NamedQuery(name = "Indicativospaises.findByCodindicativo", query = "SELECT i FROM Indicativospaises i WHERE i.codindicativo = :codindicativo")
    , @NamedQuery(name = "Indicativospaises.findByCodarea", query = "SELECT i FROM Indicativospaises i WHERE i.codarea = :codarea")})
public class Indicativospaises implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idindicativo")
    private Integer idindicativo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "codindicativo")
    private String codindicativo;
    @Column(name = "codarea")
    private Integer codarea;
    @JoinColumn(name = "idciudad", referencedColumnName = "idciudad")
    @ManyToOne(optional = false)
    private Ciudad idciudad;
    @JoinColumn(name = "iddepartamento", referencedColumnName = "iddepartamento")
    @ManyToOne(optional = false)
    private Departamentoestado iddepartamento;
    @JoinColumn(name = "idpais", referencedColumnName = "idpais")
    @ManyToOne(optional = false)
    private Pais idpais;

    public Indicativospaises() {
    }

    public Indicativospaises(Integer idindicativo) {
        this.idindicativo = idindicativo;
    }

    public Indicativospaises(Integer idindicativo, String codindicativo) {
        this.idindicativo = idindicativo;
        this.codindicativo = codindicativo;
    }

    public Integer getIdindicativo() {
        return idindicativo;
    }

    public void setIdindicativo(Integer idindicativo) {
        this.idindicativo = idindicativo;
    }

    public String getCodindicativo() {
        return codindicativo;
    }

    public void setCodindicativo(String codindicativo) {
        this.codindicativo = codindicativo;
    }

    public Integer getCodarea() {
        return codarea;
    }

    public void setCodarea(Integer codarea) {
        this.codarea = codarea;
    }

    public Ciudad getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Ciudad idciudad) {
        this.idciudad = idciudad;
    }

    public Departamentoestado getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Departamentoestado iddepartamento) {
        this.iddepartamento = iddepartamento;
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
        hash += (idindicativo != null ? idindicativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indicativospaises)) {
            return false;
        }
        Indicativospaises other = (Indicativospaises) object;
        if ((this.idindicativo == null && other.idindicativo != null) || (this.idindicativo != null && !this.idindicativo.equals(other.idindicativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Indicativospaises[ idindicativo=" + idindicativo + " ]";
    }
    
}
