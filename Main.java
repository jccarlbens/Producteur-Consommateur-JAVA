/**
 * Modèle Producteur-Consommateur avec synchronisation Java
 *  wait(), notify(), notifyAll(), synchronized
 * 
 *  - orchestration des threads
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        final int CAPACITE_BUFFER = 5;
        final int NB_ELEMENTS     = 8;

        Buffer buffer = new Buffer(CAPACITE_BUFFER);

        System.out.println("=== Démarrage Producteur-Consommateur ===");
        System.out.println("Capacité buffer : " + CAPACITE_BUFFER);
        System.out.println("Éléments par acteur : " + NB_ELEMENTS);
        System.out.println("=========================================\n");

        // Création des threads
        Thread p1 = new Thread(new Producteur(buffer, "Producteur-1", NB_ELEMENTS));
        Thread p2 = new Thread(new Producteur(buffer, "Producteur-2", NB_ELEMENTS));
        Thread c1 = new Thread(new Consommateur(buffer, "Consommateur-1", NB_ELEMENTS));
        Thread c2 = new Thread(new Consommateur(buffer, "Consommateur-2", NB_ELEMENTS));

        // Démarrage simultané
        p1.start();
        p2.start();
        c1.start();
        c2.start();

        // Attendre la fin de tous les threads
        p1.join();
        p2.join();
        c1.join();
        c2.join();

        System.out.println("\n=== Fin — Buffer final : " + buffer.taille() + " élément(s) ===");
    }
}