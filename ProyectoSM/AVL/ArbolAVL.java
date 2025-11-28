public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    private int obtenerAltura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.getAltura();
    }

    private int obtenerBalance(NodoAVL nodo) {
        return nodo == null ? 0 : obtenerAltura(nodo.getIzquierdo()) - obtenerAltura(nodo.getDerecho());
    }

    private void actualizarAltura(NodoAVL nodo) {
        if (nodo != null) {
            nodo.setAltura(1 + Math.max(obtenerAltura(nodo.getIzquierdo()), obtenerAltura(nodo.getDerecho())));
        }
    }

    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.getIzquierdo();
        NodoAVL T2 = x.getDerecho();

        x.setDerecho(y);
        y.setIzquierdo(T2);

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.getDerecho();
        NodoAVL T2 = y.getIzquierdo();

        y.setIzquierdo(x);
        x.setDerecho(T2);

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    public boolean insertar(Producto producto) {
        if (buscar(producto.getCodigo()) != null) {
            System.out.println("Error: Producto duplicado - " + producto.getCodigo());
            return false;
        }
        raiz = insertarRecursivo(raiz, producto);
        System.out.println("Producto " + producto.getCodigo() + " insertado correctamente");
        return true;
    }

    private NodoAVL insertarRecursivo(NodoAVL nodo, Producto producto) {
        if (nodo == null) {
            return new NodoAVL(producto);
        }

        if (producto.getCodigo().compareTo(nodo.getProducto().getCodigo()) < 0) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), producto));
        } else if (producto.getCodigo().compareTo(nodo.getProducto().getCodigo()) > 0) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), producto));
        } else {
            return nodo;
        }

        actualizarAltura(nodo);

        int balance = obtenerBalance(nodo);

        if (balance > 1 && producto.getCodigo().compareTo(nodo.getIzquierdo().getProducto().getCodigo()) < 0) {
            return rotarDerecha(nodo);
        }

        if (balance < -1 && producto.getCodigo().compareTo(nodo.getDerecho().getProducto().getCodigo()) > 0) {
            return rotarIzquierda(nodo);
        }

        if (balance > 1 && producto.getCodigo().compareTo(nodo.getIzquierdo().getProducto().getCodigo()) > 0) {
            nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
            return rotarDerecha(nodo);
        }

        if (balance < -1 && producto.getCodigo().compareTo(nodo.getDerecho().getProducto().getCodigo()) < 0) {
            nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public Producto buscar(String codigo) {
        return buscarRecursivo(raiz, codigo);
    }

    private Producto buscarRecursivo(NodoAVL nodo, String codigo) {
        if (nodo == null) {
            return null;
        }

        int comparacion = codigo.compareTo(nodo.getProducto().getCodigo());

        if (comparacion < 0) {
            return buscarRecursivo(nodo.getIzquierdo(), codigo);
        } else if (comparacion > 0) {
            return buscarRecursivo(nodo.getDerecho(), codigo);
        } else {
            return nodo.getProducto();
        }
    }

    public boolean actualizar(String codigo, String nombre, String categoria, double precio, int stock) {
        Producto producto = buscar(codigo);
        if (producto == null) {
            System.out.println("Producto no existe - " + codigo);
            return false;
        }

        if (nombre != null && !nombre.isEmpty()) {
            producto.setNombre(nombre);
        }
        if (categoria != null && !categoria.isEmpty()) {
            producto.setCategoria(categoria);
        }
        if (precio > 0) {
            producto.setPrecio(precio);
        }
        if (stock >= 0) {
            producto.setCantidadStock(stock);
        }

        System.out.println("Producto actualizado exitosamente - " + codigo);
        return true;
    }

    public boolean eliminar(String codigo) {
        if (buscar(codigo) == null) {
            System.out.println("Producto no encontrado - " + codigo);
            return false;
        }
        raiz = eliminarRecursivo(raiz, codigo);
        System.out.println("Producto eliminado - " + codigo);
        return true;
    }

    private NodoAVL eliminarRecursivo(NodoAVL nodo, String codigo) {
        if (nodo == null) {
            return null;
        }

        int comparacion = codigo.compareTo(nodo.getProducto().getCodigo());

        if (comparacion < 0) {
            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), codigo));
        } else if (comparacion > 0) {
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), codigo));
        } else {
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            } else if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }

            NodoAVL sucesor = obtenerMinimo(nodo.getDerecho());
            nodo.setProducto(sucesor.getProducto());
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), sucesor.getProducto().getCodigo()));
        }

        actualizarAltura(nodo);

        int balance = obtenerBalance(nodo);

        if (balance > 1 && obtenerBalance(nodo.getIzquierdo()) >= 0) {
            return rotarDerecha(nodo);
        }

        if (balance > 1 && obtenerBalance(nodo.getIzquierdo()) < 0) {
            nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
            return rotarDerecha(nodo);
        }

        if (balance < -1 && obtenerBalance(nodo.getDerecho()) <= 0) {
            return rotarIzquierda(nodo);
        }

        if (balance < -1 && obtenerBalance(nodo.getDerecho()) > 0) {
            nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    private NodoAVL obtenerMinimo(NodoAVL nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }

    public void mostrarInventario() {
        System.out.println("\n=== INVENTARIO DEL SUPERMERCADO ===");
        System.out.println("Código     | Nombre                    | Categoría       | Precio    | Stock");
        System.out.println("-----------|---------------------------|-----------------|-----------|-------");
        if (raiz == null) {
            System.out.println("Inventario vacío");
        } else {
            mostrarInOrden(raiz);
        }
        System.out.println();
    }

    private void mostrarInOrden(NodoAVL nodo) {
        if (nodo != null) {
            mostrarInOrden(nodo.getIzquierdo());
            System.out.println(nodo.getProducto());
            mostrarInOrden(nodo.getDerecho());
        }
    }

    public int contarProductos() {
        return contarNodos(raiz);
    }

    private int contarNodos(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierdo()) + contarNodos(nodo.getDerecho());
    }
}