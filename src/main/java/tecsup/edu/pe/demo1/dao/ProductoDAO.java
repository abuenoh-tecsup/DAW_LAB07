package tecsup.edu.pe.demo1.dao;

import tecsup.edu.pe.demo1.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private final String url = "jdbc:mysql://localhost:3306/lab07_bueno";
    private final String user = "root";
    private final String password = "";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            throw new RuntimeException("❌ No se encontró el driver de MySQL", e);
        }
        return DriverManager.getConnection(url, user, password);
    }

    public List<Producto> getProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                productos.add(p);
            }

            System.out.println("🟢 Productos recuperados: " + productos.size());
        } catch (Exception e) {
            System.out.println("❌ Error al obtener productos de la base de datos.");
            e.printStackTrace();
        }

        return productos;
    }

    public Producto getProducto(int id) {
        String sql = "SELECT * FROM producto WHERE id = ?";
        Producto p = null;

        try (Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
            }

        } catch (Exception e) {
            System.out.println("❌ Error al obtener producto de la base de datos.");
        }

        return p;
    }

    public boolean addProducto(Producto p) {
        String sql = "INSERT INTO producto (nombre, precio) VALUES(?,?)";
        boolean result = false;

        try (Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());

            result = ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("❌ Error al agregar producto a la base de datos.");
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateProducto(Producto p) {
        String sql = "UPDATE producto SET nombre = ?, precio = ? WHERE id = ?";
        boolean result = false;

        try (Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getId());

            result = ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("❌ Error al actualizar producto a la base de datos.");
        }
        return result;
    }

    public boolean deleteProducto(int id) {
        String sql = "DELETE FROM producto WHERE id = ?";
        boolean result = false;

        try (Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar producto a la base de datos.");
        }
        return result;
    }
}
