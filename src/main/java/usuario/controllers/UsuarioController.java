package usuario.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import usuario.model.Usuario;
import usuario.repository.UsuarioRepository;

@RestController
@RequestMapping("usuarios")
//@Api(value = "UsuarioControllerJpa", description = "REST API Usuario description")
public class UsuarioController {
	
	@Qualifier("usuarioRepository")
	@Autowired
	private final UsuarioRepository repository;
	
	public UsuarioController(@Qualifier("usuarioRepository") UsuarioRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/")
	public Iterable<Usuario> getUsuarios() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Usuario> one(@PathVariable Long id) {
		return repository.findById(id);
	}
	
	@PostMapping("/")
	public Usuario newUsuario(@RequestBody Usuario u) {
		return repository.save(u);
	}
	
	@PutMapping("/{id}")
	Usuario replaceUsuario(@RequestBody Usuario newUsuario, @PathVariable Long id) {
		return repository.findById(id).map(usuario -> {
			usuario.setNombre(newUsuario.getNombre());
			usuario.setApellido(newUsuario.getApellido());
			usuario.setEmail(newUsuario.getEmail());
			usuario.setPassword(newUsuario.getPassword());
			return repository.save(usuario);
		}).orElseGet(() -> {
			newUsuario.setId(id);
			return repository.save(newUsuario);
		});
	}
	
	@DeleteMapping("/{id}")
	void deleteUsuario(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/{id}/{password}")
	boolean getAutentication(@PathVariable Long id, @PathVariable String password) {
		return repository.userAutentication(id,password);
	}
	
}
