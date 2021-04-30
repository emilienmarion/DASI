package metier.modele;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Consultation;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-04-30T17:59:05", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Employe.class)
public class Employe_ extends Utilisateur_ {

    public static volatile SingularAttribute<Employe, Boolean> statut_en_ligne;
    public static volatile SingularAttribute<Employe, Integer> version;
    public static volatile ListAttribute<Employe, Consultation> consultations;
    public static volatile SingularAttribute<Employe, Integer> nb_consultations;

}