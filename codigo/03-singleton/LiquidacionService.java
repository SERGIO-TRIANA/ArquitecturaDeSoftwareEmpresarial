import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Compare el constructor de esta clase con el de la sesion 1.
 *
 * En la sesion 1 el servicio recibia su fuente de precios por constructor, y
 * por eso bastaba leer la firma para saber de que dependia. Aqui el
 * constructor no recibe nada y la clase parece no depender de nada, pero
 * depende de PrecioDelDia y de lo que cualquier otro pedazo del programa le
 * haya hecho antes. Es la misma dependencia de la sesion 1, escondida.
 */
public class LiquidacionService {

    private static final BigDecimal KILOS_POR_CARGA = new BigDecimal("125");

    public BigDecimal liquidar(BigDecimal kilos, BigDecimal factorCalidad) {
        // La dependencia entra aqui, en medio del calculo, y no por constructor.
        long precio = PrecioDelDia.getInstance().precioPorCarga();

        // Se multiplica todo y se divide al final, una sola vez, redondeando
        // una sola vez. Dividir primero con escala 6 -- como lo hace todavia el
        // codigo de la sesion 1 -- da el mismo peso solo mientras los kilos
        // tengan tres decimales o menos, porque n/125000 es 8n/10^6 y cabe
        // exacto en seis decimales. Con un decimal mas la division redondea y
        // aparece un peso de diferencia. El bloque 6 de DineroDemo.java lo mide
        // con dos casos, y el laboratorio 3 del recurso interactivo lo avisa
        // cuando los kilos que uno teclea traen cuatro decimales.
        return kilos.multiply(new BigDecimal(precio))
                    .multiply(factorCalidad)
                    .divide(KILOS_POR_CARGA, 0, RoundingMode.HALF_UP);
    }
}
