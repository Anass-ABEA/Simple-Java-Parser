package Calculette.AL;
import Calculette.Element;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * Classe Traitement crée et initialise
 * La matrice de transition. Elle fait le traitement
 * et la gestion des liens verifiés par la matrice
 *
 * */

public class Traitement {
    String lexeme;
    public Traitement(String lexeme) {
        this.lexeme= lexeme;
    }

    //fonction qui initlaise la matrice de transition
    public int INIT(){
        int etat = 0;
        MatriceTrans matrice = new MatriceTrans();
        for(char c : lexeme.toCharArray()){
            etat = matrice.suivant(etat,c);
            if(etat <= 0){
                return -1;
            }
        }
        if(matrice.etatsFinaux.contains(etat)){
            //  System.out.println(lexeme + " CE LEXEME EST IDENTIFIE!!!\t");
            return etat;
        }
        return -1;
    }

    /**
     * Classe interne fait l'initialisation et le remplissage de la matrice
     * de transition ainsi que ses états
     * */
    static class MatriceTrans{
        static class MatrixElement{
            int li;
            int col;
            int next;
            public MatrixElement(int li, int col, int next) {
                this.li = li;
                this.col = col;
                this.next = next;
            }
        }
        ArrayList<Integer> etatsFinaux = new ArrayList<>();
        ArrayList<String>  transitions = new ArrayList<>();
        ArrayList<MatrixElement> matriceTrans = new ArrayList<>();

        public MatriceTrans(){
            matriceTrans.add(new MatrixElement(0,0,7));
            matriceTrans.add(new MatrixElement(0,1,5));
            matriceTrans.add(new MatrixElement(0,2,1));
            matriceTrans.add(new MatrixElement(0,3,4));
            matriceTrans.add(new MatrixElement(0,6,4));
            matriceTrans.add(new MatrixElement(0,7,0));
            matriceTrans.add(new MatrixElement(0,8,6));
            matriceTrans.add(new MatrixElement(1,2,2));
            matriceTrans.add(new MatrixElement(2,3,3));
            matriceTrans.add(new MatrixElement(0,0,7));

            matriceTrans.add(new MatrixElement(2,4,2));
            matriceTrans.add(new MatrixElement(3,2,0));
            matriceTrans.add(new MatrixElement(3,3,3));
            matriceTrans.add(new MatrixElement(5,1,5));
            matriceTrans.add(new MatrixElement(7,0,7));
            matriceTrans.add(new MatrixElement(7,1,7));

            ajout();


        }

        private void ajout() {
            etatsFinaux.add(3);
            etatsFinaux.add(4);
            etatsFinaux.add(5);
            etatsFinaux.add(6);
            etatsFinaux.add(7);
            etatsFinaux.add(1);
            String s = "abcdefghijklmnopqrstuvwxyz";
            s = s+s.toUpperCase();
            transitions.add(s);
            transitions.add("0123456789");
            transitions.add("/");
            transitions.add("*");
            transitions.add(s+"_/");
            transitions.add(s+"_");
            transitions.add("+-%");
            transitions.add(" ");
            transitions.add("=");
        }



        public int suivant(int etat, char c) {
            int index = -1;
            for (String s: transitions){
                index++;
                if (s.contains(String.valueOf(c))){
                    for (MatrixElement mel : this.matriceTrans){
                        if (mel.col==index && mel.li== etat){
                            return mel.next;
                        }
                    }
                }
            }

            return -1;
        }
    }

}
