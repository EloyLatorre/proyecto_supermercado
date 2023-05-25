package com.hibernate.dao;

import com.hibernate.model.Categoria;
import com.hibernate.model.Oferta;
import com.hibernate.model.Producto;
import com.hibernate.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlmacenDAO {

	/**
	 * Inserta productos en la base de datos
	 * 
	 * @param producto - objeto producto
	 */
	public void insertProductos(Producto producto) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(producto);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza un producto existente en la base de datos
	 * 
	 * @param producto - objeto producto
	 */
	public void updateProductos(Producto producto) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(producto);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un producto de la base de datos
	 * 
	 * @param id - identificador del producto
	 */
	public void deleteProductos(int id) {
		Transaction transaction = null;
		Producto producto = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			producto = session.get(Producto.class, id);
			session.remove(producto);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Selecciona un producto de la base de datos por su id
	 * 
	 * @param id - identificador del producto
	 * @return Producto seleccionado
	 */
	public Producto selectProductosById(int id) {
		Transaction transaction = null;
		Producto producto = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			producto = session.get(Producto.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return producto;
	}

	/**
	 * Selecciona todos los productos de la base de datos
	 * 
	 * @return Lista de productos
	 */
	public List<Producto> selectAllProductos() {
		Transaction transaction = null;
		List<Producto> productos = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			productos = session.createQuery("from Producto", Producto.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return productos;
	}

	/**
	 * Selecciona productos que no tienen stock de la base de datos
	 * 
	 * @return Lista de productos sin stock
	 */
	public List<Producto> selectProductosSinStock() {
		Transaction transaction = null;
		List<Producto> productosSinStock = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			productosSinStock = session.createQuery("from Producto where cantidad = 0", Producto.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return productosSinStock;
	}

	/**
	 * Selecciona todas las categorías de la base de datos
	 * 
	 * @return Lista de categorías
	 */
	public List<Categoria> selectAllCategorias() {
		List<Categoria> categorias = new ArrayList<>();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Categoria> query = session.createQuery("FROM Categoria", Categoria.class);
			categorias = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return categorias;
	}

	/**
	 * Inserta una categoría en la base de datos
	 * 
	 * @param categoria - objeto categoría
	 */
	public void insertCategorias(Categoria categoria) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(categoria);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza una categoría existente en la base de datos
	 * 
	 * @param categoria - objeto categoría
	 */
	public void updateCategorias(Categoria categoria) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(categoria);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Elimina una categoría de la base de datos
	 * 
	 * @param id - identificador de la categoría
	 */
	public void deleteCategorias(int id) {
		Transaction transaction = null;
		Categoria categoria = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			categoria = session.get(Categoria.class, id);
			session.remove(categoria);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Selecciona todas las ofertas de la base de datos
	 * 
	 * @return Lista de ofertas
	 */
	public List<Oferta> selectAllOfertas() {
		Transaction transaction = null;
		List<Oferta> ofertas = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			ofertas = session.createQuery("from Oferta", Oferta.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return ofertas;
	}

	/**
	 * Inserta una oferta en la base de datos
	 * 
	 * @param oferta - objeto oferta
	 */
	public void insertOferta(Oferta oferta) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(oferta);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza una oferta existente en la base de datos
	 * 
	 * @param oferta - objeto oferta
	 */
	public void updateOferta(Oferta oferta) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(oferta);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Elimina una oferta de la base de datos
	 * 
	 * @param idOferta - identificador de la oferta
	 */
	public void deleteOferta(int idOferta) {
		Transaction transaction = null;
		Oferta oferta = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			oferta = session.get(Oferta.class, idOferta);
			session.remove(oferta);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Verifica si una oferta ya existe
	 * @param producto - objeto producto
	 * @param fechaInicio - fecha de inicio de la oferta
	 * @param fechaFin - fecha fin de la oferta
	 * @return
	 */
	public boolean existsOfertaForProductAndDates(Producto producto, Date fechaInicio, Date fechaFin) {
	    Transaction transaction = null;
	    List<Oferta> ofertas = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<Oferta> query = builder.createQuery(Oferta.class);
	        Root<Oferta> root = query.from(Oferta.class);
	        query.select(root).where(
	                builder.equal(root.get("producto"), producto),
	                builder.equal(root.get("fechaInicio"), fechaInicio),
	                builder.equal(root.get("fechaFin"), fechaFin)
	        );
	        ofertas = session.createQuery(query).getResultList();
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return ofertas != null && !ofertas.isEmpty();
	}


	/**
	 * Verifica si un producto ya existe en la base de datos
	 * 
	 * @param nombreProducto - nombre del producto
	 * @return verdadero si existe, falso en caso contrario
	 */
	public boolean productoExiste(String nombreProducto) {
		Transaction transaction = null;
		boolean existe = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Producto> criteriaQuery = criteriaBuilder.createQuery(Producto.class);
			Root<Producto> root = criteriaQuery.from(Producto.class);

			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("nombre"), nombreProducto));

			Query<Producto> query = session.createQuery(criteriaQuery);
			existe = (query.uniqueResult() != null);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return existe;
	}

}
