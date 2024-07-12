/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

/**
 *
 * @author aluno
 */
public enum Papel {
    ADMINISTRADOR(0),
    VENDEDOR(1),
    COMPRADOR(2);

    private final int valor;

    Papel(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public String getNome() {
        return name(); 
    }
}
