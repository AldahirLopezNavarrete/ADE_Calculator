package calculadoraeda;

/**
 *
 * @author aldahir
 */

public class CalculadoraEDA { 
    //esta clase contiene el método que genera el árbol de expresión
    //con sus funciones auxiliares y el método que evalua, lo demás se programó
    //directo en python

    //método que transforma la expresión en String a un árbol de expresión
    public static LinkedBinaryTree conviertePosfija(String expresion){
        PilaA<String> pilaA = new PilaA<String>();
        PilaA<LinkedBinaryTree> pilaB = new PilaA<LinkedBinaryTree>();
        String[] exp = generaTokens(expresion);
        

        for(int i=0; i<exp.length; i++){
            String s = exp[i];
            
            if(s != null){
                if(isOperando(s)){
                    LinkedBinaryTree nuevo = new LinkedBinaryTree(s);
                    pilaB.push(nuevo);
                }
                else{
                    if(s.equals("("))
                        pilaA.push(s);
                    else{
                        if(s.equals(")")){
                            if(!pilaA.isEmpty()){
                                String actual= pilaA.peek();

                                while(!pilaA.isEmpty()&& !actual.equals("(")){
                                    auxFunction(actual, pilaB);
                                    actual = pilaA.pop();
                                    if(!pilaA.isEmpty() && pilaB.peek().getElemRaiz().equals(actual))
                                        actual = pilaA.pop();
                                }   
                            }
                        }
                        else{
                            if(s.equals("_")){
                                LinkedBinaryTree nuevo = new LinkedBinaryTree("-");
                                NodoBin<String> izq = new NodoBin<String>("0");
                                NodoBin<String> der = new NodoBin<String>(exp[i+1]);
                                i++;
                                
                                nuevo.setIzq(nuevo.getRaiz(),izq);
                                nuevo.setDer(nuevo.getRaiz(),der);
                                pilaB.push(nuevo);
                            }
                            else{
                                //es operador
                                if(!pilaA.isEmpty()){
                                    String actual = pilaA.peek();
                                    boolean exit = true;
                                    String anterior = "";

                                    while(!pilaA.isEmpty() && !actual.equals("(") && exit){
                                        if(jerarquia(s) > jerarquia(actual)){
                                            exit = false;
                                        }
                                        else{
                                            auxFunction(actual,pilaB);
                                            anterior = actual;
                                            actual = pilaA.pop();
                                            if(!pilaA.isEmpty() && anterior.equals(actual))
                                                auxFunction(pilaA.pop(),pilaB);
                                        }
                                    }   
                                }
                                pilaA.push(s);
                            }
                        } 
                    }
                }  
            }
        }        
        if(! pilaA.isEmpty()){
            String actual;
            
            while(!pilaA.isEmpty()){
                actual = pilaA.pop();
                auxFunction(actual,pilaB);
            }
        }

        return pilaB.pop();  
    }
    
    public static void auxFunction(String actual, PilaA<LinkedBinaryTree> pilaB){
        LinkedBinaryTree p = null, s = null;
        LinkedBinaryTree nuevo = new LinkedBinaryTree(actual);
        
        if(!pilaB.isEmpty()){
            p = pilaB.pop();
            nuevo.setDer(nuevo.getRaiz(), p.getRaiz());
        } 
        if(!pilaB.isEmpty()){
            s = pilaB.pop();
            nuevo.setIzq(nuevo.getRaiz(), s.getRaiz());
        }
        
        pilaB.push(nuevo);
    }

    public static String[] generaTokens(String exp){
        String[] arreExp=new String[exp.length()+1]; 
        int i=0,k;
        String aux;
        
        while (i<exp.length()){
            if (exp.charAt(i)=='(' || exp.charAt(i)==')' || isOperador(exp.charAt(i)+"")){
                arreExp[i]=exp.charAt(i)+"";
                i++;
            }
            else{
                k=0;
                aux="";
                while (i+k<exp.length() && (isOperando(exp.charAt(i+k)+""))){
                    aux += exp.charAt(i+k)+"";
                    k++; 
                }
                arreExp[i]=aux;
                i+=k;        
            }   
        }
        return arreExp;
    }
    
    public static boolean isOperando(String op){
        op = op.charAt(0)+"";
        if(op.equals("0") || op.equals("1") || op.equals("2") || op.equals("3")
                || op.equals("4") || op.equals("5") || op.equals("6") || 
                op.equals("7") || op.equals("8") || op.equals("9")|| op.equals("."))
            return true;
        else
            return false;
    }
    
    public static boolean isOperador(String op){
        if(op.equals("*") || op.equals("/") || op.equals("-") || op.equals("+") || op.equals("_"))
            return true;
        else
            return false;
    }
    
    public static int jerarquia(String num){
        if(num.equals("*") || num.equals("/"))
            return 2;
        else{
            if(num.equals("+") || num.equals("-"))
                return 1;
            else
                return 0;
        }
    }
    
    //método que recibe como parámetro un árbol de expresión y regresa el resultado de evaluarlo
    public static <T> double evaluacion(LinkedBinaryTree<T> arbol){
        return evaluacionRecursivo(arbol.getRaiz());
    }
    
    private static <T> double evaluacionRecursivo(NodoBin<T> nodo){
        if (nodo.getIzq() == null || nodo.getDer() == null){
            return Double.parseDouble((String)nodo.getElem());
        }
        else{
            String operador = (String)nodo.getElem();
            double valorIzquierdo = evaluacionRecursivo(nodo.getIzq());
            double valorDerecho = evaluacionRecursivo(nodo.getDer());

            if (operador.equals("+")){
                return valorIzquierdo+valorDerecho;
            } else if(operador.equals("-")){
                return valorIzquierdo-valorDerecho;
            } else if(operador.equals("*")) {
                return valorIzquierdo*valorDerecho;
            } else{
                return valorIzquierdo/valorDerecho;
            }
          }
      }
    
    public static double calcula(String exp){
        LinkedBinaryTree res= conviertePosfija(exp);
        
        return evaluacion(res);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("5*4+5-_6 = "+calcula("5*4+5-_6"));
        System.out.println("(5+10)*3-4/(5-5) = "+calcula("(5+10)*3-4/(5-5)"));
        System.out.println("_5+5*4/6= "+calcula("_5+5*4/6"));
        System.out.println("(4+6)/(6-1)= "+calcula("(4+6)/(6-1)"));
        System.out.println("3+5*3+2= "+calcula("3+5*3+2"));
    } 
}