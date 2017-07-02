package tcc.utfpr.edu.br.soec.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.List;
import java.util.Objects;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.dto.AvaliacaoCurriculoDTO;
import tcc.utfpr.edu.br.soec.model.AvaliacaoCurriculo;
import tcc.utfpr.edu.br.soec.model.Curriculo;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

/**
 * Created by rodri on 22/06/2017.
 */

public class ListarCurriculoDialogFragment extends DialogFragment {

    private RadioButton rbCurriculo1;
    private RadioButton rbCurriculo2;
    private RadioButton rbCurriculo3;
    private Button btnEnviar;
    private Long idOportunidadeEmprego;
    private FragmentListener listarCurriculoDialogFragmentInterface;

    public interface FragmentListener{
        void ListarCurriculoDialogFragmentOnclick();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listarCurriculoDialogFragmentInterface = (FragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " deve implementar a interface FragmentListener da classe ListarCurriculoDialogFragment.");
        }
    }

    public ListarCurriculoDialogFragment(Long idOportunidadeEmprego) {
        this.idOportunidadeEmprego = idOportunidadeEmprego;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dialog_lista_curriculo, container, false);

        rbCurriculo1 = (RadioButton) view.findViewById(R.id.rbCurriculo1);
        rbCurriculo2 = (RadioButton) view.findViewById(R.id.rbCurriculo2);
        rbCurriculo3 = (RadioButton) view.findViewById(R.id.rbCurriculo3);
        btnEnviar = (Button) view.findViewById(R.id.btnEnviar);

        Jpdroid dataBase = Jpdroid.getInstance();
        final List<Curriculo> curriculos = dataBase.retrieve(Curriculo.class);

        if (curriculos.isEmpty()) {
            ToastUtils.setMsgLong(getActivity(), "Não existe currículo(s) cadastrado");
            return null;
        }

        disableRadioButtonCurriculo(curriculos);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Primeiro curriculo
                int valor = 0;

                if (rbCurriculo2.isChecked()) {
                    valor = 1;
                } else if (rbCurriculo3.isChecked()) {
                    valor = 2;
                }

                RetrofitInicializador retrofitInicializador = new RetrofitInicializador(false);
                Call<AvaliacaoCurriculo> callAvaliacao = retrofitInicializador.getAvaliacaoCurriculoService().salvar(curriculos.get(valor).getId(), idOportunidadeEmprego);
                callAvaliacao.enqueue(new Callback<AvaliacaoCurriculo>() {
                    @Override
                    public void onResponse(Call<AvaliacaoCurriculo> call, Response<AvaliacaoCurriculo> response) {
                        ToastUtils.setMsgLong(getActivity(), "Currículo enviado");
                        dismiss();
                        listarCurriculoDialogFragmentInterface.ListarCurriculoDialogFragmentOnclick();
                    }

                    @Override
                    public void onFailure(Call<AvaliacaoCurriculo> call, Throwable t) {
                        ToastUtils.setMsgLong(getActivity(), "Não foi possível enviar currículo");
                        dismiss();
                    }
                });
            }
        });

        return view;
    }

    private void disableRadioButtonCurriculo(List<Curriculo> curriculos) {
        switch (curriculos.size()) {
            case 1:
                rbCurriculo2.setEnabled(false);
                rbCurriculo3.setEnabled(false);
                break;

            case 2:
                rbCurriculo3.setEnabled(false);
                break;
        }
    }
}
