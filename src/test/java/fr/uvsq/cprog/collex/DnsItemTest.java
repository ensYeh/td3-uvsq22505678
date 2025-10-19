package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DnsItemTest {
    private AdresseIP ip = new AdresseIP("192.168.0.0");
    private NomMachine machine = new NomMachine("www.uvsq.fr");
    private DnsItem dnsItem = new DnsItem(machine, ip);

    @Test
    public void getNomMachineTest(){
        assertEquals(machine, dnsItem.getNomMachine());
    }

    @Test
    public void getAddresseIPTest(){
        assertEquals(ip, dnsItem.getAdresseIP());
    }

    @Test
    public void toStringTest(){
        assertEquals(machine.toString()+" "+ip.toString(), dnsItem.toString());
    }
}
