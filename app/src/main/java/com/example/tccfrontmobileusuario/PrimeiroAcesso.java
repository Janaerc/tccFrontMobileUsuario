package com.example.tccfrontmobileusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import backend.RetrofitConfig;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrimeiroAcesso extends AppCompatActivity {

    EditText nomeUsuario,telefoneUsuario, cpfUsuario, senhaUsuario, emailUsuario, senhaUsuario2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeiro_acesso);


        nomeUsuario = findViewById(R.id.editTextName);
        telefoneUsuario = findViewById(R.id.editTextTelefone);
        cpfUsuario = findViewById(R.id.editTextCpf);
        emailUsuario = findViewById(R.id.editTextTextEmailAddress);
        senhaUsuario = findViewById(R.id.editTextPassword);
        senhaUsuario2 = findViewById(R.id.editTextPassword2);
    }

    public void salvarCadastro(View view){

        UsuarioDTO usuario = new UsuarioDTO();




        String senha = senhaUsuario.getText().toString();
        String senha2 = senhaUsuario2.getText().toString();

        System.out.println("senha 1  " + senha);
        System.out.println("senha 2  " + senha2);

        /*
        if(senha != senha2){
            Toast.makeText(this, "As senhas não são iguais", Toast.LENGTH_SHORT).show();
            return;

        }*/

        if (nomeUsuario.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe um nome válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (telefoneUsuario.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe um telefone válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cpfUsuario.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe um CPF válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (emailUsuario.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe um Email válido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (senhaUsuario.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe uma Senha válida", Toast.LENGTH_SHORT).show();
            return;
        }



            usuario.setNomeUsuario(nomeUsuario.getText().toString());
            usuario.setTelefone(telefoneUsuario.getText().toString());
            usuario.setCpf(cpfUsuario.getText().toString());
            usuario.setEmail(emailUsuario.getText().toString());
            usuario.setSenha(senha);
            usuario.setBloqueio(false);
            usuario.setTipo_usuario_id(1);
            usuario.setEspecialidade_id(1);




           Call<UsuarioDTO> call1 = new RetrofitConfig().getUsuarioService().cadastrarUsuario(usuario);
            System.out.println(usuario.getCpf());

            call1.enqueue(new Callback<UsuarioDTO>() {

                              @Override
                              public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(PrimeiroAcesso.this, "Usuario Cadastrado com sucesso!!!", Toast.LENGTH_SHORT).show();
                                }else{

                                    if (response.code() == 409)
                                        Toast.makeText(PrimeiroAcesso.this, "Usuario já cadastrado", Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(PrimeiroAcesso.this, "Erro ao Criar Cadastro", Toast.LENGTH_SHORT).show();
                                }
                              }

                              @Override
                              public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                                  Toast.makeText(PrimeiroAcesso.this, "Falha ao Criar Cadastro", Toast.LENGTH_SHORT).show();
                              }
                          }
            );




            }




    }






