package com.example.userBean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

@Local
@Stateless
public class UsuarioBean {

	@PersistenceContext
	private EntityManager em;

	public UsuarioBean() {
	}

	public Usuario ingresarUsuario(Usuario usuario) throws ServiciosException {
		try {
			em.persist(usuario);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al dar de alta un usuario");
		}
		return usuario;
	}

	public String eliminarUsuario(Long id) throws ServiciosException {
		try {
			Usuario usuario = em.find(Usuario.class, id);
			em.remove(usuario);
			return "eliminado correctamente";
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al borrar un usuario");
		}
	}

	public List<Usuario> obtenerTodosUsuarios() throws ServiciosException {
		List<Usuario> usuarios = null;
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u ORDER BY u.nombre", Usuario.class);
			usuarios = query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al obtener todos los Usuarios");
		}
		return usuarios;
	}

	public void editarUsuario(Usuario usuario) throws ServiciosException {
		try {
			em.merge(usuario);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar el usuario");
		}
	}

	public String actualizar(long id_usuario, String contrasenia) throws ServiciosException {
		try {
			Usuario usuarioModifi = em.find(Usuario.class, id_usuario);
			usuarioModifi.setNombre(usuarioModifi.getNombre());
			usuarioModifi.setApellido(usuarioModifi.getApellido());
			usuarioModifi.setPerfil(usuarioModifi.getPerfil());
			usuarioModifi.setUsuario(usuarioModifi.getUsuario());
			usuarioModifi.setContraseña(contrasenia);
			em.merge(usuarioModifi);
			em.flush();
			return "actualizado";
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al actualizar el Usuario ");
		}
	}

	public Usuario buscarUsuarioPorId(long ID_USUARIO) throws ServiciosException {
		try {
			Usuario usuario = em.find(Usuario.class, ID_USUARIO);
			return usuario;
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al buscar el usuario por Id ");
		}
	}

	public String obtenerUsuarios(String usuario, String contraseña) throws ServiciosException {
		String stringPerfil = "";
		try {
			TypedQuery<Usuario> query = this.em.createQuery("SELECT u FROM Usuario u", Usuario.class);
			List<Usuario> listaU = query.getResultList();

			for (Usuario cadena : listaU) {
				if (cadena.getUsuario().equals(usuario) && cadena.getContraseña().equals(contraseña)) {
					stringPerfil = String.valueOf(cadena.getPerfil());
				}
			}
			return stringPerfil;
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al obtener los Usuarios");
		}
	}
}
