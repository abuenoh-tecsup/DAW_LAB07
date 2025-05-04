package tecsup.edu.pe.demo1.dao;

import tecsup.edu.pe.demo1.model.Producto;
import tecsup.edu.pe.demo1.model.Venta;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.time.ZoneId;
import java.util.List;

public class VentaDAO {
    private final String url = "jdbc:mysql://localhost:3306/lab07_bueno";
    private final String user = "root";
    private final String password = "";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("❌ No se encontró el driver de MySQL", e);
        }
        return DriverManager.getConnection(url, user, password);
    }

    public List<Venta> getVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT v.id, v.cantidad, v.fecha, v.total, " +
                "p.id AS producto_id, p.nombre, p.precio " +
                "FROM venta v JOIN producto p ON v.producto_id = p.id";

        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("producto_id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));

                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setCantidad(rs.getInt("cantidad"));

                Timestamp timestamp = rs.getTimestamp("fecha");
                Date fecha = new Date(timestamp.getTime());  // Convertir Timestamp a Date
                venta.setFecha(fecha);

                venta.setTotal(rs.getDouble("total"));
                venta.setProducto(producto);

                ventas.add(venta);
            }

            System.out.println("🟢 Ventas recuperadas: " + ventas.size());
        } catch (Exception e) {
            System.out.println("❌ Error al obtener ventas de la base de datos.");
            e.printStackTrace();
        }

        return ventas;
    }

    public boolean addVenta(Venta v) {
        String sql = "INSERT INTO venta (producto_id, cantidad, total) VALUES (?, ?, ?)";
        boolean result = false;

        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, v.getProducto().getId());
            ps.setInt(2, v.getCantidad());
            ps.setDouble(3, v.getTotal());

            result = ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("❌ Error al agregar venta a la base de datos.");
            e.printStackTrace();
        }

        return result;
    }

    public boolean deleteVenta(int id) {
        String sql = "DELETE FROM venta WHERE id = ?";
        boolean result = false;

        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            result = ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("❌ Error al eliminar venta.");
            e.printStackTrace();
        }

        return result;
    }

    public Venta getVenta(int id) {
        Venta venta = null;
        String sql = "SELECT v.id, v.cantidad, v.fecha, v.total, " +
                "p.id AS producto_id, p.nombre, p.precio " +
                "FROM venta v JOIN producto p ON v.producto_id = p.id WHERE v.id = ?";

        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("producto_id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));

                venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setCantidad(rs.getInt("cantidad"));

                Timestamp timestamp = rs.getTimestamp("fecha");
                Date fecha = new Date(timestamp.getTime());  // Convertir Timestamp a Date
                venta.setFecha(fecha);

                venta.setTotal(rs.getDouble("total"));
                venta.setProducto(producto);
            }

        } catch (Exception e) {
            System.out.println("❌ Error al obtener venta.");
            e.printStackTrace();
        }

        return venta;
    }

    public boolean updateVenta(Venta v) {
        String sql = "UPDATE venta SET producto_id = ?, cantidad = ?, fecha = ?, total = ? WHERE id = ?";
        boolean result = false;

        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, v.getProducto().getId());
            ps.setInt(2, v.getCantidad());

            // Convertir java.util.Date a java.sql.Timestamp
            ps.setTimestamp(3, new Timestamp(v.getFecha().getTime()));

            ps.setDouble(4, v.getTotal());
            ps.setInt(5, v.getId());

            result = ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("❌ Error al actualizar venta en la base de datos.");
            e.printStackTrace();
        }

        return result;
    }

}
