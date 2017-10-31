package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
	Varasto pieniVarasto;
	Varasto pieniVarasto2;
	Varasto pieniVarasto3;
	Varasto pieniVarasto4;
	Varasto pieniVarasto5;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
		pieniVarasto = new Varasto(0, -1);
		pieniVarasto2 = new Varasto(-1);
		pieniVarasto3 = new Varasto(1,0);
		pieniVarasto4 = new Varasto(1,2);
		pieniVarasto5 = new Varasto(1,1);
    }
	
	@Test
    public void testaaPienillaArvoille() {
        assertEquals(12, pieniVarasto.getSaldo(), vertailuTarkkuus);
    }
	
	@Test
    public void testaaNegatiivinenPoisto() {
		pieniVarasto2.otaVarastosta(-1);
        assertEquals(0, pieniVarasto2.getSaldo(), vertailuTarkkuus);
    }
	
	@Test
    public void testaaNegatiivinenLisays() {
		pieniVarasto.lisaaVarastoon(-1);
        assertEquals(0, pieniVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
	
	@Test
	public void maaraSuurempiKuinSaldo() { 
		pieniVarasto4.otaVarastosta(8);
        assertEquals(0, pieniVarasto4.getSaldo(), vertailuTarkkuus);
	}
	
	@Test
	public void listataanLiikaa() { 
		pieniVarasto4.lisaaVarastoon(8);
        assertEquals(1, pieniVarasto4.getSaldo(), vertailuTarkkuus);
	}
	
	@Test public void testaaToString() {
		String s = pieniVarasto5.toString();
		String test = "saldo = 1.0, vielä tilaa 0.0";
        assertEquals(test, s);
	}
	
	

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}