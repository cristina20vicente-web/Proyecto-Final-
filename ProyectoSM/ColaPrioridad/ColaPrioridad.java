import java.util.ArrayList;

public class ColaPrioridad {
    private ArrayList<Cliente> heap;

    public ColaPrioridad() {
        this.heap = new ArrayList<>();
    }

    private int obtenerIndicePadre(int indice) {
        return (indice - 1) / 2;
    }

    private int obtenerIndiceHijoIzquierdo(int indice) {
        return 2 * indice + 1;
    }

    private int obtenerIndiceHijoDerecho(int indice) {
        return 2 * indice + 2;
    }

    private boolean tieneMayorPrioridad(Cliente cliente1, Cliente cliente2) {
        if (cliente1.getTipo().getPrioridad() != cliente2.getTipo().getPrioridad()) {
            return cliente1.getTipo().getPrioridad() < cliente2.getTipo().getPrioridad();
        }
        return cliente1.getHoraLlegada() < cliente2.getHoraLlegada();
    }

    private void intercambiar(int i, int j) {
        Cliente temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private void bubbleUp(int indice) {
        while (indice > 0) {
            int indicePadre = obtenerIndicePadre(indice);
            if (tieneMayorPrioridad(heap.get(indice), heap.get(indicePadre))) {
                intercambiar(indice, indicePadre);
                indice = indicePadre;
            } else {
                break;
            }
        }
    }

    private void bubbleDown(int indice) {
        int tamanio = heap.size();
        
        while (true) {
            int indiceMayorPrioridad = indice;
            int indiceIzq = obtenerIndiceHijoIzquierdo(indice);
            int indiceDer = obtenerIndiceHijoDerecho(indice);

            if (indiceIzq < tamanio && tieneMayorPrioridad(heap.get(indiceIzq), heap.get(indiceMayorPrioridad))) {
                indiceMayorPrioridad = indiceIzq;
            }

            if (indiceDer < tamanio && tieneMayorPrioridad(heap.get(indiceDer), heap.get(indiceMayorPrioridad))) {
                indiceMayorPrioridad = indiceDer;
            }

            if (indiceMayorPrioridad == indice) {
                break;
            }

            intercambiar(indice, indiceMayorPrioridad);
            indice = indiceMayorPrioridad;
        }
    }

    public void agregar(Cliente cliente) {
        heap.add(cliente);
        bubbleUp(heap.size() - 1);
        System.out.println("Cliente " + cliente.getNombre() + " agregado a la cola. Posición estimada: " + 
                          calcularPosicion(cliente));
    }

    private int calcularPosicion(Cliente cliente) {
        int posicion = 1;
        for (Cliente c : heap) {
            if (tieneMayorPrioridad(c, cliente)) {
                posicion++;
            }
        }
        return posicion;
    }

    public Cliente extraer() {
        if (heap.isEmpty()) {
            return null;
        }

        if (heap.size() == 1) {
            return heap.remove(0);
        }

        Cliente clientePrioritario = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        bubbleDown(0);

        return clientePrioritario;
    }

    public Cliente consultarSiguiente() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    public boolean estaVacia() {
        return heap.isEmpty();
    }

    public int tamanio() {
        return heap.size();
    }

    public void mostrarCola() {
        if (heap.isEmpty()) {
            System.out.println("No hay clientes en espera");
            return;
        }

        System.out.println("\n=== CLIENTES EN ESPERA ===");
        System.out.println("Pos | Nombre                | Tipo          | Productos | Tiempo Est.");
        System.out.println("----|----------------------|---------------|-----------|------------");

        ArrayList<Cliente> copiaCola = new ArrayList<>(heap);
        ColaPrioridad copiaTemporal = new ColaPrioridad();
        copiaTemporal.heap = new ArrayList<>(copiaCola);

        int posicion = 1;
        while (!copiaTemporal.estaVacia()) {
            Cliente cliente = copiaTemporal.extraer();
            System.out.printf("%-3d | %-20s | %-13s | %-9d | %d seg\n",
                    posicion++,
                    cliente.getNombre(),
                    cliente.getTipo(),
                    cliente.getNumeroProductos(),
                    cliente.getTiempoAtencion());
        }
        System.out.println();
    }

    public void atenderCliente() {
        if (heap.isEmpty()) {
            System.out.println("No hay clientes en espera");
            return;
        }

        Cliente cliente = extraer();
        System.out.println("\n--- ATENDIENDO CLIENTE ---");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Tipo: " + cliente.getTipo());
        System.out.println("Productos: " + cliente.getNumeroProductos());
        System.out.println("Tiempo de atención: " + cliente.getTiempoAtencion() + " segundos");
        
        System.out.println("Procesando...");
        
        System.out.println("✓ Cliente atendido correctamente\n");
    }

    public void atenderTodos() {
        if (heap.isEmpty()) {
            System.out.println("No hay clientes en espera");
            return;
        }

        int totalClientes = heap.size();
        System.out.println("\n=== INICIANDO ATENCIÓN DE " + totalClientes + " CLIENTES ===\n");

        int contador = 1;
        while (!heap.isEmpty()) {
            System.out.println("▶ Atendiendo cliente " + contador + " de " + totalClientes);
            atenderCliente();
            contador++;
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("=== TODOS LOS CLIENTES HAN SIDO ATENDIDOS ===\n");
    }
}