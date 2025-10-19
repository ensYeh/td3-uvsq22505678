package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
                String nom = parts[0];
                String ip = parts[1];
                DnsItem item = new DnsItem(new NomMachine(nom), new AdresseIP(ip));
                items.add(item);
            }
        }
    }


    

    

}
