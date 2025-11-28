public class Supermercado {
    private ArbolAVL inventario;
    private ColaPrioridad colaClientes;
    private int totalClientesAtendidos;
    private int totalVentas;

    public Supermercado() {
        this.inventario = new ArbolAVL();
        this.colaClientes = new ColaPrioridad();
        this.totalClientesAtendidos = 0;
        this.totalVentas = 0;
        inicializarDatosEjemplo();
    }

    private void inicializarDatosEjemplo() {
        inventario.insertar(new Producto("PROD001", "Aceite de oliva", "Alimentos", 85.50, 50));
        inventario.insertar(new Producto("PROD002", "Leche entera", "Bebidas", 22.00, 100));
        inventario.insertar(new Producto("PROD003", "Pan integral", "Panaderia", 15.50, 75));
        inventario.insertar(new Producto("PROD004", "Arroz blanco 1kg", "Alimentos", 28.75, 200));
        inventario.insertar(new Producto("PROD005", "Cafe molido", "Bebidas", 65.00, 40));
    }

    public ArbolAVL getInventario() {
        return inventario;
    }

    public ColaPrioridad getColaClientes() {
        return colaClientes;
    }

    public void mostrarEstadisticas() {
        System.out.println("\n===============================================");
        System.out.println("      ESTADISTICAS DEL SUPERMERCADO           ");
        System.out.println("===============================================");
        System.out.println();
        System.out.println("INVENTARIO:");
        System.out.println("   Total de productos: " + inventario.contarProductos());
        System.out.println();
        System.out.println("CLIENTES:");
        System.out.println("   Clientes en espera: " + colaClientes.tamanio());
        System.out.println("   Clientes atendidos hoy: " + totalClientesAtendidos);
        System.out.println();
        System.out.println("VENTAS:");
        System.out.println("   Total de transacciones: " + totalVentas);
        System.out.println();
    }

    public void registrarClienteAtendido() {
        totalClientesAtendidos++;
        totalVentas++;
    }

    public int getTotalClientesAtendidos() {
        return totalClientesAtendidos;
    }

    public int getTotalVentas() {
        return totalVentas;
    }
}