package fr.uvsq.cprog.collex;

import java.io.IOException;

import org.junit.Test;

public class DnsTest {
    private final String scope="Test";

    @Test
    public void DnsTest(){
        try {
            Dns dns= new Dns(scope);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
