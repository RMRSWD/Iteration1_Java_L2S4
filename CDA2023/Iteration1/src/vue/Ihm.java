package vue;

import controleur.Controleur;
import modele.Joueur;

import java.util.Scanner;

public class Ihm {
    private Controleur controleur;
    public Ihm(){
        System.out.println("------------------------------\n" +
                           "Bienvenue dans le jeu Reversi\n" +
                           "------------------------------");
    }
    public void setControleur(Controleur controleur){
        this.controleur = controleur;
    }

    public void afficherDamier() {
        System.out.println(controleur.afficherDamier());
    }

    public void saisirNomJoueur() {
        Scanner sc = new Scanner(System.in);
        boolean testNom = true;
        while(testNom) {
            System.out.println("Entrer le nom du premier joueur: ");
            String nomJ1 = sc.nextLine();
            System.out.println("Entrer le nom deuxième joueur: ");
            String nomJ2 = sc.nextLine();
            if(!nomJ1.equals("") && !nomJ2.equals("") && !nomJ1.equals(nomJ2)) {
                controleur.nomJoueur(nomJ1, nomJ2);
                testNom = false;
            }
            else{
                System.out.println("Veuillez re-saisir le nom du joueur!");
            }
        }
    }
    public void demandeCoup(Joueur joueur){
        Scanner sc = new Scanner(System.in);
        Joueur joueurCourant = joueur;
        String input;
        int numColonne;
        char lettre;
        boolean a = true;
        while (a) {
            System.out.println(joueurCourant.getNomJ() + joueurCourant.getCouleur() + " à vous de jouer. Saisir une ligne entre 1 et 8 suivie d'une lettre entre A et H (ex: 3D ) ou P pour passer son tour.");
            input = sc.nextLine();
            if (input.equalsIgnoreCase("P")) {
                System.out.println("Tour passé");
                break;
            }
            if (input.length() != 2) {
                System.out.println("Entrée non valide veuillez réessayer.");
                continue;
            }
            numColonne = input.charAt(0) -'0'; // '0'=48
            lettre = input.charAt(1);


            if (numColonne < 1 || numColonne > 8) {
                System.out.println("Entrée non valide, veuillez réessayer.");
                continue;
            }
            if (lettre < 'A' || lettre > 'H') {
                System.out.println("Entrée non valide, veuillez réessayer.");
                continue;
            }

            if(controleur.gererCoup(numColonne, lettre,joueurCourant)){
                break;
            }
            else{
                System.out.println("Veuillez re_saisir une position correcte.");
            }
        }
    }
    public void demandeRejouer(Joueur j1, Joueur j2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Votre partie est fini. Tapez vous \"r\" pour rejouer ou \"q\" pour quiter le jeu.");
        String rejouer = sc.nextLine();
        if(rejouer.equalsIgnoreCase("r")){
            controleur.jouer();
        }
        else{
            afficherScoreFinal(j1, j2);
        }
    }
    public void afficherGagneeUneParie(Joueur joueur,int nbPion){
        System.out.println(joueur.getNomJ()+ " : a gagné!" + " ; Le nombre de pions que vous avez: "+ nbPion);
    }
    public void afficherScoreFinal( Joueur j1, Joueur j2){
        System.out.println("Nombre partie(s) gagnée(s) " +j1.getNomJ() + ": "+ j1.getNbPartiesGagnees() );
        System.out.println("Nombre partie(s) gagnée(s) "+ j2.getNomJ() + ": "+ j2.getNbPartiesGagnees());
        System.out.println("Nombre partie(s) égal(aux): " +j1.getNbPartiesEgaux());
        System.out.println("Aurevoir!!!");
        System.out.println("L'application fait par l'équipe TPF avec: "+"\n 1: Vu The Duc" + "\n 2: Vu Ngoc Hai" + "\n 3: Nordine Seffar");
    }

    public void afficherEgaux() {
        System.out.println("ex aequo");
    }

    public void afficherPerduAdversaire(Joueur joueur, int nbPion) {
        System.out.println(joueur.getNomJ()+ " : a perdu!" + " ; Le nombre de pions que vous avez: "+ nbPion);
    }
}





