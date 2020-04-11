package curso.api.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuario")
public class IndexController {

	@Autowired // Se eu utilizase CDI usaria @Inject
	private UsuarioRepository usuarioRepository;

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Usuario> init(@PathVariable(value = "id") Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> usuario() {
		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
		for (int i = 0; i < usuario.getTelefones().size(); i ++) {
			usuario.getTelefones().get(i).setUsuario(usuario);
		}
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}

	// Atualizar.. // O .save tb atualiza, quando mandamos um id existente ele sabe
	// que existe
	// e atualiza, se n mandamos um id ele sabe que deve criar um novo
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
		/* Posso colocar várias rotinas, desde enviar emails etc */
		for (int i = 0; i < usuario.getTelefones().size(); i ++) {
			usuario.getTelefones().get(i).setUsuario(usuario);
		}
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}

	/* Com parametros */
	@PutMapping(value = "/{iduser}/idvenda/{idvenda}", produces = "application/json")
	public ResponseEntity atualizarVenda(@PathVariable Long iduser, @PathVariable Long idvenda) {

		return new ResponseEntity("Venda atualizada", HttpStatus.OK);
	}

	// Exemplo venda usuario
	@PostMapping(value = "/vendausuario", produces = "application/json")
	public ResponseEntity<Usuario> cadastrarvenda(@RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}

	// Exemplo venda usuario com parametros
	@PostMapping(value = "/{iduser}/idvenda/{idvenda}", produces = "application/json")
	public ResponseEntity cadastrarvenda(@PathVariable Long iduser, @PathVariable Long idvenda) {

		return new ResponseEntity("id user :" + iduser + " idvenda :" + idvenda, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String delete(@PathVariable("id") Long id) {
		usuarioRepository.deleteById(id);
		return "Deletado";
	}

}

/*
 * TODOS COMENTARIOS SOBRE AS LINHAS ACIMA O REST faz tudo com json, o spring
 * faz tudo automaticamente
 * 
 * @RestController/*Definição arquitetura REST
 * 
 * @RequestMapping(value ="/usuario") public class IndexController {
 * 
 * //Serviço RESTful
 * 
 * @GetMapping(value = "/", produces = "application/json") //Caso n seja
 * colocado valor esse defaultValue injeta um valor para não dar erro //Também
 * posso colocar no lugar desse defaultValue required = false, dai ele n cobra
 * uma entrada public ResponseEntity<Usuario> init(@RequestParam (value ="nome",
 * defaultValue = "Vazio") String nome) {
 * 
 * System.out.println("Parametro sendo recebido" + nome);
 * 
 * return new ResponseEntity("Olá usuário REST Spring Boot seu nome é: " + nome
 * , HttpStatus.OK);
 * 
 * } }
 */