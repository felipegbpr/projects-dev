package com.eletronalize.promotehealth.Repository;

import com.eletronalize.promotehealth.Entity.Regiao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegiaoRepo extends JpaRepository<Regiao, Long> {
}
