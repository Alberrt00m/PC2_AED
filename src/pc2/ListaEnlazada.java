package pc2;
/**
 *
 * @author JOSEPH ROJAS
 */
public class ListaEnlazada {
    private Nodo cabeza; 

    public ListaEnlazada() {
        cabeza = null;
    }

    public void agregarTarea(String descripcion, int prioridad) {
        Tarea nuevaTarea = new Tarea(descripcion, prioridad);
        Nodo nuevoNodo = new Nodo(nuevaTarea);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevoNodo);
        }
    }

    public String imprimir() {
        if (cabeza == null) {
            return "La lista está vacía.";
        }

        StringBuilder resultado = new StringBuilder();
        Nodo temp = cabeza;
        while (temp != null) {
            resultado.append("Tarea: ").append(temp.getTarea().getDescripcion())
                     .append(", Prioridad: ").append(temp.getTarea().getPrioridad())
                     .append(" -> ");
            temp = temp.getSiguiente();
        }
        resultado.append("null");
        return resultado.toString();
    }

    public String buscar(String descripcion) {
        Nodo temp = cabeza;
        int posicion = 0;

        while (temp != null) {
            if (temp.getTarea().getDescripcion().equals(descripcion)) {
                return "Tarea '" + descripcion + "' encontrada en la posición " + posicion + ".";
            }
            temp = temp.getSiguiente();
            posicion++;
        }

        return "La tarea '" + descripcion + "' no se encontró en la lista.";
    }

    public String eliminarTarea(String descripcion) {
        if (cabeza == null) {
            return "La lista está vacía. No se puede eliminar la tarea.";
        }

        if (cabeza.getTarea().getDescripcion().equals(descripcion)) {
            cabeza = cabeza.getSiguiente();
            return "Tarea '" + descripcion + "' eliminada exitosamente.";
        }

        Nodo temp = cabeza;
        while (temp.getSiguiente() != null && !temp.getSiguiente().getTarea().getDescripcion().equals(descripcion)) {
            temp = temp.getSiguiente();
        }

        if (temp.getSiguiente() != null) {
            temp.setSiguiente(temp.getSiguiente().getSiguiente());
            return "Tarea '" + descripcion + "' eliminada exitosamente.";
        } else {
            return "La tarea '" + descripcion + "' no se encontró en la lista.";
        }
    }

    public String insertarTarea(String descripcion, int prioridad, int posicion) {
        Tarea nuevaTarea = new Tarea(descripcion, prioridad);
        Nodo nuevoNodo = new Nodo(nuevaTarea);

        if (posicion == 0) {
            nuevoNodo.setSiguiente(cabeza);
            cabeza = nuevoNodo;
            return "Tarea '" + descripcion + "' insertada en la posición 0 (cabeza de la lista).";
        }

        Nodo temp = cabeza;
        int indice = 0;

        while (temp != null && indice < posicion - 1) {
            temp = temp.getSiguiente();
            indice++;
        }

        if (temp == null) {
            return "Error: La posición " + posicion + " es inválida. No se puede insertar.";
        }

        nuevoNodo.setSiguiente(temp.getSiguiente());
        temp.setSiguiente(nuevoNodo);

        return "Tarea '" + descripcion + "' insertada en la posición " + posicion + ".";
    }

    public void borrarLista() {
        cabeza = null;
    }

}

