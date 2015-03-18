package com.jinxin.common.test;

import javax.sql.DataSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring 单元测试基类
 * @author ThinkGem
 * @version 2013-05-15
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)  
@Transactional  
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:spring-hibernate.xml"})
public class SpringTransactionalContextTests extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	protected DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		this.dataSource = dataSource;
	}

}
