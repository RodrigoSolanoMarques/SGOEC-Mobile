package tcc.utfpr.edu.br.soec.model;

import br.com.rafael.jpdroid.annotations.Column;
import br.com.rafael.jpdroid.annotations.Entity;
import br.com.rafael.jpdroid.annotations.PrimaryKey;

/**
 * Created by rodri on 07/02/2017.
 */

@Entity
public class Estado {

    @PrimaryKey
    @Column
    private Long _id;

    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private String uf;

    public Estado() {
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
