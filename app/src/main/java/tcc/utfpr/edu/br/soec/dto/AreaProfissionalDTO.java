package tcc.utfpr.edu.br.soec.dto;

/**
 * Created by rodri on 07/06/2017.
 */

public class AreaProfissionalDTO {

        private Long id;

        private String nome;

        private String descricao;

        public AreaProfissionalDTO() {
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


}
