package toby.springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보
// 스프링이 빈 팩토리를 위한 오브젝트 설정을 담당하는 클래스라고 인식할수 있도록 @Configuration을 추가한다.
public class DaoFactory {
    @Bean
    // 오브젝트 생성을 담당하는 IoC용 메소드
    public UserDao userDao(){
        //UserDao 타입 오브젝트를 생성하고 초기화해서 돌려주는 메소드
        return new UserDao(connectionMaker());
    }

    public AccountDao accountDao(){
        return new AccountDao(connectionMaker());
    }

    public MessageDao messageDao(){
        return new MessageDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker(){
        // ConnectionMaker 타입의 오브젝트를 생성
        return new DConnectionMaker();
    }
}
