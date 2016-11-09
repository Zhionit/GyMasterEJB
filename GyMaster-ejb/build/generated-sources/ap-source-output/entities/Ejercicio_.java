package entities;

import entities.Cliente;
import entities.EjercicioPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2016-10-05T22:38:47")
@StaticMetamodel(Ejercicio.class)
public class Ejercicio_ { 

    public static volatile SingularAttribute<Ejercicio, String> descripcion;
    public static volatile SingularAttribute<Ejercicio, Cliente> cliente1;
    public static volatile SingularAttribute<Ejercicio, Integer> peso;
    public static volatile SingularAttribute<Ejercicio, Integer> series;
    public static volatile SingularAttribute<Ejercicio, Integer> repeticiones;
    public static volatile SingularAttribute<Ejercicio, EjercicioPK> ejercicioPK;

}