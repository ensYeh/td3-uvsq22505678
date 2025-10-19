package fr.uvsq.cprog.collex;

import java.io.IOException;

public class AjouterItem implements Commande{
    
    private Dns dns;
    private AdresseIP ip;
    private NomMachine nom;

    public AjouterItem(Dns dns, AdresseIP ip, NomMachine nom) {
        this.dns = dns;
        this.ip = ip;
        this.nom = nom;
    }

    @Override
    public String execute() {
        try {
            dns.addItem(ip, nom);
            return "Ajout réussi : " + ip + " " + nom;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "ERREUR : impossible de sauvegarder l’entrée.";
        }
    }
}
