package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;
import org.junit.Test;

public class AdresseIPTest {

    @Test
    public void isValidIPTest(){
        String ip = "193.51.31.90";
        assertTrue(AdresseIP.isValidIP(ip));
    }

    @Test
    public void isValidIPTest2(){
        String ip = "193.51.av.122";
        assertFalse(AdresseIP.isValidIP(ip));
    }
    
    @Test
    public void ExceptionTest(){
        assertThrows(IllegalArgumentException.class, () -> new AdresseIP("193.51.qqq.90"));
        assertThrows(IllegalArgumentException.class, () -> new AdresseIP("193.5"));
        assertThrows(IllegalArgumentException.class, () -> new AdresseIP("255.51.0.256"));
        assertThrows(IllegalArgumentException.class, () -> new AdresseIP("193/51/0/90"));
    }

    @Test
    public void toStringTest(){
        String ip = "193.51.31.90";
        AdresseIP adr= new AdresseIP(ip);
        assertEquals(ip,adr.toString());
    }

    @Test
    public void equalsTrueTest(){
        String ip = "193.51.31.90";
        AdresseIP adr= new AdresseIP(ip);
        AdresseIP adr2= new AdresseIP(ip);
        assertTrue(adr.equals(adr2));
    }

    @Test
    public void equalsFalseTest(){
        String ip = "193.51.31.90";
        AdresseIP adr= new AdresseIP(ip);
        String ip2 = "193.51.31.67";
        AdresseIP adr2= new AdresseIP(ip2);
        assertFalse(adr.equals(adr2));
    }
}
