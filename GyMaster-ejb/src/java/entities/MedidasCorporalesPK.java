/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author zhion
 */
@Embeddable
public class MedidasCorporalesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CLIENTE")
    private String cliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    public MedidasCorporalesPK() {
    }

    public MedidasCorporalesPK(String cliente, Date fechaRegistro) {
        this.cliente = cliente;
        this.fechaRegistro = fechaRegistro;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliente != null ? cliente.hashCode() : 0);
        hash += (fechaRegistro != null ? fechaRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedidasCorporalesPK)) {
            return false;
        }
        MedidasCorporalesPK other = (MedidasCorporalesPK) object;
        if ((this.cliente == null && other.cliente != null) || (this.cliente != null && !this.cliente.equals(other.cliente))) {
            return false;
        }
        if ((this.fechaRegistro == null && other.fechaRegistro != null) || (this.fechaRegistro != null && !this.fechaRegistro.equals(other.fechaRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MedidasCorporalesPK[ cliente=" + cliente + ", fechaRegistro=" + fechaRegistro + " ]";
    }
    
}
