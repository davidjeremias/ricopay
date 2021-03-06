package u2d.com.ricopay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import u2d.com.ricopay.dto.UsuarioDTO;
import u2d.com.ricopay.exception.NegocioException;
import u2d.com.ricopay.service.UsuarioService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO usuarioDTO) throws NegocioException {
        UsuarioDTO retorno = service.save(usuarioDTO);
        return new ResponseEntity<>(retorno, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        List<UsuarioDTO> listRetorno = service.findAll();
        return !listRetorno.isEmpty() || listRetorno != null ? new ResponseEntity<List<UsuarioDTO>>(listRetorno, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
