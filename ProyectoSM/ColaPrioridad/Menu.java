import java.util.Scanner;

public class Menu {
    private Supermercado supermercado;
    private Scanner scanner;

    public Menu() {
        this.supermercado = new Supermercado();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        mostrarBienvenida();
        boolean continuar = true;

        while (continuar) {
            mostrarMenuPrincipal();
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                continuar = procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Ingrese un número válido");
            } catch (Exception e) {
                System.out.println("❌ Error inesperado: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("\n¡Gracias por usar el sistema! Hasta pronto. 👋\n");
    }

    private void mostrarBienvenida() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║                                               ║");
        System.out.println("║    🛒 SISTEMA DE GESTIÓN DE SUPERMERCADO 🛒  ║");
        System.out.println("║                                               ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");
    }

    private void mostrarMenuPrincipal() {
        System.out.println("========== SISTEMA DE SUPERMERCADO ==========");
        System.out.println();
        System.out.println("--- GESTIÓN DE INVENTARIO ---");
        System.out.println("1.  Insertar nuevo producto");
        System.out.println("2.  Buscar producto por código");
        System.out.println("3.  Actualizar producto");
        System.out.println("4.  Eliminar producto");
        System.out.println("5.  Mostrar inventario completo");
        System.out.println();
        System.out.println("--- GESTIÓN DE CLIENTES ---");
        System.out.println("6.  Agregar cliente a la cola");
        System.out.println("7.  Atender siguiente cliente");
        System.out.println("8.  Ver clientes en espera");
        System.out.println("9.  Simular atención de todos los clientes");
        System.out.println();
        System.out.println("--- OTROS ---");
        System.out.println("10. Mostrar estadísticas del sistema");
        System.out.println("11. Salir");
        System.out.println();
        System.out.print("Seleccione una opción: ");
    }

    private boolean procesarOpcion(int opcion) {
        System.out.println();

        switch (opcion) {
            case 1:
                insertarProducto();
                break;
            case 2:
                buscarProducto();
                break;
            case 3:
                actualizarProducto();
                break;
            case 4:
                eliminarProducto();
                break;
            case 5:
                supermercado.getInventario().mostrarInventario();
                break;
            case 6:
                agregarCliente();
                break;
            case 7:
                atenderCliente();
                break;
            case 8:
                supermercado.getColaClientes().mostrarCola();
                break;
            case 9:
                simularAtencionCompleta();
                break;
            case 10:
                supermercado.mostrarEstadisticas();
                break;
            case 11:
                return false;
            default:
                System.out.println("❌ Opción inválida. Intente nuevamente.");
        }

        pausar();
        return true;
    }

    private void insertarProducto() {
        System.out.println("=== INSERTAR NUEVO PRODUCTO ===\n");

        try {
            System.out.print("Código del producto (ej: PROD006): ");
            String codigo = scanner.nextLine().trim().toUpperCase();

            if (codigo.isEmpty()) {
                System.out.println("❌ El código no puede estar vacío");
                return;
            }

            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Categoría: ");
            String categoria = scanner.nextLine().trim();

            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine());

            if (precio <= 0) {
                System.out.println("❌ El precio debe ser mayor a 0");
                return;
            }

            System.out.print("Cantidad en stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            if (stock < 0) {
                System.out.println("❌ El stock no puede ser negativo");
                return;
            }

            Producto producto = new Producto(codigo, nombre, categoria, precio, stock);
            supermercado.getInventario().insertar(producto);

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Ingrese valores numéricos válidos");
        }
    }

    private void buscarProducto() {
        System.out.println("=== BUSCAR PRODUCTO ===\n");

        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine().trim().toUpperCase();

        Producto producto = supermercado.getInventario().buscar(codigo);

        if (producto != null) {
            System.out.println("\n✓ Producto encontrado:");
            System.out.println("─────────────────────────────────────");
            System.out.println("Código:    " + producto.getCodigo());
            System.out.println("Nombre:    " + producto.getNombre());
            System.out.println("Categoría: " + producto.getCategoria());
            System.out.println("Precio:    $" + producto.getPrecio());
            System.out.println("Stock:     " + producto.getCantidadStock() + " unidades");
            System.out.println("─────────────────────────────────────\n");
        } else {
            System.out.println("❌ Producto no encontrado: " + codigo);
        }
    }

    private void actualizarProducto() {
        System.out.println("=== ACTUALIZAR PRODUCTO ===\n");

        System.out.print("Ingrese el código del producto a actualizar: ");
        String codigo = scanner.nextLine().trim().toUpperCase();

        Producto producto = supermercado.getInventario().buscar(codigo);
        if (producto == null) {
            System.out.println("❌ Producto no encontrado: " + codigo);
            return;
        }

        System.out.println("\nProducto actual:");
        System.out.println(producto);
        System.out.println("\nDeje en blanco los campos que no desea modificar\n");

        try {
            System.out.print("Nuevo nombre [" + producto.getNombre() + "]: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Nueva categoría [" + producto.getCategoria() + "]: ");
            String categoria = scanner.nextLine().trim();

            System.out.print("Nuevo precio [" + producto.getPrecio() + "]: ");
            String precioStr = scanner.nextLine().trim();
            double precio = precioStr.isEmpty() ? -1 : Double.parseDouble(precioStr);

            System.out.print("Nuevo stock [" + producto.getCantidadStock() + "]: ");
            String stockStr = scanner.nextLine().trim();
            int stock = stockStr.isEmpty() ? -1 : Integer.parseInt(stockStr);

            supermercado.getInventario().actualizar(codigo, nombre, categoria, precio, stock);

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Valores numéricos inválidos");
        }
    }

    private void eliminarProducto() {
        System.out.println("=== ELIMINAR PRODUCTO ===\n");

        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine().trim().toUpperCase();

        Producto producto = supermercado.getInventario().buscar(codigo);
        if (producto == null) {
            System.out.println("❌ Producto no encontrado: " + codigo);
            return;
        }

        System.out.println("\nProducto a eliminar:");
        System.out.println(producto);
        System.out.print("\n¿Está seguro? (S/N): ");
        String confirmacion = scanner.nextLine().trim().toUpperCase();

        if (confirmacion.equals("S")) {
            supermercado.getInventario().eliminar(codigo);
        } else {
            System.out.println("❌ Operación cancelada");
        }
    }

    private void agregarCliente() {
        System.out.println("=== AGREGAR CLIENTE A LA COLA ===\n");

        try {
            System.out.print("Nombre del cliente: ");
            String nombre = scanner.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("❌ El nombre no puede estar vacío");
                return;
            }

            System.out.println("\nTipo de cliente:");
            System.out.println("1. VIP (prioridad alta)");
            System.out.println("2. ADULTO MAYOR (prioridad media)");
            System.out.println("3. REGULAR (prioridad estándar)");
            System.out.print("Seleccione tipo (1-3): ");
            
            int tipoOpcion = Integer.parseInt(scanner.nextLine());
            Cliente.TipoCliente tipo;

            switch (tipoOpcion) {
                case 1:
                    tipo = Cliente.TipoCliente.VIP;
                    break;
                case 2:
                    tipo = Cliente.TipoCliente.ADULTO_MAYOR;
                    break;
                case 3:
                    tipo = Cliente.TipoCliente.REGULAR;
                    break;
                default:
                    System.out.println("❌ Opción inválida, asignando tipo REGULAR");
                    tipo = Cliente.TipoCliente.REGULAR;
            }

            System.out.print("Número de productos: ");
            int numProductos = Integer.parseInt(scanner.nextLine());

            if (numProductos <= 0) {
                System.out.println("❌ El número de productos debe ser mayor a 0");
                return;
            }

            Cliente cliente = new Cliente(nombre, tipo, numProductos);
            supermercado.getColaClientes().agregar(cliente);

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Ingrese valores numéricos válidos");
        }
    }

    private void atenderCliente() {
        System.out.println("=== ATENDER SIGUIENTE CLIENTE ===\n");

        if (supermercado.getColaClientes().estaVacia()) {
            System.out.println("❌ No hay clientes en espera");
            return;
        }

        supermercado.getColaClientes().atenderCliente();
        supermercado.registrarClienteAtendido();
    }

    private void simularAtencionCompleta() {
        System.out.println("=== SIMULACIÓN DE ATENCIÓN COMPLETA ===\n");

        if (supermercado.getColaClientes().estaVacia()) {
            System.out.println("❌ No hay clientes en espera");
            return;
        }

        System.out.print("¿Desea atender a todos los clientes en espera? (S/N): ");
        String confirmacion = scanner.nextLine().trim().toUpperCase();

        if (confirmacion.equals("S")) {
            int clientesAtendidos = supermercado.getColaClientes().tamanio();
            supermercado.getColaClientes().atenderTodos();
            
            for (int i = 0; i < clientesAtendidos; i++) {
                supermercado.registrarClienteAtendido();
            }
        } else {
            System.out.println("❌ Operación cancelada");
        }
    }

    private void pausar() {
        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
        limpiarPantalla();
    }

    private void limpiarPantalla() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.iniciar();
    }
}