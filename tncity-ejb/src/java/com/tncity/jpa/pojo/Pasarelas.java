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
@Table(name = "pasarelas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pasarelas.findAll", query = "SELECT p FROM Pasarelas p")
    , @NamedQuery(name = "Pasarelas.findByConfigidpasarela", query = "SELECT p FROM Pasarelas p WHERE p.configidpasarela = :configidpasarela")
    , @NamedQuery(name = "Pasarelas.findByConfigId", query = "SELECT p FROM Pasarelas p WHERE p.configId = :configId")
    , @NamedQuery(name = "Pasarelas.findByTipo", query = "SELECT p FROM Pasarelas p WHERE p.tipo = :tipo")
    , @NamedQuery(name = "Pasarelas.findByNombre", query = "SELECT p FROM Pasarelas p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Pasarelas.findByPuerto", query = "SELECT p FROM Pasarelas p WHERE p.puerto = :puerto")
    , @NamedQuery(name = "Pasarelas.findByHost", query = "SELECT p FROM Pasarelas p WHERE p.host = :host")
    , @NamedQuery(name = "Pasarelas.findByProtocolo", query = "SELECT p FROM Pasarelas p WHERE p.protocolo = :protocolo")})
public class Pasarelas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "configidpasarela")
    private Long configidpasarela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "config_id")
    private int configId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "puerto")
    private String puerto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "host")
    private String host;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "protocolo")
    private String protocolo;

    public Pasarelas() {
    }

    public Pasarelas(Long configidpasarela) {
        this.configidpasarela = configidpasarela;
    }

    public Pasarelas(Long configidpasarela, int configId, String tipo, String nombre, String puerto, String host, String protocolo) {
        this.configidpasarela = configidpasarela;
        this.configId = configId;
        this.tipo = tipo;
        this.nombre = nombre;
        this.puerto = puerto;
        this.host = host;
        this.protocolo = protocolo;
    }

    public Long getConfigidpasarela() {
        return configidpasarela;
    }

    public void setConfigidpasarela(Long configidpasarela) {
        this.configidpasarela = configidpasarela;
    }

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (configidpasarela != null ? configidpasarela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pasarelas)) {
            return false;
        }
        Pasarelas other = (Pasarelas) object;
        if ((this.configidpasarela == null && other.configidpasarela != null) || (this.configidpasarela != null && !this.configidpasarela.equals(other.configidpasarela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.Pasarelas[ configidpasarela=" + configidpasarela + " ]";
    }
    
}
