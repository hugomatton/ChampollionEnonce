package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;


public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
	Intervention icm, itd, itp, int_java;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");	
		icm = new Intervention(new Date(), 10, 2, new Salle("101", 30), TypeIntervention.CM, untel, uml );	
		itd = new Intervention(new Date(), 10, 3, new Salle("101", 30), TypeIntervention.TD, untel, uml );
		itp = new Intervention(new Date(), 10, 4, new Salle("101", 30), TypeIntervention.TP, untel, uml );
		int_java = new Intervention(new Date(), 10, 4, new Salle("101", 30), TypeIntervention.TP, untel, java );
	}
	

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

                // 20h TD pour UML
                untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");		
		
	}

	@Test
	public void testHeurePrevu(){
		untel.ajouteEnseignement(uml, 0, 20, 0);
		untel.ajouteEnseignement(java, 0, 20, 0);
		assertEquals(40, untel.heuresPrevues());
	}

	@Test 
	public void testAjouterIntervention(){
		untel.ajouteEnseignement(uml, 20, 20, 20);
		untel.ajouterIntervention(icm);
		assertEquals(1, untel.gInterventions().size());

		try{
			untel.ajouterIntervention(int_java);
			fail("Une intervention de java n'est pas possible pour ce prof");
		}catch(Exception e){

		}
	}

	@Test
	public void testHeureAPlaniffiee(){
		untel.ajouteEnseignement(uml, 20, 20, 20);
		untel.ajouterIntervention(icm);
		untel.ajouterIntervention(itd);
		untel.ajouterIntervention(itp);
		assertEquals(18, untel.resteAPlanifier(uml, TypeIntervention.CM));
		assertEquals(17, untel.resteAPlanifier(uml, TypeIntervention.TD));
		assertEquals(16, untel.resteAPlanifier(uml, TypeIntervention.TP));

		try {
			//java n'est pas un ue pris enseigné par untel
			untel.resteAPlanifier(java, TypeIntervention.CM);
			fail();
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testEnSousService(){
		untel.ajouteEnseignement(uml, 10, 10, 10);
		untel.ajouterIntervention(icm);
		assertTrue(untel.enSousService());
	}
	
}
