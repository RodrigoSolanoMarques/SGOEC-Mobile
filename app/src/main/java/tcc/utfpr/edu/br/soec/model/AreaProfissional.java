package tcc.utfpr.edu.br.soec.model;

import br.com.rafael.jpdroid.annotations.Column;
import br.com.rafael.jpdroid.annotations.Entity;
import br.com.rafael.jpdroid.annotations.PrimaryKey;
import tcc.utfpr.edu.br.soec.dto.AreaProfissionalDTO;

@Entity
public class AreaProfissional {

    @PrimaryKey
    @Column
    private Long _id;

    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private String descricao;

    public AreaProfissional() {
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public AreaProfissional converterAreaProfissionalDTO(AreaProfissionalDTO areaProfissionalDTO) {
        AreaProfissional areaProfissional = new AreaProfissional();

        areaProfissional.setId(areaProfissionalDTO.getId());
        areaProfissional.setNome(areaProfissionalDTO.getNome());
        areaProfissional.setDescricao(areaProfissionalDTO.getDescricao());

        return areaProfissional;
    }
}
