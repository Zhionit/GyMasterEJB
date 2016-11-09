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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zhion
 */
@Entity
@Table(name = "medidas_corporales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedidasCorporales.findAll", query = "SELECT m FROM MedidasCorporales m"),
    @NamedQuery(name = "MedidasCorporales.findByCliente", query = "SELECT m FROM MedidasCorporales m WHERE m.medidasCorporalesPK.cliente = :cliente"),
    @NamedQuery(name = "MedidasCorporales.findByFechaRegistro", query = "SELECT m FROM MedidasCorporales m WHERE m.medidasCorporalesPK.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "MedidasCorporales.findByPeso", query = "SELECT m FROM MedidasCorporales m WHERE m.peso = :peso"),
    @NamedQuery(name = "MedidasCorporales.findByEstatura", query = "SELECT m FROM MedidasCorporales m WHERE m.estatura = :estatura"),
    @NamedQuery(name = "MedidasCorporales.findByCintura", query = "SELECT m FROM MedidasCorporales m WHERE m.cintura = :cintura"),
    @NamedQuery(name = "MedidasCorporales.findByCadera", query = "SELECT m FROM MedidasCorporales m WHERE m.cadera = :cadera"),
    @NamedQuery(name = "MedidasCorporales.findByPiernaI", query = "SELECT m FROM MedidasCorporales m WHERE m.piernaI = :piernaI"),
    @NamedQuery(name = "MedidasCorporales.findByPiernaD", query = "SELECT m FROM MedidasCorporales m WHERE m.piernaD = :piernaD"),
    @NamedQuery(name = "MedidasCorporales.findByBrazoI", query = "SELECT m FROM MedidasCorporales m WHERE m.brazoI = :brazoI"),
    @NamedQuery(name = "MedidasCorporales.findByBrazoD", query = "SELECT m FROM MedidasCorporales m WHERE m.brazoD = :brazoD"),
    @NamedQuery(name = "MedidasCorporales.findByGluteoI", query = "SELECT m FROM MedidasCorporales m WHERE m.gluteoI = :gluteoI"),
    @NamedQuery(name = "MedidasCorporales.findByGluteoD", query = "SELECT m FROM MedidasCorporales m WHERE m.gluteoD = :gluteoD"),
    @NamedQuery(name = "MedidasCorporales.findByCuello", query = "SELECT m FROM MedidasCorporales m WHERE m.cuello = :cuello"),
    @NamedQuery(name = "MedidasCorporales.findByHombroI", query = "SELECT m FROM MedidasCorporales m WHERE m.hombroI = :hombroI"),
    @NamedQuery(name = "MedidasCorporales.findByHombroD", query = "SELECT m FROM MedidasCorporales m WHERE m.hombroD = :hombroD"),
    @NamedQuery(name = "MedidasCorporales.findByEspalda", query = "SELECT m FROM MedidasCorporales m WHERE m.espalda = :espalda")})
public class MedidasCorporales implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MedidasCorporalesPK medidasCorporalesPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PESO")
    private long peso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATURA")
    private long estatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CINTURA")
    private long cintura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CADERA")
    private long cadera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PIERNA_I")
    private long piernaI;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PIERNA_D")
    private long piernaD;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BRAZO_I")
    private long brazoI;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BRAZO_D")
    private long brazoD;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GLUTEO_I")
    private long gluteoI;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GLUTEO_D")
    private long gluteoD;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUELLO")
    private long cuello;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HOMBRO_I")
    private long hombroI;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HOMBRO_D")
    private long hombroD;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESPALDA")
    private long espalda;
    @JoinColumn(name = "CLIENTE", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente1;

    public MedidasCorporales() {
    }

    public MedidasCorporales(MedidasCorporalesPK medidasCorporalesPK) {
        this.medidasCorporalesPK = medidasCorporalesPK;
    }

    public MedidasCorporales(MedidasCorporalesPK medidasCorporalesPK, long peso, long estatura, long cintura, long cadera, long piernaI, long piernaD, long brazoI, long brazoD, long gluteoI, long gluteoD, long cuello, long hombroI, long hombroD, long espalda) {
        this.medidasCorporalesPK = medidasCorporalesPK;
        this.peso = peso;
        this.estatura = estatura;
        this.cintura = cintura;
        this.cadera = cadera;
        this.piernaI = piernaI;
        this.piernaD = piernaD;
        this.brazoI = brazoI;
        this.brazoD = brazoD;
        this.gluteoI = gluteoI;
        this.gluteoD = gluteoD;
        this.cuello = cuello;
        this.hombroI = hombroI;
        this.hombroD = hombroD;
        this.espalda = espalda;
    }

    public MedidasCorporales(String cliente, Date fechaRegistro) {
        this.medidasCorporalesPK = new MedidasCorporalesPK(cliente, fechaRegistro);
    }

    public MedidasCorporalesPK getMedidasCorporalesPK() {
        return medidasCorporalesPK;
    }

    public void setMedidasCorporalesPK(MedidasCorporalesPK medidasCorporalesPK) {
        this.medidasCorporalesPK = medidasCorporalesPK;
    }

    public long getPeso() {
        return peso;
    }

    public void setPeso(long peso) {
        this.peso = peso;
    }

    public long getEstatura() {
        return estatura;
    }

    public void setEstatura(long estatura) {
        this.estatura = estatura;
    }

    public long getCintura() {
        return cintura;
    }

    public void setCintura(long cintura) {
        this.cintura = cintura;
    }

    public long getCadera() {
        return cadera;
    }

    public void setCadera(long cadera) {
        this.cadera = cadera;
    }

    public long getPiernaI() {
        return piernaI;
    }

    public void setPiernaI(long piernaI) {
        this.piernaI = piernaI;
    }

    public long getPiernaD() {
        return piernaD;
    }

    public void setPiernaD(long piernaD) {
        this.piernaD = piernaD;
    }

    public long getBrazoI() {
        return brazoI;
    }

    public void setBrazoI(long brazoI) {
        this.brazoI = brazoI;
    }

    public long getBrazoD() {
        return brazoD;
    }

    public void setBrazoD(long brazoD) {
        this.brazoD = brazoD;
    }

    public long getGluteoI() {
        return gluteoI;
    }

    public void setGluteoI(long gluteoI) {
        this.gluteoI = gluteoI;
    }

    public long getGluteoD() {
        return gluteoD;
    }

    public void setGluteoD(long gluteoD) {
        this.gluteoD = gluteoD;
    }

    public long getCuello() {
        return cuello;
    }

    public void setCuello(long cuello) {
        this.cuello = cuello;
    }

    public long getHombroI() {
        return hombroI;
    }

    public void setHombroI(long hombroI) {
        this.hombroI = hombroI;
    }

    public long getHombroD() {
        return hombroD;
    }

    public void setHombroD(long hombroD) {
        this.hombroD = hombroD;
    }

    public long getEspalda() {
        return espalda;
    }

    public void setEspalda(long espalda) {
        this.espalda = espalda;
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
        hash += (medidasCorporalesPK != null ? medidasCorporalesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedidasCorporales)) {
            return false;
        }
        MedidasCorporales other = (MedidasCorporales) object;
        if ((this.medidasCorporalesPK == null && other.medidasCorporalesPK != null) || (this.medidasCorporalesPK != null && !this.medidasCorporalesPK.equals(other.medidasCorporalesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MedidasCorporales[ medidasCorporalesPK=" + medidasCorporalesPK + " ]";
    }
    
}
