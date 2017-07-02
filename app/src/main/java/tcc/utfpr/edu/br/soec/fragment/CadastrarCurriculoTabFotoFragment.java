package tcc.utfpr.edu.br.soec.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import br.com.rafael.jpdroid.exceptions.JpdroidException;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.model.Candidato;
import tcc.utfpr.edu.br.soec.model.Curriculo;
import tcc.utfpr.edu.br.soec.model.Pessoa;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

import static android.R.attr.value;

public class CadastrarCurriculoTabFotoFragment extends Fragment {

    public static final int CADASTRAR_CURRICULO_TAB_CAMERA = 123;
    public static final int CADASTRAR_CURRICULO_TAB_GALERIA = 456;

    private ImageView ivFoto;
    private Button btnCamera;
    private Button btnGaleria;
    private Button btnSalvar;
    private String caminhoFoto;
    private byte[] bitmapFoto;
    private Boolean isCamera;
    private Boolean isGaleria;


    private Jpdroid dataBase;
    private Curriculo curriculo;
    private Context context;
    private FragmentListener mFragmentListener;

    public interface FragmentListener {
        void onClickCadastrarCurriculoTabFotoFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mFragmentListener = (FragmentListener) context;
            this.context = context;

            dataBase = Jpdroid.getInstance();
            dataBase.setContext(context);
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " deve implementar a interface FragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = getViews(inflater, container);

//        if (isCamera != null && isCamera == true) {
//            recuperarArgumentos(savedInstanceState);
//        } else if (isGaleria != null && isGaleria) {
//            savedInstanceState.clear();
//        } else {
//            recuperarArgumentos(getArguments());
//
//            if (curriculo.getCandidato() != null) {
//                caminhoFoto = curriculo.getCandidato().getPessoa().getFoto();
//                if (!"".equals(caminhoFoto) && !("null".equals(caminhoFoto)) && (caminhoFoto != null)) {
//                    //carregarFoto();
//                    //Bitmap bitmap = ((BitmapDrawable)ivFoto.getDrawable()).getBitmap();
//                    Bitmap bitmap = convertByteArrayToBitmap(caminhoFoto);
//                    ivFoto.setImageBitmap(bitmap);
//                }
//            }
//        }

        setOnClickInView();
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            recuperarArgumentos(savedInstanceState);
        } else {
            recuperarArgumentos(getArguments());
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CADASTRAR_CURRICULO_TAB_CAMERA) {
                carregarFoto();
            } else {
                Uri selectedImage = data.getData();
                //caminhoFoto = getImagePath(selectedImage);
                ivFoto.setImageURI(selectedImage);
            }
        }
    }

    public String getImagePath(Uri contentUri) {
        String[] campos = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContext().getContentResolver().query(contentUri, campos, null, null, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {

        if (isCamera) {
            outState.putSerializable("caminhoFoto", caminhoFoto);
            outState.putSerializable("curriculo", curriculo);
        } else {
            //outState.clear();
            outState.putSerializable("curriculo", curriculo);
        }

        super.onSaveInstanceState(outState);
    }

    private void setOnClickInView() {
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                /* Recupera o caminho que a aplicação pode salvar */
                caminhoFoto = getContext().getExternalFilesDir(null) + "/" + System.currentTimeMillis() + "foto.jpg";

                /* Cria um arquivo no caminho especificado */
                File arquivoFoto = new File(caminhoFoto);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(Intent.createChooser(intent, "Tire uma foto"), CADASTRAR_CURRICULO_TAB_CAMERA);

                isCamera = true;
                isGaleria = false;
            }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), CADASTRAR_CURRICULO_TAB_GALERIA);
                isCamera = false;
                isGaleria = true;
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    salvarFoto();
                } catch (JpdroidException e) {
                    e.printStackTrace();
                    ToastUtils.setMsgLong(context, "Nao foi possivel salvar");
                }
            }
        });
    }

    @NonNull
    private View getViews(LayoutInflater inflater, ViewGroup container) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_cadastrar_curriculo_tab_foto, container, false);

        ivFoto = (ImageView) layout.findViewById(R.id.fragment_cadastrar_curriculo_tab_foto_ivFoto);
        btnCamera = (Button) layout.findViewById(R.id.fragment_cadastrar_curriculo_tab_foto_btnCamera);
        btnGaleria = (Button) layout.findViewById(R.id.fragment_cadastrar_curriculo_tab_foto_btnGaleria);
        btnSalvar = (Button) layout.findViewById(R.id.fragment_cadastrar_curriculo_tab_foto_btnSalvar);
        return layout;
    }

    private void recuperarArgumentos(Bundle bundle) {
        if (bundle == null) {
            return;
        }

        if (bundle.containsKey("curriculo")) {
            curriculo = (Curriculo) bundle.getSerializable("curriculo");
        }

//        if (bundle.containsKey("caminhoFoto")) {
//            caminhoFoto = bundle.getString("caminhoFoto");
//        }
    }

    private void salvarFoto() throws JpdroidException {
        if (curriculo.get_id() == null) {

            Candidato candidato = curriculo.getCandidato();

            Pessoa pessoa = candidato.getPessoa();
            //pessoa.setFoto(caminhoFoto);

            Bitmap bitmap = ((BitmapDrawable) ivFoto.getDrawable()).getBitmap();
            byte[] foto = convertBitmapToArrayByte(bitmap);
            pessoa.setFoto(Base64.encodeToString(foto, 0));

            dataBase.persist(curriculo);

            if (curriculo.get_id() == null) {

                Cursor cursorCurriculo = dataBase.rawQuery("SELECT MAX(_ID) as ID  FROM CURRICULO", null);
                Cursor cursorCandidato = dataBase.rawQuery("SELECT MAX(_ID) as ID  FROM CANDIDATO", null);

                if (cursorCurriculo.moveToFirst()) {
                    long id = cursorCurriculo.getLong(cursorCurriculo.getColumnIndex("ID"));
                    curriculo.set_id(id);
                }

                if (cursorCandidato.moveToFirst()) {
                    long id = cursorCandidato.getLong(cursorCandidato.getColumnIndex("ID"));
                    candidato.set_id(id);
                }
            }

            mFragmentListener.onClickCadastrarCurriculoTabFotoFragment();
            return;
        }

        Pessoa pessoa = curriculo.getCandidato().getPessoa();
        //pessoa.setFoto(caminhoFoto);

        Bitmap bitmap = ((BitmapDrawable) ivFoto.getDrawable()).getBitmap();
        byte[] foto = convertBitmapToArrayByte(bitmap);
        pessoa.setFoto(Base64.encodeToString(foto, 0));

        dataBase.persist(curriculo);

        mFragmentListener.onClickCadastrarCurriculoTabFotoFragment();
    }

    // Usado para camera
    private void carregarFoto() {

        Bitmap bitmapReduzido = criarBitmapFoto();
        ivFoto.setImageBitmap(bitmapReduzido);
        ivFoto.setScaleType(ImageView.ScaleType.FIT_XY);

    }

    // Usado para Camera
    private Bitmap criarBitmapFoto() {
        Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
        Matrix matrix = new Matrix();
        matrix.setRotate(0);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private Bitmap convertByteArrayToBitmap(String imagem) throws UnsupportedEncodingException {

        byte[] bytarray = Base64.decode(imagem,0);
        return BitmapFactory.decodeByteArray(bytarray, 0,
                bytarray.length);
    }

    private byte[] convertBitmapToArrayByte(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);
        return baos.toByteArray();
    }

}
