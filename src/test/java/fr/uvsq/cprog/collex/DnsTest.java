package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void getItemsTest(){
        
        DnsItem[] items= {
            new DnsItem(new NomMachine("ecampus.uvsq.fr"),new AdresseIP("193.51.25.12")),
            new DnsItem(new NomMachine("pikachu.uvsq.fr"),new AdresseIP("193.51.25.24")),
            new DnsItem(new NomMachine("poste.uvsq.fr"),new AdresseIP("193.51.31.154") ),
            new DnsItem(machineExistant, ipExistant)
        };
        List<DnsItem> itemForUVSQFR= new ArrayList<>();
        for (DnsItem item : items) {
            itemForUVSQFR.add(item);
        }
        try {
            dns= new Dns(scope);
            assertEquals(itemForUVSQFR.toString(), dns.getItems("uvsq.fr").toString());
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

     @Test
    public void getItemsBadOrderTest(){
        
        DnsItem[] items= {
            new DnsItem(machineExistant, ipExistant),
            new DnsItem(new NomMachine("ecampus.uvsq.fr"),new AdresseIP("193.51.25.12")),
            new DnsItem(new NomMachine("pikachu.uvsq.fr"),new AdresseIP("193.51.25.24")),
            new DnsItem(new NomMachine("poste.uvsq.fr"),new AdresseIP("193.51.31.154") )
            
        };
        List<DnsItem> itemForUVSQFR= new ArrayList<>();
        for (DnsItem item : items) {
            itemForUVSQFR.add(item);
        }
        try {
            dns= new Dns(scope);
            assertNotEquals(itemForUVSQFR.toString(), dns.getItems("uvsq.fr").toString());
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    public void getItemsInexistantDomainTest(){
        List<DnsItem> itemForUVSQFR= new ArrayList<>();
        try {
            dns= new Dns(scope);
            assertEquals(itemForUVSQFR, dns.getItems("uvsq.com"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    public void addItemTest(){

        //Je teste l'ajout avec la suppression
        try {
            dns= new Dns(scope);
            dns.addItem(new AdresseIP("0.0.0.0"), new NomMachine("test.uvsq.fr"));
            assertEquals("test.uvsq.fr 0.0.0.0", dns.getItem(new AdresseIP("0.0.0.0")).toString());
            dns.deleteLastItem();
            assertEquals(null, dns.getItem(new AdresseIP("0.0.0.0")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AddItemExceptionTest(){
        try {
            dns= new Dns(scope);
            assertThrows(IllegalArgumentException.class, () -> dns.addItem(ipExistant, machineExistant));
            assertThrows(IllegalArgumentException.class, () -> dns.addItem(ipExistant, machineNonExistant));
            assertThrows(IllegalArgumentException.class, () -> dns.addItem(ipNonExistant, machineExistant));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }



}
