package fr.uvsq.cprog.collex;

public class AdresseIP {

    private String addresseIp;

    public AdresseIP(String ip){
        if(!isValidIP(ip)) throw new IllegalArgumentException("Adresse IP Invalide : "+ip);
        addresseIp=ip;
    }

    public String getAddresseIp() {
        return addresseIp;
    }

    public static Boolean isValidIP(String ip){
        String[] parts = ip.split("\\.");
        if (parts.length != 4) return false;
        for (String p : parts) {
            try {
                int value = Integer.parseInt(p);
                if (value < 0 || value > 255) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
    
}
