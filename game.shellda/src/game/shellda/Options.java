package game.shellda;

public class Options {
	
	public 	static int LARGEUR_CARTE = 16;
	public static int HAUTEUR_CARTE = 9;
	
	public static int WIDTH = LARGEUR_CARTE*(48)+3;
	public static int HEIGHT = HAUTEUR_CARTE*(48) + 55 + 28; // + 55 pour l'affichage des FPS, etc
	
	public static int PROFONDEUR_ARBORESCENCE = 1;
	
	public static int PC_SPEED = 1000;
	
	public static float BoutonPlayScale=0.1F;
	public static float BoutonExitScale=0.05F;
	public static float BoutonOptionScale=1.5F;
	public static float LogoScale=2F;
	
	public static int DEGATS_VIRUS = 25;
	public static int CORROMPU_DEFAITE = 30;
	
	public static int DECALAGE_CORBEILLE = 3;
	
}
