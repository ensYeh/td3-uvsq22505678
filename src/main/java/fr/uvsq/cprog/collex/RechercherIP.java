package fr.uvsq.cprog.collex;

public class RechercherIP implements Commande{

    private Dns dns;
    private NomMachine nom;

    public RechercherIP(Dns dns, NomMachine nom) {
        this.dns = dns;
        this.nom = nom;
    }

    @Override
    public String execute() {
        DnsItem item = dns.getItem(nom);
        if (item != null) {
            return item.getAdresseIP().toString();
        } else {
            return "ERREUR : Nom de machine inconnu.";
        }
    }
}
