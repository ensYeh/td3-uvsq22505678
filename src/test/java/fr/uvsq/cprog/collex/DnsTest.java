package fr.uvsq.cprog.collex;

import java.io.IOException;
import static org.junit.Assert.*;

import org.junit.Test;

public class DnsTest {
    private final String scope="Test";
    private final NomMachine machineExistant = new NomMachine("www.uvsq.fr");
    private final NomMachine machineNonExistant = new NomMachine("www.uvsq.com");
    private Dns dns;

    

    @Test
    public void getItemWithNomMachineFoundTest(){
        try {
            dns= new Dns(scope);
            assertEquals("193.51.31.90 www.uvsq.fr", dns.getItem(machineExistant));
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
}
