# WebSourceCode
웹개발 관련 소스


# 스프링 pom.xml 셋팅

		<!-- MySQL -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.36</version>
		</dependency>
		
		<!-- MyBatis -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.2.8</version>
		</dependency>
		
		<!-- MyBatis-Spring -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.2.2</version>
		</dependency>
		
		<!-- spring-jdbc -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		
		<!-- spring-test -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		
		
		
		
		
--------------------------------------------------------------------------------------------------------------------------------------


# root-context.xml (2nd)
<bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" 
	      value="net.sf.log4jdbc.sql.jdbcapi.DriverSpy">
    </property>
    <property name="url" 
              value="jdbc:log4jdbc:mysql://127.0.0.1:3306/mysql?         zeroDateTimeBehavior=convertToNull&amp;useUnicode=true&amp;characterEncoding=UTF-8">           
    </property>
    <property name="username"   value="root"></property>
    <property name="password"   value="12345"></property>
</bean>

<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	<property name="dataSource"      ref  ="datasource"></property>
	<property name="configLocation"  value="classpath:/mybatis-config.xml"></property>
	<property name="mapperLocations" value="classpath:/mappers/**/*Mapper.xml"></property>
</bean>





# root-context.xml (1st)

<bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
	<property name="driverClassName" value="com.mysql.jdbc.Driver"></property> 
	<property name="url"   value="jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&amp;characterEncoding=UTF-8"></property>
	<property name="username"        value="root"></property>
	<property name="password"        value="12345"></property>
</bean>

--------------------------------------------------------------------------------------------------------------------------------------


