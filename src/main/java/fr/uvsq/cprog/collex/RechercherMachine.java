package fr.uvsq.cprog.collex;

import java.util.Arrays;
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
        Comparator<DnsItem> cmpByIp = (a, b) -> {
            int[] o1 = Arrays.stream(a.getAdresseIP().getAddresseIp().split("\\."))
                            .mapToInt(Integer::parseInt)
                            .toArray();
            int[] o2 = Arrays.stream(b.getAdresseIP().getAddresseIp().split("\\."))
                            .mapToInt(Integer::parseInt)
                            .toArray();
            for (int i = 0; i < 4; i++) {
                int diff = Integer.compare(o1[i], o2[i]);
                if (diff != 0) return diff;
            }
            return 0;
        };

        if (liste.isEmpty()) {
            return "Aucune machine trouvée pour le domaine : " + domaine;
        }

        if (triParAdresse) {
            // Trie par adresse IP (ordre croissant)
            liste = liste.stream()
                    .sorted(cmpByIp)
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
