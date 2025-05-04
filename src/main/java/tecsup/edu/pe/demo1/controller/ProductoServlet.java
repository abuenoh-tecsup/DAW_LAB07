package tecsup.edu.pe.demo1.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tecsup.edu.pe.demo1.model.Producto;
import tecsup.edu.pe.demo1.dao.ProductoDAO;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    private final ProductoDAO productoDAO = new ProductoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            listarProductos(request, response);
        } else{
            switch (action) {
                case "nuevo":
                    request.getRequestDispatcher("agregarProducto.jsp").forward(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditarProducto(request, response);
                    break;
                case "eliminar":
                    eliminarProducto(request, response);
                    break;
                default:
                    listarProductos(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));

        Producto p = new Producto();
        p.setId(id);
        p.setNombre(nombre);
        p.setPrecio(precio);

        String accion = request.getParameter("action");
        if ("agregar".equals(accion)) {
            productoDAO.addProducto(p);
        } else if ("actualizar".equals(accion)) {
            productoDAO.updateProducto(p);
        }

        response.sendRedirect("productos");
    }

    private void mostrarFormularioEditarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Producto p = productoDAO.getProducto(id);
        request.setAttribute("producto", p);
        request.getRequestDispatcher("editarProducto.jsp").forward(request, response);
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productoDAO.deleteProducto(id);
        response.sendRedirect("productos");
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> productos = productoDAO.getProductos();
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("listarProductos.jsp").forward(request, response);
    }
}
