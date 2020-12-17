package Calculette;
/**
 *
 * Classe ELEMENT qui est la structure
 * des éléments de la table de Symbole
 *
 * */
public class Element {
    String lexeme;
    boolean isMC;

    //fonction toString pour organiser l'affichage de la table
    @Override
    public String toString() {
        if (isMC){
            return "|"+lexeme+space(lexeme)+"| Mot cle | ";
        }else{
            return "|"+lexeme+space(lexeme)+"|    ID   |";
        }
    }

    //fonction d'espacement pour garder la forme bien organisé
    private String space(String lexeme) {
        int e = 15-lexeme.length();
        String str = "";
        for(int i = 0;i<e;i++){
            str+=" ";
        }
        return str;
    }
}
