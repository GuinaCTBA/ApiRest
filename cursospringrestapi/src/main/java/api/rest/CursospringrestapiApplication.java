package api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//Esse conjunto de anotações é tudo que precisa para um projeto spring boot rodar em qualquer arquitetura
@SpringBootApplication //Ativa uma aplicação Sprig boot
@EntityScan(basePackages = {"curso.api.rest.model"}) //Todas as classes que estiverem nesse pacote ele cria no banco automático.
@ComponentScan(basePackages = {"curso.*"}) //Tudo que estiver dentro da pasta curso ele vai sair lendo e configurando sozinho.
@EnableJpaRepositories(basePackages = {"curso.api.rest.repository"}) //Ativa todo funcionamento e diz a pasta que vai ficar toda persistência.
@EnableTransactionManagement//Vai controlar para não ter erros de transações.
@EnableWebMvc //Posso trabalhar com várias arquiteturas ao mesmo tempo.. mesmo sendo Rest posso utilizar MVC junto.
@RestController //Dá a permissão toda aos controles Rest.
@EnableAutoConfiguration //O Spring configura todo projeto para gente.

public class CursospringrestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursospringrestapiApplication.class, args);
	}

}
