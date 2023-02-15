package calculadoraeda;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author aldahir
 */
public class LinkedBinaryTree <T> implements BinaryTreeADT<T> {
    protected NodoBin<T> raiz;
    protected int cont;
    
    public LinkedBinaryTree(){
        raiz = null;
        cont = 0;
    }
    
    public LinkedBinaryTree(T elem){
        raiz= new NodoBin<T>(elem);
        cont= 1;
    }
    
    public boolean isEmpty(){
        return cont == 0;
    }
    
    public int size(){
        return cont;
    }
    
    public boolean find(T elem){
        return find(elem,raiz)!=null;
    }
    
    public NodoBin<T> find (T elem, NodoBin<T> pos){
        if(pos == null)
            return null;
        
        if(pos.getElem().equals(elem))
            return pos;
        
        NodoBin<T> temp = find(elem, pos.getIzq());
        if(temp == null)
            temp = find(elem, pos.getDer());
        
        return temp;
    }
    
    public T getElemRaiz(){
        return this.raiz.getElem();
    }
    
    public NodoBin<T> getRaiz(){
        return this.raiz;
    }
    
    public void setIzq(NodoBin<T> actual,NodoBin<T> izq){
        actual.setIzq(izq);
    }
    
    public void setDer(NodoBin<T> actual,NodoBin<T> der){
        actual.setDer(der);
    }
    
    public Iterator<T> preOrden(){
        ArrayList<T> lista = new ArrayList<T>();
        
        preOrden(raiz, lista);
        return lista.iterator();
    }
    
    private void preOrden(NodoBin<T> actual, ArrayList<T> lista){
        if(actual == null)
            return;
        
        lista.add(actual.getElem()); // visitar el nodo
        preOrden(actual.getIzq(), lista); //recorro hijo izquierdo
        preOrden(actual.getDer(), lista); //recorro hijo derecho
    }
    
    public Iterator<T> inOrden(){
        ArrayList<T> lista = new ArrayList<T>();
        
        inOrden(raiz, lista);
        return lista.iterator();
    }
    
    private void inOrden(NodoBin<T> actual, ArrayList<T> lista){
        if(actual == null)
            return;
        
        inOrden(actual.getIzq(), lista); //recorro hijo izquierdo
        lista.add(actual.getElem()); // visitar el nodo
        inOrden(actual.getDer(), lista); //recorro hijo derecho
    }
    
    public Iterator<T> postOrden(){
        ArrayList<T> lista = new ArrayList<T>();
        
        postOrden(raiz, lista);
        return lista.iterator();
    }
    
    private void postOrden(NodoBin<T> actual, ArrayList<T> lista){
        if(actual == null)
            return;
        
        postOrden(actual.getIzq(), lista); //recorro hijo izquierdo
        postOrden(actual.getDer(), lista); //recorro hijo derecho
        lista.add(actual.getElem()); // visitar el nodo
    }
    
    
    public int altura(){
        if(raiz == null)
            return -1;
        return altura(raiz)-1;
    }
    
    private int altura(NodoBin<T> actual){
        if(actual == null)
            return 0;
        
        int alturaIzq = altura(actual.getIzq());
        int alturaDer = altura(actual.getDer());

        if(alturaIzq < alturaDer)
            return alturaIzq+1;
        else
            return alturaDer+1; 
    }
}
