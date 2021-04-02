package com.volapp.charityuser;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharityRepository extends JpaRepository<Charity, Integer> {
    Charity findByUsername(String username);
	List<Charity> findAll();
}
