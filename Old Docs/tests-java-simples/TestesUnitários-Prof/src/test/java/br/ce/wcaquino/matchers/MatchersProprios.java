package br.ce.wcaquino.matchers;

import java.util.Calendar;

public class MatchersProprios {

    public static DiaSemanaMatcher caiEm(Integer diaSemana){
        return new DiaSemanaMatcher(diaSemana);
    }

    public static DiaSemanaMatcher caiNumaSegunda(){
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }

    public static DataDiferencaDiasMacher ehHojeComDiferencadeDias(Integer qtdDias){
        return new DataDiferencaDiasMacher(qtdDias);
    }

    // Vai chamar o método anterior só que vai chamar automáticamente com dia = 0
    public static DataDiferencaDiasMacher ehHoje(){
        return new DataDiferencaDiasMacher(0);
    }


}
