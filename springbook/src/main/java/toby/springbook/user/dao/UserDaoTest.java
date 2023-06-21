package toby.springbook.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import toby.springbook.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /**
         * ApplicationContext를 구현한 클래스는 여러가지가 있는데
         * DaoFactory처럼 @Configuration이 붙은 자바코드를 설정정보로 사용하려면
         * AnnotationConfigApplicationContext를 이용하면 된다.
         * 애플리케이션 컨텍스트를 만들 때 생성자 파라미터로
         * DaoFactory 클래스를 넣어주면 ApplicationContext의
         * getBean()이라는 메소드를 이용해 UserDao의 오브젝트를 가져올 수 있다.
         */
        ApplicationContext context
                = new AnnotationConfigApplicationContext(DaoFactory.class);

        /**
         * getBean()의 파라미터인 "userDao"는 ApplicationContext에 등록된 빈의 이름이다.
         * DaoFactory에서 @Bean이라는 애노테이션을 userDao라는 이름의 메소드에 붙였는데
         * 이 메소드의 이림이 바로 빈의 이름이 된다.
         * userDao라는 이름의 빈을 가져온다는 것은 DaoFactory의
         * userDao()메소드를 호출해서 그 결과를 가져온다고 생각하면 된다.
         */
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();

        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        System.out.println(user.getId() + " 조회 성공");
    }
}
