package br.ce.wcaquino.matchers;

import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataDiferencaDiasMacher extends TypeSafeMatcher<Date> {

    private Integer qtdDias;

    public DataDiferencaDiasMacher(Integer qtdDias){
        this.qtdDias = qtdDias;
    }

    // Esse eh o generico
    @Override
    protected boolean matchesSafely(Date data) {
        // se a data do 1 param eh igual a outra data + 1 dia, ou seja, isso vale pra saber se
        //      A DATA DE LOCAÇÃO ESTA CORRETA
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(qtdDias));
    }

    @Override
    public void describeTo(Description description) {
        Date dataEsperada = DataUtils.obterDataComDiferencaDias(qtdDias);
        DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        description.appendText(format.format(dataEsperada));


    }
}
