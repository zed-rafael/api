package net.uni5.ztec.api.enums;

import java.util.stream.Stream;

public enum PerfilUsuario {
    ADMIN(0,"Pendente"),
    USUARIO(1,"Conciliado");

    private final int perfil;
    private String  descricao;

    PerfilUsuario(Integer perfil, String descricao){
        this.perfil    = perfil;
        this.descricao = descricao;
    }

    public Integer getPerfil() {
        return this.perfil;
    }

    public static PerfilUsuario toEnum(int perfil) {
        return Stream.of(PerfilUsuario.values())
                .filter(f -> f.perfil == perfil)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Perfil: " + perfil + " inválido."));
    }
}