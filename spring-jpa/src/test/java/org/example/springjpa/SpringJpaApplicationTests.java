package org.example.springjpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringJpaApplicationTests {

    @Test
    void contextLoads() {
        // This test will pass if the application context loads successfully
    }

    @Test
    void applicationStartsSuccessfully() {
        SpringJpaApplication.main(new String[]{});
        assertThat(true).isTrue(); // Check that the application starts without issues
    }
}
