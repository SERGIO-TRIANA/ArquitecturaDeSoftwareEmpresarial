import java.math.BigDecimal;

/**
 * El organico reutiliza la forma del pergamino, pero con piso de 0,90 y
 * prima de certificacion de 0,06.
 */
public class PoliticaOrganico implements PoliticaDeCalidad {

    private final PoliticaPergamino base = new PoliticaPergamino();

    @Override
    public String tipo() {
        return "ORGANICO";
    }

    @Override
    public BigDecimal factor(Muestra m) {
        BigDecimal factor = base.factor(m).max(new BigDecimal("0.9000"));
        if (m.certificada()) {
            factor = factor.add(new BigDecimal("0.0600"))
                           .min(new BigDecimal("1.1000"));
        }
        return factor;
    }
}
