package fr.uvsq.cprog.collex;

public class RechercherNom implements Commande{
    private Dns dns;
    private AdresseIP ip;
    
    public RechercherNom(Dns dns, AdresseIP ip) {
        this.dns = dns;
        this.ip = ip;
    }

    @Override
    public String execute() {
        DnsItem item = dns.getItem(ip);
        if (item != null) {
            return item.getNomMachine().toString();
        } else {
            return "ERREUR : Adresse IP inconnu.";
        }
    }
}
