package controleur;

import modele.Coup;
import modele.Damier;
import modele.Joueur;
import vue.Ihm;

public class Controleur {
    private Ihm ihm;
     private Damier damier;

     private Joueur joueur1;
     private Joueur joueur2;

     private Joueur joueurCourant ;

     private Joueur joueurAdversaire;

    public Controleur (Ihm ihm){
        this.ihm = ihm;
        ihm.setControleur(this);
        this.damier = new Damier();
        ihm.saisirNomJoueur();
    }
    public String afficherDamier() {

        return damier.toString();
    }

    public void jouer() {
        damier.creerUnDamier();
        this.joueurCourant = joueur1;
        ihm.afficherDamier();
        do{
            ihm.demandeCoup(this.joueurCourant);
            ihm.afficherDamier();
            changerTourJoueur();
        }
        while(testJeuFini(joueurCourant));
        int pionNoir = damier.compterPionNoir();
        int pionBlanc = damier.compterPionBlanc();
        if((pionNoir - pionBlanc) > 0){
            joueur1.gagnerPartie();
            ihm.afficherGagneeUneParie(joueur1, pionNoir);
            ihm.afficherPerduAdversaire(joueur2, pionBlanc);
        }
        else if((pionNoir - pionBlanc) < 0){
            joueur2.gagnerPartie();
            ihm.afficherGagneeUneParie(joueur2, pionBlanc);
            ihm.afficherPerduAdversaire(joueur1, pionNoir);
        }
        else{
            joueur1.casEgaux();
            ihm.afficherEgaux();
        }
        ihm.demandeRejouer(joueur1, joueur2);

    }
    public Joueur changerTourJoueur(){
        if(this.joueurCourant == joueur1){
            this.joueurCourant = joueur2;
        }
        else if (this.joueurCourant == joueur2){
            this.joueurCourant = joueur1;
        }
        return joueurCourant;
    }
    public void nomJoueur(String nomJ1, String nomJ2) {
        this.joueur1= new Joueur(nomJ1,"\u26AB");
        this.joueur2 = new Joueur(nomJ2, "\u26AA");
    }

    public boolean gererCoup(int numColonne, char lettre, Joueur joueurCourant) {
        boolean testCoup = false;
        Coup new_coup = new Coup(numColonne, lettre);
        testCoup = damier.verifierCoup(new_coup, joueurCourant);
        if(testCoup == true){
        damier.effectuerCoup(new_coup, joueurCourant);
        }
        return testCoup;
    }

    public boolean testJeuFini(Joueur joueurCourant){
        Joueur j1 = joueurCourant;
        Joueur j2 = getAdversaire(joueurCourant);
        boolean test = false;
        test = damier.JeuFini(j1,j2);
        return test;
    }
    public Joueur getAdversaire(Joueur joueurCourant) {
        if(joueurCourant == joueur1){
            joueurAdversaire = joueur2;
        }
        else if (joueurCourant == joueur2){
            joueurAdversaire = joueur1;
        }
        return joueurAdversaire;

    }

}
