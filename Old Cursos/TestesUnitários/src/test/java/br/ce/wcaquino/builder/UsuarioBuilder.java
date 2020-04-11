package br.ce.wcaquino.builder;

import br.ce.wcaquino.entidades.Usuario;

public class UsuarioBuilder {

    // constutor privado pra ninguem criar possa criar instancia do builder estexnamente
    private Usuario usuario;

    private UsuarioBuilder(){

    }

    // eh static para que possa ser acesasdo externmanete, e sem presisar criar instancia
    public static UsuarioBuilder umUsuario(){
        UsuarioBuilder builder = new UsuarioBuilder();
        builder.usuario = new Usuario();
        builder.usuario.setNome("Usuario 1");
        return builder;
    }

    public UsuarioBuilder comNome(String nome) {
        usuario.setNome(nome);
        return this;
    }


    public Usuario agora(){
        return usuario;
    }

}
