package entities;

import entities.Ejercicio;
import entities.Eps;
import entities.MedidasCorporales;
import entities.Servicio;
import entities.Telefono;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2016-10-05T22:38:47")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, Date> fechaNacimiento;
    public static volatile ListAttribute<Cliente, Telefono> telefonoList;
    public static volatile SingularAttribute<Cliente, String> direccion;
    public static volatile SingularAttribute<Cliente, Eps> eps;
    public static volatile ListAttribute<Cliente, Servicio> servicioList;
    public static volatile SingularAttribute<Cliente, String> nombre;
    public static volatile SingularAttribute<Cliente, Short> tipoSangre;
    public static volatile SingularAttribute<Cliente, Boolean> rh;
    public static volatile SingularAttribute<Cliente, String> apellido;
    public static volatile SingularAttribute<Cliente, Boolean> genero;
    public static volatile ListAttribute<Cliente, MedidasCorporales> medidasCorporalesList;
    public static volatile ListAttribute<Cliente, Ejercicio> ejercicioList;
    public static volatile SingularAttribute<Cliente, String> id;

}