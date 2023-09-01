package com.latam.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.tienda.dao.CategoriaDao;
import com.latam.tienda.dao.ProductoDao;
import com.latam.tienda.modelo.Categoria;
import com.latam.tienda.modelo.Producto;
import com.latam.tienda.utils.JPAUtils;

public class RegistroDeProducto {

	public static void main(String[] args) {

		registrarProducto();
		EntityManager em = JPAUtils.getEntityManager();

		ProductoDao productoDao = new ProductoDao(em);

		Producto producto = productoDao.consultaPorId(1l);

		System.out.println(producto.getNombre());

		BigDecimal precio  = productoDao.consultarPrecioPorNombreDeProducto("Xaomi Redmi Note 8");

		System.out.println(precio);

	}

	private static void registrarProducto() {
		EntityManager em = JPAUtils.getEntityManager();

		ProductoDao productoDao = new ProductoDao(em);
		Categoria celulares = new Categoria("CELULARES");

		Producto celular = new Producto("Xaomi Redmi Note 8", "Muy bueno", new BigDecimal("1500"), celulares);

		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();

		categoriaDao.guardar(celulares);
		productoDao.guardar(celular);

		em.getTransaction().commit();
		em.close();
	}

}
