package com.fb.app.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fb.app.model.Share;
import com.fb.app.model.User;

public interface FacebookShareRepository extends CrudRepository<Share, Integer>{

	@Query("FROM Share s WHERE s.shareto = :userid")
	Iterable<Share> findByshareto(@Param("userid")String userid);
}
