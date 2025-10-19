package fr.uvsq.cprog.collex;

public class NomMachine {
    private String nomComplet;
    private String nom;
    private String domaine;

    public NomMachine(String nomComplet) {
        if (!nomComplet.contains(".")) {
            throw new IllegalArgumentException("Nom de machine invalide : " + nomComplet);
        }
        this.nomComplet = nomComplet;
        String[] parts = nomComplet.split("\\.", 2);
        this.nom = parts[0];
        this.domaine = parts[1];
    }
    
}
