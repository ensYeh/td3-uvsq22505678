package fr.uvsq.cprog.collex;

public class Quitter implements Commande{


    @Override
    public String execute() {
        return "quit";
    }
    
}
