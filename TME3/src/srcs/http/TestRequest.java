package src.srcs.http;

public class TestRequest {

	public static void main(String[] args) {
		Serveur s= new Serveur(8080, new ReadLineSocket());
		s.start();
		System.out.println("helloooo");
		
		//2. Le header d'une requête HTTP est affichée. One ne peut pas savoir si le client finit de transmettre ses informations
		// car pour l'instant le serveur ne traite pas proprement les requetes client
		//3. Soit on ferme l'onglet, soit on envoie une nouvelle requête au serveur.  
		
		//question 5 - à faire sur la machine personnel ou si on fait sur ppti il faut alors faire avec url de ppti 
	}

}
