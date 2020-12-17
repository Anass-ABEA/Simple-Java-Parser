package Calculette.AL;

public class Home_Calculette {
    public static void main(String[] args) {
        //changer l'emplacement du fichier code.txt      (contenant le code)
        //changer l'emplacement du fichier motscles.txt  (contenant les mots clés) dans la classe Table.
        Analyser AL = new Analyser("./src/Calculette/AL/code.txt");
        AL.COMPILE();

        //supprimer le commentaire pour afficher  table de symbole
        // System.out.println("\n\n***AFFICHAGE DE LA TABLE DES SYMBOLES******\n\n");
        // AL.tableS.afficherListeS();

        //initialiser une variable deux fois pour tester l'erreur d'initialisation.

    }
}
