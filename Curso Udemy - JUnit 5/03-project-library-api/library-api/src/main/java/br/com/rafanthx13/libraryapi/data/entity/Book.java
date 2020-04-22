package br.com.rafanthx13.libraryapi.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity // Essa classe é Entidade para JPA
@Table // Uma tabela
public class Book {

    @Id // Identifica que é ChavePrimária
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // diz que esse campo é auto-increment. IDENTY = É o banco que vai se virar
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String isbn;

    // Vai server para pegarmos todos os emprestimos de um livro
    // Esse relacionamento entre Book <-> Loan é araves de Loan.book
    // O defalut de @OneTOMany: fetch = FetchType.LAZY
    @OneToMany( mappedBy = "book" ) //mappedBt: book de Loan. Nâo é a cahve, é o nome da propriedaade
    private List<Loan> loans;


}