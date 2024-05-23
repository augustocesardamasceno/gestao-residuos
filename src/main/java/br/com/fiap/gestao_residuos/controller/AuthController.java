package br.com.fiap.gestao_residuos.controller;


import br.com.fiap.gestao_residuos.dto.LoginDto;
import br.com.fiap.gestao_residuos.dto.TokenDto;
import br.com.fiap.gestao_residuos.dto.UsuarioCadastroDto;
import br.com.fiap.gestao_residuos.dto.UsuarioExibicaoDto;
import br.com.fiap.gestao_residuos.model.Usuario;
import br.com.fiap.gestao_residuos.security.TokenService;
import br.com.fiap.gestao_residuos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody
            @Valid
            LoginDto usuarioDto
    ){
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        usuarioDto.email(),
                        usuarioDto.senha());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        System.out.println(auth.getPrincipal());

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDto(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrar(@RequestBody UsuarioCadastroDto usuarioCadastroDTO){

        UsuarioExibicaoDto usuarioSalvo = null;
        usuarioSalvo = usuarioService.salvarUsuario(usuarioCadastroDTO);

        return usuarioSalvo;

    }

}
