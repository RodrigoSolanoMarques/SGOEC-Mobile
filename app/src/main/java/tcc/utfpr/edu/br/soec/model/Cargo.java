package tcc.utfpr.edu.br.soec.model;

import java.util.List;

import br.com.rafael.jpdroid.annotations.Column;
import br.com.rafael.jpdroid.annotations.Entity;
import br.com.rafael.jpdroid.annotations.ForeignKey;
import br.com.rafael.jpdroid.annotations.PrimaryKey;
import br.com.rafael.jpdroid.annotations.RelationClass;
import br.com.rafael.jpdroid.core.Jpdroid;
import br.com.rafael.jpdroid.enums.RelationType;
import tcc.utfpr.edu.br.soec.dto.CargoDTO;

@Entity
public class Cargo {

	@PrimaryKey
	@Column
	private Long _id;

    @Column
    private Long id;

	@ForeignKey(joinEntity = Empresa.class, joinPrimaryKey = "_id", deleteCascade = true)
	@Column
	private Long idEmpresa;

	@ForeignKey(joinEntity = AreaProfissional.class, joinPrimaryKey = "_id", deleteCascade = true)
	@Column
	private Long idAreaProfissional;

	@Column
	private String nome;

	@Column
	private String descricao;

	/* Relacionamentos */

	@RelationClass(relationType = RelationType.OneToOne, joinColumn = "idEmpresa")
	private Empresa empresa;

	@RelationClass(relationType = RelationType.OneToMany, joinColumn = "idAreaProfissional")
	private AreaProfissional areaProfissional;

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

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Long getIdAreaProfissional() {
        return idAreaProfissional;
    }

    public void setIdAreaProfissional(Long idAreaProfissional) {
        this.idAreaProfissional = idAreaProfissional;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public AreaProfissional getAreaProfissional() {
        return areaProfissional;
    }

    public void setAreaProfissional(AreaProfissional areaProfissional) {
        this.areaProfissional = areaProfissional;
    }

    public Cargo converterCargoDTO(CargoDTO cargoDTO){

        Cargo cargo = new Cargo();
        cargo.setId(cargoDTO.getId());
        cargo.setNome(cargoDTO.getNome());
        cargo.setDescricao(cargoDTO.getDescricao());

        Jpdroid dataBase = Jpdroid.getInstance();
        dataBase.open();

        List<AreaProfissional> areaProfissionals = dataBase.retrieve(AreaProfissional.class, "id = " + cargoDTO.getAreaProfissional().getId());
        if(!areaProfissionals.isEmpty()){
            AreaProfissional areaProfissional = areaProfissionals.get(0);
            cargo.setIdAreaProfissional(areaProfissional.get_id());
            cargo.setAreaProfissional(areaProfissional);
        }

        List<Empresa> empresas = dataBase.retrieve(Empresa.class, "id = " + cargoDTO.getEmpresa().getId());
        if(!empresas.isEmpty()){
            Empresa empresa = empresas.get(0);
            cargo.setIdEmpresa(empresa.get_id());
            cargo.setEmpresa(empresa);
        }

        return cargo;
    }
}
