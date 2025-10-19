package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;


public class Dns {
    private List<DnsItem> items = new ArrayList<>();
    private Path filePath;

    public Dns() throws IOException{
        loadFile("");
        loadData();
    }

    //Ce constructeur prend en compte l'environnement de test
    public Dns(String scope) throws IOException {
        loadFile(scope);
        loadData();
    }

    private void loadFile(String scope) throws IOException{
        Properties props = new Properties();
        try (var fis = Files.newInputStream(Path.of("config.properties"))) {
            props.load(fis);
        }

        
        String dnsFile = props.getProperty("dns"+scope+".file");
        if (dnsFile == null) {
            throw new RuntimeException("Propriété 'dns"+scope+".file' manquante dans config.properties");
        }
        this.filePath = Path.of(dnsFile);
    }

    private void loadData() throws IOException{
        List<String> lignes = Files.readAllLines(filePath);

        
        for (String ligne : lignes) {
            String[] parts = ligne.trim().split("\\s+");
            if (parts.length == 2) {
                String nom = parts[1];
                String ip = parts[0];
                DnsItem item = new DnsItem(new NomMachine(nom), new AdresseIP(ip));
                items.add(item);
            }
        }
    }


    public DnsItem getItem(NomMachine machine) {
        for (DnsItem item : items) {
            if (item.getNomMachine().equals(machine)) {
                return item;
            }
        }
        return null;
    }
    
    public DnsItem getItem(AdresseIP ip) {
        for (DnsItem item : items) {
            if (item.getAdresseIP().equals(ip)) {
                return item;
            }
        }
        return null;
    }

    public List<DnsItem> getItems(String domaine){
        return items.stream()
                .filter(i -> i.getNomMachine().getDomaine().equals(domaine))
                .sorted(Comparator.comparing(i -> i.getNomMachine().getNom()))
                .toList();
    }


    public void addItem(AdresseIP ip, NomMachine nom) throws IOException {

        if (getItem(ip) != null) {
            throw new IllegalArgumentException("ERREUR : L’adresse IP existe déjà !");
        }

        if (getItem(nom) != null) {
            throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");
        }

        DnsItem newItem = new DnsItem(nom, ip);
        items.add(newItem);

        // Sauvegarder dans le fichier
        sauvegarder();


    }

    private void sauvegarder() throws IOException {
        List<String> lignes = new ArrayList<>();
        for (DnsItem item : items) {
            lignes.add(item.getAdresseIP()+ " "+ item.getNomMachine());
        }
        Files.write(filePath, lignes);
    }

    public void deleteLastItem() throws IOException{
        //Pour pouvoir tester la méthode addItem
        List<String> lignes = new ArrayList<>();
        items.removeLast();
        for (DnsItem item : items) {
            lignes.add(item.getAdresseIP()+ " "+ item.getNomMachine());
        }
        Files.write(filePath, lignes);

    }
    

    

}
