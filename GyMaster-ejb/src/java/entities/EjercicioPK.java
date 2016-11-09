/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author zhion
 */
@Embeddable
public class EjercicioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CLIENTE")
    private String cliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA")
    private int dia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN_SECUENCIA")
    private int ordenSecuencia;

    public EjercicioPK() {
    }

    public EjercicioPK(String cliente, int dia, int ordenSecuencia) {
        this.cliente = cliente;
        this.dia = dia;
        this.ordenSecuencia = ordenSecuencia;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getOrdenSecuencia() {
        return ordenSecuencia;
    }

    public void setOrdenSecuencia(int ordenSecuencia) {
        this.ordenSecuencia = ordenSecuencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliente != null ? cliente.hashCode() : 0);
        hash += (int) dia;
        hash += (int) ordenSecuencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EjercicioPK)) {
            return false;
        }
        EjercicioPK other = (EjercicioPK) object;
        if ((this.cliente == null && other.cliente != null) || (this.cliente != null && !this.cliente.equals(other.cliente))) {
            return false;
        }
        if (this.dia != other.dia) {
            return false;
        }
        if (this.ordenSecuencia != other.ordenSecuencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EjercicioPK[ cliente=" + cliente + ", dia=" + dia + ", ordenSecuencia=" + ordenSecuencia + " ]";
    }
    
}
