package io.noizwaves.datademo.datademo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.noizwaves.datademo.datademo.helpers.TestDataSource.testDataSource;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleRepositoryTest {
    private JdbcTemplate jdbcTemplate;
    private ArticleRepository repo;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = new JdbcTemplate(testDataSource());

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
