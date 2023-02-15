package calculadoraeda;

/**
 *
 * @author aldahir
 */
public class NodoBin<T> {
    private T elem;
    private NodoBin<T> izq, der, papa;
   
    public NodoBin(T elemento){
        elem = elemento;
        izq = null;
        der = null;
    }
    
    public T getElem(){
        return this.elem;
    }
    
    public void setElem(T dato){
        this.elem = dato;
    }

    public NodoBin<T> getIzq() {
        return izq;
    }
    
    public void setIzq(NodoBin<T> izq) {
        this.izq = izq;
    }
    
    public NodoBin<T> getDer() {
        return der;
    }
    
    public void setDer(NodoBin<T> der) {
        this.der = der;
    }

    public NodoBin<T> getPapa() {
        return papa;
    }

}

