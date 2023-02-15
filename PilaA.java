package calculadoraeda;

/**
 *
 * @author aldahir
 * 4 de feb 2021
 * clase que modela pilas (Stacks)
 */

public class PilaA <T> implements PilaADT <T>{
    private T[] elementos;
    private int tope;
    private final int MAX=20;
    
    public PilaA(){
        this.elementos= (T[]) new Object[MAX];
        tope=-1; //INDICA PILA VACIA
    }
    
    public PilaA(int tam){
        this.elementos= (T[]) new Object[tam];
        tope=-1; //INDICA PILA VACIA   
    }

    @Override
    public boolean isEmpty(){
        return tope==-1;
    }
    
//    @Override
//    public T peek(){  //opcion A
//       T resp=null;
//       
//       if(!isEmpty())
//           resp=elementos[tope];
//       
//       return resp;
//    }
    
    @Override
    public T peek(){  //opcion B
        if(isEmpty())
            throw new ExcepcionColeccionVacia("Pila vacía, NO se puede consultar");
        else
            return elementos[tope];
    }
    
    @Override
    public T pop(){
        T dato;
        
        if(isEmpty())
            throw new ExcepcionColeccionVacia("Pila vacía, NO se puede consultar");
        else{
            dato= elementos[tope];
            elementos[tope]=null;
            tope--;
            return dato;
        }  
    }
        
    private void aumentaCapacidad(){
        T[] nuevo= (T[]) new Object[elementos.length*2];
        
        for(int i=0;i<=tope;i++){
            nuevo[i]=elementos[i];
        }
        elementos=nuevo;
    }
    
    @Override
    public void push(T dato){
        if(tope==elementos.length-1) //Esta llena la pila
            aumentaCapacidad();
        tope++;
        elementos[tope]=dato;    
    }
    
    @Override
    public void multiPop(int n){
        if(n<tope+1 && n>0){
            for(int i=0;i<n;i++){
                pop();
            }
        }
    }
    
    //equals recursivo
    public boolean equals(Object obj){
        boolean res;
        
        if(obj != null && obj instanceof PilaADT){
            PilaADT<T> otra= (PilaADT)obj;
            PilaA<T> aux=new PilaA();
            res=equals(otra, new PilaA(),tope);
            while(!aux.isEmpty())
                otra.push(aux.pop());
        }
        else
            res=false;
        
        return res;
    }
    
    private boolean equals(PilaADT<T> otra, PilaA<T> aux,int i){
        
        if(i==-1 && otra.isEmpty())
            return true;
        else{
            if(i==-1 || otra.isEmpty())
                return false;
            else{
                if(!elementos[i].equals(otra.peek())){
                    return false;
                }
                else{
                    i--;
                    aux.push(otra.pop());
                    return equals(otra,aux,i);
                }
            }
        }      
    }
  
}
