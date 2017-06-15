package tcc.utfpr.edu.br.soec.dto;

import java.io.Serializable;

/**
 * Created by rodri on 09/06/2017.
 */

public class CidadeDTO implements Serializable {

    private Long id;
    private String nome;
    private EstadoDTO estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoDTO estado) {
        this.estado = estado;
    }
}
