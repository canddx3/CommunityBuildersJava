package com.volapp.charityuser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CharityIdRepository extends JpaRepository<Charity, String> {
	Charity findById(Long id);
}
