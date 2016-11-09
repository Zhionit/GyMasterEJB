package entities;

import entities.Cliente;
import entities.ServicioPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2016-10-05T22:38:47")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile SingularAttribute<Servicio, String> descripcion;
    public static volatile SingularAttribute<Servicio, Cliente> cliente1;
    public static volatile SingularAttribute<Servicio, ServicioPK> servicioPK;

}