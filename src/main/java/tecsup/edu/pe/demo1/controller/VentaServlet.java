package tecsup.edu.pe.demo1.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import tecsup.edu.pe.demo1.dao.ProductoDAO;
import tecsup.edu.pe.demo1.dao.VentaDAO;
import tecsup.edu.pe.demo1.model.Producto;
import tecsup.edu.pe.demo1.model.Venta;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/ventas")
public class VentaServlet extends HttpServlet {
    private final VentaDAO ventaDAO = new VentaDAO();
    private final ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            listarVentas(request, response);
        } else {
            switch (action) {
                case "nuevo":
                    mostrarFormularioNuevaVenta(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditarVenta(request, response);
                    break;
                case "eliminar":
                    eliminarVenta(request, response);
                    break;
                default:
                    listarVentas(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        int productoId = Integer.parseInt(request.getParameter("producto_id"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Producto producto = productoDAO.getProducto(productoId);
        double total = producto.getPrecio() * cantidad;

        Venta venta = new Venta();
        venta.setProducto(producto);
        venta.setCantidad(cantidad);
        venta.setFecha(new java.util.Date());
        venta.setTotal(total);

        if ("actualizar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            venta.setId(id);
            ventaDAO.updateVenta(venta);
        } else {
            ventaDAO.addVenta(venta);
        }

        response.sendRedirect("ventas");
    }

    private void mostrarFormularioNuevaVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> productos = productoDAO.getProductos();
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("agregarVenta.jsp").forward(request, response);
    }

    private void mostrarFormularioEditarVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Venta venta = ventaDAO.getVenta(id);
        List<Producto> productos = productoDAO.getProductos();

        request.setAttribute("venta", venta);
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("editarVenta.jsp").forward(request, response);
    }

    private void eliminarVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ventaDAO.deleteVenta(id);
        response.sendRedirect("ventas");
    }

    private void listarVentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Venta> ventas = ventaDAO.getVentas();
        request.setAttribute("ventas", ventas);
        request.getRequestDispatcher("listarVentas.jsp").forward(request, response);
    }
}
