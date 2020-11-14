package usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import usuario.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	
	 @Query("SELECT u FROM Usuario u where u.nombre = :nombre")
	    public List<Usuario> findAllByName(String nombre);
	 
	 @Query("SELECT u FROM Usuario u where u.apellido = :apellido")
	    public List<Usuario> findAllByLastname(String apellido);
	 
	 @Query("SELECT u FROM Usuario u where u.documento = :documento")
	    public List<Usuario> findAllByDocumento(int documento);
	 
	 @Query("SELECT u FROM Usuario u where u.email = :email")
	    public List<Usuario> findAllByEmail(String email);

	 @Query("SELECT u FROM Usuario u where u.id = :id and u.password = :password")
	 public boolean userAutentication(Long id, String password);

}
