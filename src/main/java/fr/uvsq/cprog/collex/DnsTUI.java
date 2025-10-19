package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsTUI {
    
    private Scanner scanner = new Scanner(System.in);
    
    public Commande nextCommande(Dns dns) {
        System.out.print("> ");
        String ligne = scanner.nextLine().trim();

        // Quitter ?
        if (ligne.equalsIgnoreCase("quit") || ligne.equalsIgnoreCase("exit")) {
            return new Quitter();
        }

        // Ajouter une addresse Ip et un nom de machine
        if (ligne.startsWith("add ")) {
            String[] parts = ligne.split("\\s+");
            if (parts.length == 3) {
                AdresseIP ip = new AdresseIP(parts[1]);
                NomMachine nom = new NomMachine(parts[2]);
                return new AjouterItem(dns, ip, nom);
            } else {
                System.out.println("Commande invalide, veuillez vérifier");
                return null;
            }
        }

        // ls [-a] domaine
        if (ligne.startsWith("ls")) {
            String[] parts = ligne.split("\\s+");
            if (parts.length == 2) {
                return new RechercherMachine(dns, parts[1], false);
            } else if (parts.length == 3 && parts[1].equals("-a")) {
                return new RechercherMachine(dns, parts[2], true);
            } else {
                System.out.println("Commande invalide, veuillez vérifier");
                return null;
            }
        }

        // Si la commande ressemble à une adresse IP
        if (AdresseIP.isValidIP(ligne)) {
            AdresseIP ip = new AdresseIP(ligne);
            return new RechercherNom(dns, ip);
        }

        // Sinon, c’est un nom de machine
        if (NomMachine.isValidNomMachine(ligne)) {
            NomMachine nom = new NomMachine(ligne);
            return new RechercherIP(dns, nom);
        }

        System.out.println("Commande inconnue !");
        return null;
    }

    /** Affiche le résultat d’une commande */
    public void affiche(String message) {
        System.out.println(message);
    }
    
}
