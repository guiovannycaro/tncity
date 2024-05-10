/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.pojo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guiovanny
 */
@Entity
@Table(name = "cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c")
    , @NamedQuery(name = "Cuenta.findByIdcuenta", query = "SELECT c FROM Cuenta c WHERE c.idcuenta = :idcuenta")
    , @NamedQuery(name = "Cuenta.findByValEntradas", query = "SELECT c FROM Cuenta c WHERE c.valEntradas = :valEntradas")
    , @NamedQuery(name = "Cuenta.findByValSalidas", query = "SELECT c FROM Cuenta c WHERE c.valSalidas = :valSalidas")
    , @NamedQuery(name = "Cuenta.findByValSaldo", query = "SELECT c FROM Cuenta c WHERE c.valSaldo = :valSaldo")
    , @NamedQuery(name = "Cuenta.findByIsActiva", query = "SELECT c FROM Cuenta c WHERE c.isActiva = :isActiva")
    , @NamedQuery(name = "Cuenta.findByValcomision", query = "SELECT c FROM Cuenta c WHERE c.valcomision = :valcomision")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcuenta")
    private Integer idcuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "val_entradas")
    private double valEntradas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "val_salidas")
    private double valSalidas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "val_saldo")
    private double valSaldo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_activa")
    private boolean isActiva;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valcomision")
    private Double valcomision;
    @JoinColumn(name = "idbenefactor", referencedColumnName = "idbenefactor")
    @OneToOne(optional = false)
    private Benefactor idbenefactor;
    @JoinColumn(name = "idpasarela", referencedColumnName = "idpasarela")
    @ManyToOne(optional = false)
    private Pasarelapago idpasarela;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcuenta")
    private Collection<Cuentamovimiento> cuentamovimientoCollection;

    public Cuenta() {
    }

    public Cuenta(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public Cuenta(Integer idcuenta, double valEntradas, double valSalidas, double valSaldo, boolean isActiva) {
        this.idcuenta = idcuenta;
        this.valEntradas = valEntradas;
        this.valSalidas = valSalidas;
        this.valSaldo = valSaldo;
        this.isActiva = isActiva;
    }

    public Integer getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public double getValEntradas() {
        return valEntradas;
    }

    public void setValEntradas(double valEntradas) {
        this.valEntradas = valEntradas;
    }

    public double getValSalidas() {
        return valSalidas;
    }

    public void setValSalidas(double valSalidas) {
        this.valSalidas = valSalidas;
    }

    public double getValSaldo() {
        return valSaldo;
    }

    public void setValSaldo(double valSaldo) {
        this.valSaldo = valSaldo;
    }

    public boolean getIsActiva() {
        return isActiva;
    }

    public void setIsActiva(boolean isActiva) {
        this.isActiva = isActiva;
    }

    public Double getValcomision() {
        return valcomision;
    }

    public void setValcomision(Double valcomision) {
        this.valcomision = valcomision;
    }

    public Benefactor getIdbenefactor() {
        return idbenefactor;
    }

    public void setIdbenefactor(Benefactor idbenefactor) {
        this.idbenefactor = idbenefactor;
    }

    public Pasarelapago getIdpasarela() {
        return idpasarela;
    }

    public void setIdpasarela(Pasarelapago idpasarela) {
        this.idpasarela = idpasarela;
    }

    @XmlTransient
    public Collection<Cuentamovimiento> getCuentamovimientoCollection() {
        return cuentamovimientoCollection;
    }

    public void setCuentamovimientoCollection(Collection<Cuentamovimiento> cuentamovimientoCollection) {
        this.cuentamovimientoCollection = cuentamovimientoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcuenta != null ? idcuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.idcuenta == null && other.idcuenta != null) || (this.idcuenta != null && !this.idcuenta.equals(other.idcuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Cuenta[ idcuenta=" + idcuenta + " ]";
    }
    
}
