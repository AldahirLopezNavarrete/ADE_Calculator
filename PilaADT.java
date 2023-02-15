
package calculadoraeda;

/**
 *
 * @author aldahir
 * 4 de feb 2021
 * interface que exige comportamiento para pilas
 */

public interface PilaADT <T> {
    
    public void push(T dato);
    public T pop();
    public boolean isEmpty();
    public T peek();
    public void multiPop(int n);
    public boolean equals(Object otro);
    
}
