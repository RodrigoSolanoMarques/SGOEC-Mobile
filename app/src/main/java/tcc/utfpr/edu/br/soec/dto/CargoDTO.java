package tcc.utfpr.edu.br.soec.dto;

/**
 * Created by rodri on 15/06/2017.
 */

public class CargoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private EmpresaDTO empresa;
    private AreaProfissionalDTO areaProfissional;

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

    public EmpresaDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDTO empresa) {
        this.empresa = empresa;
    }

    public AreaProfissionalDTO getAreaProfissional() {
        return areaProfissional;
    }

    public void setAreaProfissional(AreaProfissionalDTO areaProfissional) {
        this.areaProfissional = areaProfissional;
    }
}
