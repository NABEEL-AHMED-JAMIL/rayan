package com.example.rayan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Lycus 01 on 7/5/2017.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByRole(String role);

}

