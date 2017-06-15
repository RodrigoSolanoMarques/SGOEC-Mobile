package tcc.utfpr.edu.br.soec.model;

import android.database.Cursor;

import java.util.List;

import br.com.rafael.jpdroid.annotations.Column;
import br.com.rafael.jpdroid.annotations.Entity;
import br.com.rafael.jpdroid.annotations.ForeignKey;
import br.com.rafael.jpdroid.annotations.PrimaryKey;
import br.com.rafael.jpdroid.annotations.RelationClass;
import br.com.rafael.jpdroid.core.Jpdroid;
import br.com.rafael.jpdroid.enums.RelationType;
import tcc.utfpr.edu.br.soec.dto.CidadeDTO;

/**
 * Created by rodri on 07/02/2017.
 */

@Entity
public class Cidade {

    @PrimaryKey
    @Column
    private Long _id;

    @Column
    private Long id;

    @Column
    private String nome;

    @ForeignKey(joinEntity = Estado.class, joinPrimaryKey = "_id")
    @Column
    private Long idEstado;

    @RelationClass(relationType = RelationType.OneToMany, joinColumn = "idEstado")
    private Estado estado;

    public Cidade() {
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public Cidade converterCidadeDTO(CidadeDTO cidadeDTO) {

        Cidade cidade = new Cidade();
        cidade.setId(cidadeDTO.getId());
        cidade.setNome(cidadeDTO.getNome());

        Jpdroid dataBase = Jpdroid.getInstance();
        dataBase.open();

        List<Estado> estados = dataBase.retrieve(Estado.class, "id = " + cidadeDTO.getEstado().getId());
        if(!estados.isEmpty()){
            Estado estado = estados.get(0);
            cidade.setIdEstado(estado.get_id());
            cidade.setEstado(estado);
        }
        return cidade;
    }
}
