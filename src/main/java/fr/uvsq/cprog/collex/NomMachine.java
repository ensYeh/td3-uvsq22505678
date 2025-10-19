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
    
    public String getNomComplet() {
        return nomComplet;
    }

    public String getNom(){
        return nom;
    }

    public String getDomaine(){
        return domaine;
    }

    @Override
    public String toString(){
        return nomComplet;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof NomMachine)) return false;
        NomMachine other = (NomMachine) obj;
        return this.nomComplet.equals(other.nomComplet);
    }

    @Override
    public int hashCode() {
        return nomComplet.hashCode();
    }
}
