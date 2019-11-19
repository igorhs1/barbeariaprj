package com.example.barbeariaprj2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.barbeariaprj2.DAO.UsuarioDAO;
import com.example.barbeariaprj2.R;
import com.example.barbeariaprj2.config.ConfiguracaoFirebase;
import com.example.barbeariaprj2.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelaCadastro extends AppCompatActivity {

    private UsuarioDAO usuarioDAO;

    private Button btnConfirmarCadastro;

    private EditText nome, email, senha, confirmarSenha, dataNascimento;
    private RadioGroup radioGroupSexo;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        usuarioDAO = new UsuarioDAO(this);

        //inicializando componentes
        nome = findViewById(R.id.etNomeCadastro);
        email = findViewById(R.id.etEmailCadastro);
        senha = findViewById(R.id.etSenhaCadastro);
        confirmarSenha = findViewById(R.id.etConfirmarSenhaCadastro);
        dataNascimento = findViewById(R.id.etDataNascimentoCadastro);
        radioGroupSexo = findViewById(R.id.radioGroupSexo);

        btnConfirmarCadastro = findViewById(R.id.btnEnviarCadastro);

        btnConfirmarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCadastroUsuario();
            }
        });


    }

    public void OnClickBtnCancelar(View view){
        onBackPressed();
    }

    public void validarCadastroUsuario(){

        //recuperando texto dos campos
        String textoNome = nome.getText().toString();
        String textoEmail = email.getText().toString();
        String textoSenha = senha.getText().toString();
        String textoConfirmarSenha = confirmarSenha.getText().toString();
        String textoDataNascimento = dataNascimento.getText().toString();


        if( !textoNome.isEmpty()){
            if( !textoEmail.isEmpty()){
                if(!textoDataNascimento.isEmpty() ) {
                    if (!textoSenha.isEmpty()) {
                        if (!textoConfirmarSenha.isEmpty()) {
                            if (textoSenha.equals(textoConfirmarSenha)) {

                                if(validarEmail(textoEmail)){

                                    Usuario usuario = new Usuario();
                                    usuario.setNome(textoNome);
                                    usuario.setEmail(textoEmail);
                                    usuario.setSenha(textoSenha);
                                    usuario.setDataNascimento(textoDataNascimento);

                                    long id = usuarioDAO.inserirUsuario(usuario);
                                    Toast.makeText(TelaCadastro.this, "Cadastro efetuado com sucesso! ID: "+ id, Toast.LENGTH_SHORT).show();
                                    onBackPressed();

                                    //cadastrarUsuario(usuario);

                                }else{
                                    Toast.makeText(TelaCadastro.this, "Email inválido", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(TelaCadastro.this, "Senhas distintas!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(TelaCadastro.this, "Preencha a senha de confirmação!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TelaCadastro.this, "Preencha a senha!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(TelaCadastro.this, "Preencha a data de nascimento!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(TelaCadastro.this, "Preencha o email!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(TelaCadastro.this, "Preencha o nome!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrarUsuario(Usuario usuario){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(TelaCadastro.this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public boolean validarEmail(String email) {

        boolean isEmailIdValid = false;

        if (email != null && email.length() > 0) {

            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);

            Matcher matcher = pattern.matcher(email);

            if (matcher.matches()) {

                isEmailIdValid = true;

            }

        }
        return isEmailIdValid;

    }

}
