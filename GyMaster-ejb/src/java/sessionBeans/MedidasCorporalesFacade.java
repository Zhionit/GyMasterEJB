/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionBeans;

import entities.MedidasCorporales;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zhion
 */
@Stateless
public class MedidasCorporalesFacade extends AbstractFacade<MedidasCorporales> {
    @PersistenceContext(unitName = "GyMaster-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedidasCorporalesFacade() {
        super(MedidasCorporales.class);
    }
    
}
