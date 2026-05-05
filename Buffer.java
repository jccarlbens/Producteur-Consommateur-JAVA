import java.util.LinkedList;
import java.util.Queue;

/**
 * Buffer synchronisé - Ressource partagée entre producteurs et consommateurs
 * Utilise synchronized, wait(), notify() pour la synchronisation
 */
public class Buffer {
    private final Queue<Integer> file = new LinkedList<>();
    private final int capaciteMax;

    public Buffer(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    /**
     * Produit un élément dans le buffer.
     * Bloque si le buffer est plein (wait).
     */
    public synchronized void produire(int valeur, String nomThread) throws InterruptedException {
        // Attendre tant que le buffer est plein
        while (file.size() == capaciteMax) {
            System.out.println("[" + nomThread + "] Buffer PLEIN (" + capaciteMax + ") — en attente...");
            wait(); // libère le lock et attend
        }

        file.add(valeur);
        System.out.println("[" + nomThread + "] ✚ Produit : " + valeur
                + " | Buffer : " + file.size() + "/" + capaciteMax);

        // Réveil des consommateurs en attente
        notifyAll();
    }

    /**
     * Consomme un élément du buffer.
     * Bloque si le buffer est vide (wait).
     */
    public synchronized int consommer(String nomThread) throws InterruptedException {
        // Attendre tant que le buffer est vide
        while (file.isEmpty()) {
            System.out.println("[" + nomThread + "] Buffer VIDE — en attente...");
            wait(); // libère le verrou et attend
        }

        int valeur = file.poll();
        System.out.println("[" + nomThread + "] ✖ Consommé : " + valeur
                + " | Buffer : " + file.size() + "/" + capaciteMax);

        // Réveiller les producteurs en attente
        notifyAll();
        return valeur;
    }

    public synchronized int taille() {
        return file.size();
    }
}
