package calculadoraeda;

import java.util.Iterator;

/**
 *
 * @author aldahir
 */
public interface BinaryTreeADT<T>{
    public boolean isEmpty();
    public int size();
    public boolean find(T elem);
    public Iterator<T> preOrden();
    public Iterator<T> inOrden();
    public Iterator<T> postOrden();
    
}
