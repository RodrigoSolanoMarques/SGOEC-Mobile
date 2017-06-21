package tcc.utfpr.edu.br.soec.sincronizacao;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import tcc.utfpr.edu.br.soec.sincronizacao.impl.SincronizacaoAreaProfissional;
import tcc.utfpr.edu.br.soec.sincronizacao.impl.SincronizacaoCandidato;
import tcc.utfpr.edu.br.soec.sincronizacao.impl.SincronizacaoCargo;
import tcc.utfpr.edu.br.soec.sincronizacao.impl.SincronizacaoCidade;
import tcc.utfpr.edu.br.soec.sincronizacao.impl.SincronizacaoContaUsuario;
import tcc.utfpr.edu.br.soec.sincronizacao.impl.SincronizacaoCurriculo;
import tcc.utfpr.edu.br.soec.sincronizacao.impl.SincronizacaoCursoComplementar;
import tcc.utfpr.edu.br.soec.sincronizacao.impl.SincronizacaoEmpresa;
import tcc.utfpr.edu.br.soec.sincronizacao.impl.SincronizacaoEstado;
import tcc.utfpr.edu.br.soec.sincronizacao.impl.SincronizacaoExperienciaProfissional;
import tcc.utfpr.edu.br.soec.sincronizacao.impl.SincronizacaoFormacao;
import tcc.utfpr.edu.br.soec.sincronizacao.impl.SincronizacaoPessoa;


public class ListaSincronizacao {

    private List<SincronizacaoAbstract> listaSincronizacao;
    private boolean isCargaInicial;

    public ListaSincronizacao(boolean isCargaInicial) {
        this.isCargaInicial = isCargaInicial;
    }

    public void sincronizarTudo(Context context) throws Exception {
        try {
            listaSincronizacao = new ArrayList<>();

            if (isCargaInicial) {
                listaSincronizacao.add(new SincronizacaoEstado(context));
                listaSincronizacao.add(new SincronizacaoCidade(context));
                listaSincronizacao.add(new SincronizacaoAreaProfissional(context));
                listaSincronizacao.add(new SincronizacaoEmpresa(context));
                listaSincronizacao.add(new SincronizacaoCargo(context));
            } else {
                listaSincronizacao.add(new SincronizacaoContaUsuario(context));
                listaSincronizacao.add(new SincronizacaoPessoa(context));
                listaSincronizacao.add(new SincronizacaoCandidato(context));
                listaSincronizacao.add(new SincronizacaoFormacao(context));
                listaSincronizacao.add(new SincronizacaoCursoComplementar(context));
                listaSincronizacao.add(new SincronizacaoExperienciaProfissional(context));
                listaSincronizacao.add(new SincronizacaoCurriculo(context));
            }

            for (SincronizacaoAbstract sinc : listaSincronizacao) {
                sinc.sincronizar();
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
