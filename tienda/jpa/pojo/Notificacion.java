/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.jpa.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Desarrollador
 */
@Entity
@Table(name = "notificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n")
    , @NamedQuery(name = "Notificacion.findByIdnotificacion", query = "SELECT n FROM Notificacion n WHERE n.idnotificacion = :idnotificacion")
    , @NamedQuery(name = "Notificacion.findByConfigId", query = "SELECT n FROM Notificacion n WHERE n.configId = :configId")
    , @NamedQuery(name = "Notificacion.findByTipo", query = "SELECT n FROM Notificacion n WHERE n.tipo = :tipo")
    , @NamedQuery(name = "Notificacion.findByEmail", query = "SELECT n FROM Notificacion n WHERE n.email = :email")
    , @NamedQuery(name = "Notificacion.findByEmailAsunto", query = "SELECT n FROM Notificacion n WHERE n.emailAsunto = :emailAsunto")
    , @NamedQuery(name = "Notificacion.findByEmailContenido", query = "SELECT n FROM Notificacion n WHERE n.emailContenido = :emailContenido")
    , @NamedQuery(name = "Notificacion.findBySmsContent", query = "SELECT n FROM Notificacion n WHERE n.smsContent = :smsContent")
    , @NamedQuery(name = "Notificacion.findBySmsNumcel", query = "SELECT n FROM Notificacion n WHERE n.smsNumcel = :smsNumcel")
    , @NamedQuery(name = "Notificacion.findByCreatedAt", query = "SELECT n FROM Notificacion n WHERE n.createdAt = :createdAt")})
public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotificacion")
    private Long idnotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "config_id")
    private int configId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tipo")
    private String tipo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Size(max = 2147483647)
    @Column(name = "email_asunto")
    private String emailAsunto;
    @Size(max = 2147483647)
    @Column(name = "email_contenido")
    private String emailContenido;
    @Size(max = 2147483647)
    @Column(name = "sms_content")
    private String smsContent;
    @Column(name = "sms_numcel")
    private Long smsNumcel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Notificacion() {
    }

    public Notificacion(Long idnotificacion) {
        this.idnotificacion = idnotificacion;
    }

    public Notificacion(Long idnotificacion, int configId, String tipo, Date createdAt) {
        this.idnotificacion = idnotificacion;
        this.configId = configId;
        this.tipo = tipo;
        this.createdAt = createdAt;
    }

    public Long getIdnotificacion() {
        return idnotificacion;
    }

    public void setIdnotificacion(Long idnotificacion) {
        this.idnotificacion = idnotificacion;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailAsunto() {
        return emailAsunto;
    }

    public void setEmailAsunto(String emailAsunto) {
        this.emailAsunto = emailAsunto;
    }

    public String getEmailContenido() {
        return emailContenido;
    }

    public void setEmailContenido(String emailContenido) {
        this.emailContenido = emailContenido;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public Long getSmsNumcel() {
        return smsNumcel;
    }

    public void setSmsNumcel(Long smsNumcel) {
        this.smsNumcel = smsNumcel;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotificacion != null ? idnotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.idnotificacion == null && other.idnotificacion != null) || (this.idnotificacion != null && !this.idnotificacion.equals(other.idnotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tienda.jpa.pojo.Notificacion[ idnotificacion=" + idnotificacion + " ]";
    }
    
}
