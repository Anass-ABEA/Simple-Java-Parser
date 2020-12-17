package Calculette;

public class Home_TableSymbole {

    public static void main(String[] args) {

        Table tableSym = new Table();

        tableSym.INIT();

        //tableSym.afficherListeS();
        //2 éléments avec la meme valeur apres le hachage
        System.out.println("Recheche de l'élément dans une table");
        //insertion et recherche

        tableSym.insert("ac"+"\0");
        tableSym.insert("cb"+"\0");

        tableSym.afficherListeS();

        resultat(tableSym.recherche("ac\0"));

    }
    private static void resultat(int recherche) {
        if(recherche==1){
            System.out.println("l'élément existe");
        }else{
            System.out.println("l'élément n'existe pas");
        }
    }
}
