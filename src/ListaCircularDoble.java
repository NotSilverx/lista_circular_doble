import java.util.Comparator;

public class ListaCircularDoble {
    public Nodo cabeza;
    public Nodo cola;
    public int tamanio;

    public ListaCircularDoble() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public void insertar(int dato) {
        Nodo nuevoNodo = new Nodo(dato);

        if (estaVacia()) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            nuevoNodo.anterior = cola;
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }

        cola.siguiente = cabeza;
        cabeza.anterior = cola;
        tamanio++;
    }

    public void eliminar(int dato) {
        if (estaVacia()) {
            return;
        }

        Nodo actual = cabeza;

        if (actual.dato == dato) {
            if (tamanio == 1) {
                cabeza = null;
                cola = null;
            } else {
                cabeza = cabeza.siguiente;
                cola.anterior = cabeza;
                cabeza.anterior = cola;
            }
            tamanio--;
            return;
        }

        do {
            if (actual.siguiente.dato == dato) {
                if (actual.siguiente == cola) {
                    cola = actual;
                }
                actual.siguiente = actual.siguiente.siguiente;
                actual.siguiente.anterior = actual;
                tamanio--;
                return;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);
    }

    public Nodo buscarAvanzada(int dato, Comparator<Integer> comparator) {
        if (estaVacia()) {
            return null;
        }

        Nodo actual = cabeza;

        do {
            if (comparator.compare(actual.dato, dato) == 0) {
                return actual;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);

        return null;
    }

    public void ordenar(Comparator<Integer> comparator) {
        if (estaVacia() || tamanio == 1) {
            return;
        }

        Nodo actual = cabeza;
        Nodo menor = null;
        Nodo siguienteMenor = null;

        do {
            menor = actual;
            siguienteMenor = actual.siguiente;

            while (siguienteMenor != cabeza && comparator.compare(siguienteMenor.dato, menor.dato) < 0) {
                menor = siguienteMenor;
                siguienteMenor = siguienteMenor.siguiente;
            }

            if (menor != actual) {
                int temp = actual.dato;
                actual.dato = menor.dato;
                menor.dato = temp;
            }

            actual = siguienteMenor;
        } while (actual != cabeza);
    }
}