package io.noizwaves.datademo.datademo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArticleRepositoryTest {
    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;
    private ArticleRepository repo;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update("DELETE FROM article");

        repo = new ArticleRepository(jdbcTemplate);
    }

    @Test
    public void testCount() throws Exception {
        jdbcTemplate.update("INSERT INTO article (body) VALUES (?)", "foo");
        jdbcTemplate.update("INSERT INTO article (body) VALUES (?)", "bar");

        assertThat(repo.count(), equalTo(2));
    }

    @Test
    public void testCount_emptyTable() throws Exception {
        assertThat(repo.count(), equalTo(0));
    }
}
