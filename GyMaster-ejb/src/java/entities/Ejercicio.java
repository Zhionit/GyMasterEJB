/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author zhion
 */
@Entity
@Table(name = "ejercicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ejercicio.findAll", query = "SELECT e FROM Ejercicio e"),
    @NamedQuery(name = "Ejercicio.findByCliente", query = "SELECT e FROM Ejercicio e WHERE e.ejercicioPK.cliente = :cliente"),
    @NamedQuery(name = "Ejercicio.findByDia", query = "SELECT e FROM Ejercicio e WHERE e.ejercicioPK.dia = :dia"),
    @NamedQuery(name = "Ejercicio.findByOrdenSecuencia", query = "SELECT e FROM Ejercicio e WHERE e.ejercicioPK.ordenSecuencia = :ordenSecuencia"),
    @NamedQuery(name = "Ejercicio.findByDescripcion", query = "SELECT e FROM Ejercicio e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Ejercicio.findBySeries", query = "SELECT e FROM Ejercicio e WHERE e.series = :series"),
    @NamedQuery(name = "Ejercicio.findByRepeticiones", query = "SELECT e FROM Ejercicio e WHERE e.repeticiones = :repeticiones"),
    @NamedQuery(name = "Ejercicio.findByPeso", query = "SELECT e FROM Ejercicio e WHERE e.peso = :peso")})
public class Ejercicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EjercicioPK ejercicioPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SERIES")
    private int series;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPETICIONES")
    private int repeticiones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PESO")
    private int peso;
    @JoinColumn(name = "CLIENTE", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente1;

    public Ejercicio() {
    }

    public Ejercicio(EjercicioPK ejercicioPK) {
        this.ejercicioPK = ejercicioPK;
    }

    public Ejercicio(EjercicioPK ejercicioPK, String descripcion, int series, int repeticiones, int peso) {
        this.ejercicioPK = ejercicioPK;
        this.descripcion = descripcion;
        this.series = series;
        this.repeticiones = repeticiones;
        this.peso = peso;
    }

    public Ejercicio(String cliente, int dia, int ordenSecuencia) {
        this.ejercicioPK = new EjercicioPK(cliente, dia, ordenSecuencia);
    }

    public EjercicioPK getEjercicioPK() {
        return ejercicioPK;
    }

    public void setEjercicioPK(EjercicioPK ejercicioPK) {
        this.ejercicioPK = ejercicioPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ejercicioPK != null ? ejercicioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejercicio)) {
            return false;
        }
        Ejercicio other = (Ejercicio) object;
        if ((this.ejercicioPK == null && other.ejercicioPK != null) || (this.ejercicioPK != null && !this.ejercicioPK.equals(other.ejercicioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ejercicio[ ejercicioPK=" + ejercicioPK + " ]";
    }
    
}
