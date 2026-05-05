import java.util.Random;

/**
 * Consommateur - Thread qui prélève des éléments du buffer
 */
public class Consommateur implements Runnable {
    private final Buffer buffer;
    private final String nom;
    private final int nbElements;
    private final Random random = new Random();

    public Consommateur(Buffer buffer, String nom, int nbElements) {
        this.buffer = buffer;
        this.nom = nom;
        this.nbElements = nbElements;
    }//constructeur qui initialise les champs du consommateur

    @Override
    public void run() {
        try {
            for (int i = 1; i <= nbElements; i++) {
                int valeur = buffer.consommer(nom);//prélève un élément du buffer
                // Simulation d'un temps de traitement 
                Thread.sleep(random.nextInt(700) + 200);// pause entre 200ms et 900ms
            }
            System.out.println("[" + nom + "] Consommation terminée.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[" + nom + "] Interrompu.");//si le thread est interrompu, on affiche un message et on termine proprement
        }
    }
}
