package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;
import org.junit.Test;

public class NomMachineTest {

    private NomMachine machine=new NomMachine("www.uvsq.fr");

    @Test
    public void ExceptionTest(){
        assertThrows(IllegalArgumentException.class, () -> new NomMachine("wwwfuzfubnzuinfze"));
    }

    @Test
    public void getNomCompletTest(){
        assertEquals("www.uvsq.fr", machine.getNomComplet());
    }

    @Test
    public void getNomTest(){
        assertEquals("www", machine.getNom());
    }

    @Test
    public void getDomaineTest(){
        assertEquals("uvsq.fr", machine.getDomaine());
    }

    @Test
    public void toStringTest(){
        assertEquals("www.uvsq.fr", machine.toString());
    }
    
}
