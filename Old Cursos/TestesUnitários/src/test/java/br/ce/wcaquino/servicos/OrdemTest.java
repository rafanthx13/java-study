package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

// COm isso vai rodar em ormde alfabetica
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTest {

    public static int count;
    @Test
    public void ainicio(){
        count = 1;
    }

    @Test
    public void verifica(){
        Assert.assertEquals(1,count);
    }
}
