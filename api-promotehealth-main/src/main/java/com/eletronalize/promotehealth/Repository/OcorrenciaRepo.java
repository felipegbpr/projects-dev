package com.eletronalize.promotehealth.Repository;

import com.eletronalize.promotehealth.Entity.IncidenciaExame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcorrenciaRepo extends JpaRepository<IncidenciaExame, Long> {
}
