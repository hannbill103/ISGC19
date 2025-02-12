package com.example.bookapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
// metod för att hitta alla böcker som tillhör en viss kategori av böcker
// JPA kan nu genom @REPOSITORY kunna generera SQL förfrågningarna
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategory(Category category);
}
