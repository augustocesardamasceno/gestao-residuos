package br.com.fiap.gestao_residuos.service;

import br.com.fiap.gestao_residuos.exception.UsuarioNaoEcontradoException;
import br.com.fiap.gestao_residuos.model.Coleta;
import br.com.fiap.gestao_residuos.repository.ColetaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ColetaService {

    @Autowired
    private ColetaRepository coletaRepository;

    public Coleta gravar(Coleta coletaCadastrada) {
        Coleta coleta = new Coleta();
        BeanUtils.copyProperties(coletaCadastrada, coleta);
        return new Coleta();
    }

    public Coleta buscarPorId(long id) {
        return coletaRepository.findById(id)
                .map(Coleta::new)
                .orElseThrow(() -> new UsuarioNaoEcontradoException("Coleta não encontrada"));
    }

    public Page<Coleta> listarTodosAsColetas(Pageable paginacao) {
        return coletaRepository
                .findAll(paginacao)
                .map(Coleta::new);
    }

    public void excluir(Long id) {
        Optional<Coleta> coletaOptional = coletaRepository.findById(id);

        if (coletaOptional.isPresent()) {
            coletaRepository.delete(coletaOptional.get());
        } else {
            throw new RuntimeException("Contato não encontrado");
        }

    }

    public Coleta atualizar(Coleta coleta) {
        Optional<Coleta> coletaOptional = coletaRepository.findById(coleta.getId());

        if (coletaOptional.isPresent()) {
            return coletaRepository.save(coleta);
        } else {
            throw new RuntimeException("Contato não encontrado");
        }
    }

}

