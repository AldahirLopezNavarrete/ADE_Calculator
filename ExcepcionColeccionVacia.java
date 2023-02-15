
package calculadoraeda;

/**
 *
 * @author aldahir
 * 4 de feb 2021
 * excepcion que se usa para cuando las pilas estan vacias
 */

public class ExcepcionColeccionVacia extends RuntimeException {

    public ExcepcionColeccionVacia() {
        super("Coleccion vacía");
    }
    
    public ExcepcionColeccionVacia(String mensaje) {
        super(mensaje);
    } 
}
