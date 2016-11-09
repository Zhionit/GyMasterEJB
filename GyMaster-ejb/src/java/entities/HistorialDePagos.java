/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zhion
 */
@Entity
@Table(name = "historial_de_pagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialDePagos.findAll", query = "SELECT h FROM HistorialDePagos h"),
    @NamedQuery(name = "HistorialDePagos.findByCedula", query = "SELECT h FROM HistorialDePagos h WHERE h.historialDePagosPK.cedula = :cedula"),
    @NamedQuery(name = "HistorialDePagos.findByAnio", query = "SELECT h FROM HistorialDePagos h WHERE h.historialDePagosPK.anio = :anio"),
    @NamedQuery(name = "HistorialDePagos.findByMes", query = "SELECT h FROM HistorialDePagos h WHERE h.historialDePagosPK.mes = :mes"),
    @NamedQuery(name = "HistorialDePagos.findByValor", query = "SELECT h FROM HistorialDePagos h WHERE h.valor = :valor")})
public class HistorialDePagos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistorialDePagosPK historialDePagosPK;
    @Column(name = "valor")
    private Integer valor;

    public HistorialDePagos() {
    }

    public HistorialDePagos(HistorialDePagosPK historialDePagosPK) {
        this.historialDePagosPK = historialDePagosPK;
    }

    public HistorialDePagos(int cedula, int anio, int mes) {
        this.historialDePagosPK = new HistorialDePagosPK(cedula, anio, mes);
    }

    public HistorialDePagosPK getHistorialDePagosPK() {
        return historialDePagosPK;
    }

    public void setHistorialDePagosPK(HistorialDePagosPK historialDePagosPK) {
        this.historialDePagosPK = historialDePagosPK;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historialDePagosPK != null ? historialDePagosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialDePagos)) {
            return false;
        }
        HistorialDePagos other = (HistorialDePagos) object;
        if ((this.historialDePagosPK == null && other.historialDePagosPK != null) || (this.historialDePagosPK != null && !this.historialDePagosPK.equals(other.historialDePagosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.HistorialDePagos[ historialDePagosPK=" + historialDePagosPK + " ]";
    }
    
}
