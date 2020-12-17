package Calculette.AL;
import Calculette.Table;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Analyser {
    File code;
    Scanner myReader;
    Table tableS;
    String previous="";
    ArrayList <String> liste = new ArrayList<>();
    int mligne= 0;

    // constructeur qui initlaise la table de symbole et le code à compiler
    // elle initialise aussi la variable 'liste' qui seras plustard utile
    // dans la detection d'erreur dans la fonction traiter(lexeme)
    Analyser(String PATH){
        code = new File(PATH);
        tableS  = new Table();
        tableS.INIT();
        liste.add("int");
        liste.add("float");
        liste.add("bool");
        liste.add("double");
        liste.add("long");
        liste.add("short");
        liste.add("unsigned");
        liste.add("signed");
        liste.add("const");
        liste.add("char");

    }

    //fonction qui lit le code ligne par ligne puis mot par mot
    //et saute les commentaires et appelle la fonction traiter(lexeme)
    //pour 'traiter' le lexeme.
    void COMPILE(){
        try {
            myReader = new Scanner(code);
            while (myReader.hasNextLine()) {
                mligne++;
                String line = myReader.nextLine()+"\n";
                StringBuilder chaine = new StringBuilder();
                boolean skip = false;
                char pprecedent =' ';
                char precedent =' ';
                for(char c : line.toCharArray()){
                    if (precedent=='/' && c == '*'){
                        skip= true;
                    }
                    if (c == '/'){
                        if(precedent=='*'){
                            skip= false;
                        }
                    }
                    if(!skip){
                        if((c==' ' || c== '\n')&& precedent !='/'){
                            traiter(chaine);
                            chaine= new StringBuilder();
                            skip = false;
                            precedent = ' ';
                            pprecedent=' ';
                        }else{
                            if (precedent == '/') {
                                if(pprecedent=='*'){
                                    chaine.append(c);
                                }else{
                                    traiter(chaine);
                                    chaine= new StringBuilder();
                                    skip = false;
                                    precedent = ' ';
                                    pprecedent=' ';
                                }
                            }else{
                                chaine.append(c);
                            }
                        }
                    }
                    pprecedent = precedent;
                    precedent= c;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //fonction qui traite les tokens  et vérifie si le l'identificateur n'a pas été déjà initialisé
    //Si l'identificateur a été initialisé elle retourne une erreur
    private void traiter(StringBuilder chaine){
        Traitement t = new Traitement (chaine.toString());
        int indice = t.INIT();
        switch(indice){
            case -1 :
                break;
            case 7:

                if (!tableS.isMc(""+chaine)){
                    System.out.println(""+chaine+space(""+chaine)+"IDENTIFICATEUR");
                    int res=tableS.insert(""+chaine+"\0");
                    if (res==-1)
                    {
                        if (liste.contains(previous))
                        {
                            try {
                                throw new Exception("ligne : "+mligne+" Variable "+chaine+" deja initialisée");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                else{
                    System.out.println(""+chaine+space(""+chaine)+"Mot Cle");

                }
                break;
            default:
                System.out.println(""+chaine+space(""+chaine)+afficherType(indice));
                break;
        }
        previous=""+chaine;
    }

    //fonction qui organise l'affichage
    private String space(String lexeme) {
        int e = 10-lexeme.length();
        String str = "";
        for(int i = 0;i<e;i++){
            str+=" ";
        }
        return str;
    }

    //fonction d'affichage du type de lexeme
    private String afficherType(int indice) {
         switch (indice) {
            case 6 : return "OPERATEUR = ";
            case 5 : return "SUITE DE NUMEROS";
            case 4 : return "OPERATEUR";
            case 1 : return "OPERATEUR";
            default : return "";
        }
    }
}
