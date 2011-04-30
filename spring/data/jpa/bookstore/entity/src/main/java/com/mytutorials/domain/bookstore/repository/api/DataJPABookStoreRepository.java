package com.mytutorials.domain.bookstore.repository.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mytutorials.domain.bookstore.entity.mapping.impl.DefaultBook;

public interface DataJPABookStoreRepository extends
		JpaRepository<DefaultBook, Long> {

	List<DefaultBook> findByTitle(String bookTitle);

	List<DefaultBook> findByAuthorsFirstName(String firstName);

	Page<DefaultBook> findByAuthorsFirstName(String firstName, Pageable pageable);

	List<DefaultBook> findByAuthorsFirstName(String firstName, Sort sort);

	List<DefaultBook> findByEdition(String edition);

	@Query(value = "select b from DefaultBook b where b.edition =:edition and b.title =:title and b.totalNumberOfPages=:totalNumPages")
	List<DefaultBook> findByEditionAndTitleAndTotalNumberOfPages(
			@Param("edition") String edition, @Param("title") String title,
			@Param("totalNumPages") Integer totalNumPages);

	@Modifying
	@Query(value = "update DefaultBook b set b.title =?1 where b.id =?2")
	void updateTitleById(String newTitle, Long bookId);
}