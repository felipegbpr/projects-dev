package br.com.spb.devolution.repositories;

import br.com.spb.devolution.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

        @Query("SELECT u from User u where u.id > :id")
        public List<User> findAllMoreThan(@Param("id") Long id);

        public List<User> findByIdGreaterThan(Long id);

        public List<User> findByNameIgnoreCase(String name);

}
