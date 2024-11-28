package org.example.springjpa.repository;

import org.example.springjpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Search by first name, limited to two characters (starts with provided value)
    List<User> findByFirstNameStartingWith(String firstName);

    // Search by mobile, limited to two characters (starts with provided value)
    List<User> findByMobileStartingWith(String mobile);

    // Search by first name and mobile, both starting with the provided values
    List<User> findByFirstNameStartingWithAndMobileStartingWith(String firstName, String mobile);

    User findByEmail(String email);

}
