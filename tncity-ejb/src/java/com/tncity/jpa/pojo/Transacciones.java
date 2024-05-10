/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.pojo;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guiovanny
 */
@Entity
@Table(name = "transacciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transacciones.findAll", query = "SELECT t FROM Transacciones t")
    , @NamedQuery(name = "Transacciones.findByIdtransaccion", query = "SELECT t FROM Transacciones t WHERE t.idtransaccion = :idtransaccion")
    , @NamedQuery(name = "Transacciones.findByCodtransaccion", query = "SELECT t FROM Transacciones t WHERE t.codtransaccion = :codtransaccion")
    , @NamedQuery(name = "Transacciones.findByFormapago", query = "SELECT t FROM Transacciones t WHERE t.formapago = :formapago")
    , @NamedQuery(name = "Transacciones.findByFranquicia", query = "SELECT t FROM Transacciones t WHERE t.franquicia = :franquicia")
    , @NamedQuery(name = "Transacciones.findByDescripcion", query = "SELECT t FROM Transacciones t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "Transacciones.findByReferencia1", query = "SELECT t FROM Transacciones t WHERE t.referencia1 = :referencia1")
    , @NamedQuery(name = "Transacciones.findByFechapago", query = "SELECT t FROM Transacciones t WHERE t.fechapago = :fechapago")
    , @NamedQuery(name = "Transacciones.findByNumerorecibo", query = "SELECT t FROM Transacciones t WHERE t.numerorecibo = :numerorecibo")
    , @NamedQuery(name = "Transacciones.findByCodigo", query = "SELECT t FROM Transacciones t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "Transacciones.findByMensajeerror", query = "SELECT t FROM Transacciones t WHERE t.mensajeerror = :mensajeerror")
    , @NamedQuery(name = "Transacciones.findByCodigoint", query = "SELECT t FROM Transacciones t WHERE t.codigoint = :codigoint")})
public class Transacciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtransaccion")
    private Long idtransaccion;
    @Size(max = 2147483647)
    @Column(name = "codtransaccion")
    private String codtransaccion;
    @Size(max = 2147483647)
    @Column(name = "formapago")
    private String formapago;
    @Size(max = 2147483647)
    @Column(name = "franquicia")
    private String franquicia;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 2147483647)
    @Column(name = "referencia1")
    private String referencia1;
    @Size(max = 2147483647)
    @Column(name = "fechapago")
    private String fechapago;
    @Size(max = 2147483647)
    @Column(name = "numerorecibo")
    private String numerorecibo;
    @Column(name = "codigo")
    private BigInteger codigo;
    @Size(max = 2147483647)
    @Column(name = "mensajeerror")
    private String mensajeerror;
    @Size(max = 2147483647)
    @Column(name = "codigoint")
    private String codigoint;
    @JoinColumn(name = "idrecaudo", referencedColumnName = "idrecaudo")
    @ManyToOne(optional = false)
    private Recaudo idrecaudo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaccionid")
    private Collection<Transmensajetexto> transmensajetextoCollection;

    public Transacciones() {
    }

    public Transacciones(Long idtransaccion) {
        this.idtransaccion = idtransaccion;
    }

    public Long getIdtransaccion() {
        return idtransaccion;
    }

    public void setIdtransaccion(Long idtransaccion) {
        this.idtransaccion = idtransaccion;
    }

    public String getCodtransaccion() {
        return codtransaccion;
    }

    public void setCodtransaccion(String codtransaccion) {
        this.codtransaccion = codtransaccion;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public String getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia1() {
        return referencia1;
    }

    public void setReferencia1(String referencia1) {
        this.referencia1 = referencia1;
    }

    public String getFechapago() {
        return fechapago;
    }

    public void setFechapago(String fechapago) {
        this.fechapago = fechapago;
    }

    public String getNumerorecibo() {
        return numerorecibo;
    }

    public void setNumerorecibo(String numerorecibo) {
        this.numerorecibo = numerorecibo;
    }

    public BigInteger getCodigo() {
        return codigo;
    }

    public void setCodigo(BigInteger codigo) {
        this.codigo = codigo;
    }

    public String getMensajeerror() {
        return mensajeerror;
    }

    public void setMensajeerror(String mensajeerror) {
        this.mensajeerror = mensajeerror;
    }

    public String getCodigoint() {
        return codigoint;
    }

    public void setCodigoint(String codigoint) {
        this.codigoint = codigoint;
    }

    public Recaudo getIdrecaudo() {
        return idrecaudo;
    }

    public void setIdrecaudo(Recaudo idrecaudo) {
        this.idrecaudo = idrecaudo;
    }

    @XmlTransient
    public Collection<Transmensajetexto> getTransmensajetextoCollection() {
        return transmensajetextoCollection;
    }

    public void setTransmensajetextoCollection(Collection<Transmensajetexto> transmensajetextoCollection) {
        this.transmensajetextoCollection = transmensajetextoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtransaccion != null ? idtransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transacciones)) {
            return false;
        }
        Transacciones other = (Transacciones) object;
        if ((this.idtransaccion == null && other.idtransaccion != null) || (this.idtransaccion != null && !this.idtransaccion.equals(other.idtransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Transacciones[ idtransaccion=" + idtransaccion + " ]";
    }
    
}
