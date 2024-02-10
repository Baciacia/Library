package com.bacia.quickstart.Repository;

import com.bacia.quickstart.Domain.Entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {
    Page<BookEntity> findAllByGenre(String gen, Pageable pageable);
    Page<BookEntity> findAllByYearPublishGreaterThanEqual(int year, Pageable pageable);
}
