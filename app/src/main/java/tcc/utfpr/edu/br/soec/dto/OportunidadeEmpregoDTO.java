package tcc.utfpr.edu.br.soec.dto;

/**
 * Created by rodri on 15/06/2017.
 */

public class OportunidadeEmpregoDTO {

    private Long id;
    private CidadeDTO cidade;
    private CargoDTO cargo;
    private String descricao;
    private Boolean isSalario;
    private String salario;
    private String cargaHoraria;
    private String beneficios;
    private Boolean isFinalizado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CidadeDTO getCidade() {
        return cidade;
    }

    public void setCidade(CidadeDTO cidade) {
        this.cidade = cidade;
    }

    public CargoDTO getCargo() {
        return cargo;
    }

    public void setCargo(CargoDTO cargo) {
        this.cargo = cargo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getSalario() {
        return isSalario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public Boolean getFinalizado() {
        return isFinalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        isFinalizado = finalizado;
    }

    public void setSalario(Boolean salario) {
        isSalario = salario;
    }
}
