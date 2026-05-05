import java.util.Random;

/**
 * Producteur - Thread qui ajoute des éléments au buffer
 */
public class Producteur implements Runnable {//implémente l'interface Runnable pour pouvoir être exécuté dans un thread
    private final Buffer buffer;
    private final String nom;
    private final int nbElements;
    private final Random random = new Random();

    public Producteur(Buffer buffer, String nom, int nbElements) {
        this.buffer = buffer;
        this.nom = nom;
        this.nbElements = nbElements;
    }//constructeur qui initialise les champs du producteur

    @Override
    public void run() {
        try {
            for (int i = 1; i <= nbElements; i++) {
                int valeur = random.nextInt(100);//on choisit les valeurs aléatoirement entre 0 et 99
                buffer.produire(valeur, nom);
                // Simule un temps de production variable
                Thread.sleep(random.nextInt(500) + 100); // pause entre 100ms et 00ms
            }
            System.out.println("[" + nom + "] Production terminée.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[" + nom + "] Interrompu.");//si le thread est interrompu, on affiche un message et on termine proprement
        }
    }
}
