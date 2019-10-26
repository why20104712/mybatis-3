package why;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class MybatisTest {

  @Test
  public void test() throws IOException {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);

    /*
     * 构建sqlSessionFactory
     * 初始化数据源
     */
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    try (SqlSession session = sqlSessionFactory.openSession()) {
      User user1 = (User) session.selectOne("why.selectUser", 1);
      log.error("user1:{}", user1);
      User user2 = (User) session.selectOne("why.selectUser", 1);
      log.error("user2:{}", user2);
    }
  }

  @Test
  public void cacheTwo() throws IOException {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);

    /*
     * 构建sqlSessionFactory
     * 初始化数据源
     */
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    try (SqlSession session = sqlSessionFactory.openSession()) {
      User user1 = (User) session.selectOne("why.selectUser", 1);
      log.error("user1:{}", user1);
    }

    try (SqlSession session = sqlSessionFactory.openSession()) {
      User user2 = (User) session.selectOne("why.selectUser", 1);
      log.error("user2:{}", user2);
    }
  }


  @Test
  public void testManyMappedStatement() throws IOException {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);

    /*
     * 构建sqlSessionFactory
     * 初始化数据源
     */
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    try (SqlSession session = sqlSessionFactory.openSession()) {
      User user1 = (User) session.selectOne("why.selectUser", 1);
      log.error("user1:{}", user1);
    }

    try (SqlSession session = sqlSessionFactory.openSession()) {
      List<User> list = session.selectList("why.selectUserList");
      log.error("list:{}", list);
    }
  }
}
