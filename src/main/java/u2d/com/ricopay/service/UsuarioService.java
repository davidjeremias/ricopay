package u2d.com.ricopay.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import u2d.com.ricopay.dto.UsuarioDTO;
import u2d.com.ricopay.exception.NegocioException;
import u2d.com.ricopay.model.Usuario;
import u2d.com.ricopay.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    ModelMapper modelMapper;

    public UsuarioDTO save(UsuarioDTO usuarioDTO) throws NegocioException {
        validaDuplicidadeCPF(usuarioDTO);
        Usuario retorno = repository.save(modelMapper.map(usuarioDTO, Usuario.class));
        return modelMapper.map(retorno, UsuarioDTO.class);
    }

    private void validaDuplicidadeCPF(UsuarioDTO usuarioDTO) throws NegocioException {
        Optional<Usuario> user = Optional.of(repository.findByCpf(usuarioDTO.getCpf()));
        if(user.isPresent()){
            throw new NegocioException("Usuario já cadastrado com esse cpf");
        }
    }

    public List<UsuarioDTO> findAll() {
        List<Usuario> listaUsuario = repository.findAll();
        List<UsuarioDTO> listaRetorno = new ArrayList<>();
        listaUsuario.forEach(e -> {
            listaRetorno.add(modelMapper.map(e, UsuarioDTO.class));
        });
        return listaRetorno;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
