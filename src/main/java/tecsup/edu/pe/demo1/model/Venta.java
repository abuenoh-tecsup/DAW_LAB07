package tecsup.edu.pe.demo1.model;
import java.util.Date;

public class Venta {
    private int id;
    private Producto producto; // Relación con Producto
    private int cantidad;
    private Date fecha;
    private double total;

    public Venta() {}

    public Venta(int id, Producto producto, int cantidad, Date fecha, double total) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
