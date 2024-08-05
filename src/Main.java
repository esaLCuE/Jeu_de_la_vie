import java.util.Objects;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static void montrerTableau(String[][] tab){
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void JDLV (String[][] tab, String[][] tab2, boolean ident){
        int tot;
        for (int i = 0; i < tab[0].length; i++) {
            for (int j = 0; j < tab.length; j++) {
                tot = 0;

                if (i>0) {
                    if (j>0) {
                        if (tab2[j - 1][i - 1].equals("■")) {
                            tot++;
                        }
                    }
                    if ((tab2[j][i - 1].equals("■"))){
                        tot++;
                    }
                    if (j<tab.length-1) {
                        if (tab2[j+1][i-1].equals("■")) {
                            tot++;
                        }
                    }
                }

                if (j>0){
                    if (tab2[j-1][i].equals("■")){
                        tot++;
                    }
                }
                if (j<tab.length-1){
                    if (tab2[j+1][i].equals("■")){
                        tot++;
                    }
                }

                if (i<tab[0].length-1){
                    if (j>0) {
                        if (tab2[j - 1][i + 1].equals("■")) {
                            tot++;
                        }
                    }
                    if ((tab2[j][i + 1].equals("■"))){
                        tot++;
                    }
                    if (j<tab.length-1) {
                        if (tab2[j+1][i+1].equals("■")) {
                            tot++;
                        }
                    }
                }

                if (Objects.equals(tab2[j][i], "■")){
                    if (tot>3 || tot <2){
                        tab[j][i] = "□";
                    }
                } else {
                    if (tot==3){
                        tab[j][i]= "■";
                    }
                }
            }
        }
        ident = true;
        for (int i = 0; i < tab2.length; i++) {
            for (int j = 0; j < tab2[0].length; j++) {
                if (!Objects.equals(tab2[i][j], tab[i][j])) {
                    ident = false;
                    tab2[i][j] = tab[i][j];
                }
            }
        }
        if (ident) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        String [][] tab = new String[25][50];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                tab[i][j] = "□";
            }
        }

        montrerTableau(tab);

        Scanner sc = new Scanner(System.in);
        int abs = -1;
        int ord = -1;
        boolean cont = true;
        System.out.println("Quelles cases rendre vivantes ?");
        while (cont) {
            String repCon = " ";
            do {
                System.out.println("Abscisse ?");
                abs = sc.nextInt();
            } while (abs<0 || abs>tab[0].length-1);

            do {
                System.out.println("Ordonnée ?");
                ord = sc.nextInt();
            } while (ord<0 || ord>tab.length-1);

            tab[ord][abs]="■";

            montrerTableau(tab);

            while (!(repCon.equalsIgnoreCase("oui") || repCon.equalsIgnoreCase("non"))) {
                System.out.println("En ajouter ?");
                repCon = sc.next();
                if (repCon.equalsIgnoreCase("non")) {
                    cont = false;
                }
            }
        }
        String [][]tab2 = new String[25][50];
        for (int i = 0; i < tab2.length; i++) {
            for (int j = 0; j < tab2[0].length; j++) {
                tab2[i][j]=tab[i][j];
            }
        }
        Timer timer = new Timer();
        boolean ident = false;
        do {
            JDLV(tab, tab2, ident);
            montrerTableau(tab2);
            System.out.println();
        } while (!ident);
        // IL FAUT IMPLEMENTER UN TIMER POUR POUVOIR SUIVRE ETAPE PAR ETAPE, MAIS A CHAQUE TENTATIVE
        // CELA CREE DES PROBLEMES DANS L'EXECUTION DU PROGRAMME (PAS D'ERREUR MAIS AFFICHAGE MODIFIE)
    }
}