package com.volapp.charity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharityRepository extends JpaRepository<Charity, Long> {
    Charity findByUsername(String username);
	List<Charity> findAll();
}
