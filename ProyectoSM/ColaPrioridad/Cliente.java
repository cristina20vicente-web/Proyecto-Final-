public class Cliente {
    private String nombre;
    private TipoCliente tipo;
    private int numeroProductos;
    private long horaLlegada;

    public enum TipoCliente {
        VIP(1),
        ADULTO_MAYOR(2),
        REGULAR(3);

        private final int prioridad;

        TipoCliente(int prioridad) {
            this.prioridad = prioridad;
        }

        public int getPrioridad() {
            return prioridad;
        }
    }

    public Cliente(String nombre, TipoCliente tipo, int numeroProductos) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.numeroProductos = numeroProductos;
        this.horaLlegada = System.currentTimeMillis();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public int getNumeroProductos() {
        return numeroProductos;
    }

    public void setNumeroProductos(int numeroProductos) {
        this.numeroProductos = numeroProductos;
    }

    public long getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(long horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public int getTiempoAtencion() {
        return numeroProductos * 2;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %d productos)", nombre, tipo, numeroProductos);
    }
}