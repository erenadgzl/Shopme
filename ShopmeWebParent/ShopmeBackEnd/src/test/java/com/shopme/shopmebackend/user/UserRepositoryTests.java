package com.shopme.shopmebackend.user;


import com.shopme.shopme.common.entity.Role;
import com.shopme.shopme.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;


import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        Role role = entityManager.find(Role.class, 1);
        User user = new User("erenadiguzel966@gmail.com", "123456", "Eren", "Adıguzel");
        user.addRole(role);

        User savedUser = repo.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers(){
        Iterable<User> listUsers = repo.findAll();
        listUsers.forEach(user -> System.out.println(user));
    }

    @Test
    public void testGetUserById(){
        User user = repo.findById(1).get();
        System.out.println(user);
        assertThat(user).isNotNull();
    }

    @Test
    public void testUpdateUserDetails(){
        User user = repo.findById(1).get();
        user.setEmail("test@test.com");
        repo.save(user);
    }

    @Test
    public void testDeleteUserById(){
        User user = repo.findById(1).get();
        repo.delete(user);
    }

    @Test
    public void testCountById(){
        Integer id = 1;
        Long count = repo.countById(id);
        assertThat(count).isNotNull().isGreaterThan(0);
    }

    @Test
    public void testDisabledUser(){
        Integer id = 1;
        repo.updateEnabledStatus(id, false);
    }

    @Test
    public void testEnabledUser(){
        Integer id = 1;
        repo.updateEnabledStatus(id, true);
    }

    @Test
    public void testListFirstPage(){
        int pageNumber = 0;
        int pageSize = 1;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> page = repo.findAll(pageable);
        List<User> listUsers = page.getContent();

        assertThat(listUsers.size()).isEqualTo(pageSize);
    }

    @Test
    public void testSearchUsers(){
        String keyword = "Eren";
        int pageNumber = 0;
        int pageSize = 1;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> page = repo.findAll(keyword,pageable);
        List<User> listUsers = page.getContent();
        assertThat(listUsers.size()).isGreaterThan(0);
    }
}
