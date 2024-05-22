package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.Usuario;
import br.com.fiap.gestao_residuos.model.UsuarioRole;

public record UsuarioExibicaoDto(
        Long usuarioId,
        String nome,
        String email,
        UsuarioRole role
) {
    public UsuarioExibicaoDto(Usuario usuario) {
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
