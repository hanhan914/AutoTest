import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//Application一般为入口类
@SpringBootApplication   //托管
@ComponentScan("com.test")     //扫描哪个包下面的类进行托管操作
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);//固定写法
    }
}
