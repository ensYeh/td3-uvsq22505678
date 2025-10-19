package fr.uvsq.cprog.collex;

import java.io.IOException;
import static org.junit.Assert.*;

import org.junit.Test;

public class DnsTest {
    private final String scope="Test";
    private final NomMachine machineExistant = new NomMachine("www.uvsq.fr");
    private final NomMachine machineNonExistant = new NomMachine("www.uvsq.com");
    private final AdresseIP ipExistant = new AdresseIP("193.51.31.90");
    private final AdresseIP ipNonExistant = new AdresseIP("192.168.0.0");
    private Dns dns;

    

    @Test
    public void getItemWithNomMachineFoundTest(){
        try {
            dns= new Dns(scope);
            DnsItem item = dns.getItem(machineExistant);
            assertEquals("193.51.31.90 www.uvsq.fr", item.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

    @Test
    public void getItemWithNomMachineNotFoundTest(){
        try {
            dns= new Dns(scope);
            assertEquals(null, dns.getItem(machineNonExistant));
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    public void getItemWithAdresseIPFoundTest(){
        try {
            dns= new Dns(scope);
            DnsItem item = dns.getItem(ipExistant);
            assertEquals("193.51.31.90 www.uvsq.fr", item.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    public void getItemWithAdresseIPNotFoundTest(){
        try {
            dns= new Dns(scope);
            assertEquals(null, dns.getItem(ipNonExistant));
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }


}
