package Calculette;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Table {
    final int  taille = 3079; //choix d'un nombre premier
    Element[] tableS= new Element[taille];

    //fonction d'affichage de la table de symbole
    public void afficherListeS(){
        for(int i = 0 ; i<tableS.length; i++){
            if (tableS[i]!=null)
                System.out.println(tableS[i]);
        }
    }

    //fonction de Hashage
    public int hashF(String lexeme){
        int i,cle=0;
        for(i=0;lexeme.charAt(i)!='\0';i++)
            cle=cle+lexeme.charAt(i)*(i+1);
        return cle % taille;

    }

    //fonction d'initialisation de la table de symboles
    public void INIT(){
        try {
            File fichier = new File("./src/Calculette/motscles.txt");
            Scanner myReader = new Scanner(fichier);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data=data+"\0";
                if(insertMC(data)==0){
                    System.out.println("CANNOT ADD *"+data+"* OVERFLOW\n try increasing the variable 'taille' in Table.java!!!");
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //fonction d'insertion des mots clés
    private int insertMC(String data) {
        int hashCode = hashF(data);
        if(this.tableS[hashCode]==null){
            tableS[hashCode] = new Element();
            tableS[hashCode].lexeme = data;
            tableS[hashCode].isMC = true;
            return 1;
        }else{
            int copie = hashCode;
            while(tableS[hashCode]!=null){
                hashCode= (hashCode+1)%taille;
                if(hashCode==copie){
                    return 0;
                }
            }
            tableS[hashCode] = new Element();
            tableS[hashCode].lexeme = data;
            tableS[hashCode].isMC = true;
            return 1;
        }
    }

    //fonction d'insertion des identificateurs
    public int insert(String token){
        int hashCode = hashF(token);
        if(this.tableS[hashCode]==null){
            tableS[hashCode] = new Element();
            tableS[hashCode].lexeme = token;
            tableS[hashCode].isMC = false;
            return 1;
        }else{
            int copie = hashCode;
            while(tableS[hashCode]!=null){
                if(tableS[hashCode].lexeme.equals(token))
                {
                    return -1;
                }
                hashCode= (hashCode+1)%taille;
                if(hashCode==copie){
                    return 0;
                }
            }
            tableS[hashCode] = new Element();
            tableS[hashCode].lexeme = token;
            tableS[hashCode].isMC = false;
            return 1;
        }
    }

    //fonction de recherche des lexemes dans la table de symboles
    public int recherche(String token){
        int hashCode = hashF(token);
        if (this.tableS[hashCode] == null){
            return 0;
        }
        int copie = hashCode;
        while(tableS[hashCode]!=null){
            if(tableS[hashCode].lexeme.equals(token)){
                return 1;
            }
            hashCode= (hashCode+1)%taille;
            if(hashCode==copie){
                return 0;
            }
        }
        return 0;
    }

    // fonction qui vérifie si le token est un mot clé ou identificateur
    public boolean isMc (String token){
        try{
            return tableS[hashF(token+"\0")].isMC;
        }
        catch (Exception e)
        {
            return false;
        }


    }

}
