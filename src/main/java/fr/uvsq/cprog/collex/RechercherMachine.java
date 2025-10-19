package fr.uvsq.cprog.collex;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RechercherMachine implements Commande{
    
    private Dns dns;
    private String domaine;
    private boolean triParAdresse;

    public RechercherMachine(Dns dns, String domaine, boolean triParAdresse) {
        this.dns = dns;
        this.domaine = domaine;
        this.triParAdresse = triParAdresse;
    }

    @Override
    public String execute() {
        List<DnsItem> liste = dns.getItems(domaine);

        if (liste.isEmpty()) {
            return "Aucune machine trouvée pour le domaine : " + domaine;
        }

        if (triParAdresse) {
            // Trie par adresse IP (ordre croissant)
            liste = liste.stream()
                    .sorted(Comparator.comparing(i -> ((DnsItem) i).getAdresseIP().getAddresseIp()))
                    .collect(Collectors.toList());
        }

        // Construction du texte à afficher
        StringBuilder sb = new StringBuilder();
        for (DnsItem item : liste) {
            sb.append(item.getAdresseIP())
              .append(" ")
              .append(item.getNomMachine())
              .append("\n");
        }

        return sb.toString().trim();
    }
}
