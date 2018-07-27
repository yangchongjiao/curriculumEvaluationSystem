package spring_mvc_03.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.curriculumEvaluationSystem.bean.Admin;
import com.curriculumEvaluationSystem.dao.AdminMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/com/curriculumEvaluationSystem/resources/spring-mybatis.xml") //加载配置
public class SMTest {

	@Autowired
	AdminMapper adminMapper;
	
	
	@Test
	public void testAdd() {
		/*
		User user = new User(-1, "tom", new Date(), 1234.01);
		mapper.save(user);
		*/
		Admin user = adminMapper.selectByPrimaryKey(1);
		int id = user.getId();
		System.out.println(user.getUsername());
	}
}
