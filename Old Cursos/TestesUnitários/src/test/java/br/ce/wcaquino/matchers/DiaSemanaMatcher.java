package br.ce.wcaquino.matchers;

import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/*  assertThat(retorno.getRetorno(), new DiaSemanaMatcher(Calendar.MONDAY));
                data                    diaSemana
    Eh um mathcer que vai receber uma Date qualquer, a dataDeRetorno
 */
public class DiaSemanaMatcher extends TypeSafeMatcher<Date> {

    // o dia da semana que eu quero, que sera o segundo[2] parametro
    private Integer diaSemana;

    public DiaSemanaMatcher(Integer diaSemana){
        this.diaSemana = diaSemana;
    }

    // A descriaçoa de quando ocorre um erro
    public void describeTo(Description desc) {
        Calendar data = Calendar.getInstance();
        data.set(Calendar.DAY_OF_WEEK, diaSemana);
        String dataExtenso = data.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt","BR"));
        desc.appendText(dataExtenso);
    }

    // eh o mesmo do tipo que eh extendido,é onde a comparação será realizada
    protected boolean matchesSafely(Date data){
        return DataUtils.verificarDiaSemana(data, diaSemana);
    }
}
