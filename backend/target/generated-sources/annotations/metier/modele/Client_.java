package metier.modele;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Consultation;
import metier.modele.ProfilAstral;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-05-20T10:51:33", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Client.class)
public class Client_ extends Utilisateur_ {

    public static volatile SingularAttribute<Client, String> adresse_postale;
    public static volatile SingularAttribute<Client, ProfilAstral> profilAstral;
    public static volatile ListAttribute<Client, Consultation> consultations;

}