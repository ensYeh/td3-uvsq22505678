package fr.uvsq.cprog.collex;

/**
 * Hello world!
 *
 */
public class DnsApp 
{
    public static void main( String[] args )
    {
        new DnsApp().run();
    }

    public void run() {
        try {
            Dns dns = new Dns(); 
            DnsTUI tui = new DnsTUI();
            boolean running = true;

            System.out.println("=== Simulateur DNS ===");
            System.out.println("Tapez 'quit' ou 'exit' pour quitter.\n");

            while (running) {
                Commande cmd = tui.nextCommande(dns);
                if (cmd != null) {
                    String result = cmd.execute();
                    if ("quit".equalsIgnoreCase(result)) {
                        running = false;
                    } else {
                        tui.affiche(result);
                    }
                }
            }

            System.out.println("👋 Au revoir !");
        } catch (Exception e) {
            System.err.println("ERREUR : " + e.getMessage());
        }
    }
}
