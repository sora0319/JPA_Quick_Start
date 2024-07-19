


### 데이터 소스 과정

```
s_emp-mapping.xml
<mapper namespace="EmployeeDAO">

<!-- 데이터 소스 설정 -->
<environments default="development">
	<environment id="development">
		<transactionManager type="JDBC"/>
		<!-- connection pool -->
		<dataSource type="POOLED">
			<property name="driver" value="org.h2.Driver"/>
			<property name="url" value="jdbc:h2:tcp://localhost/./test"/>
			<property name="username" value="sa"/>
			<property name="password" value="sa"/>
		</dataSource>
	</environment>
</environments>
```

1. connection pool (공부)
mysql client <-> DB server  
접속 정보를 이용해서 로그인 -> session 성립 : DB 사용을 위한 전처리  
미리 접속된 connection을 생성해서 보관한 후, 요청을 받으면, connection을 하나 반환함  
따라서, 바로 연결된 것으로 사용하면 접속을 위한 전처리가 필요가 없다  
default connection 개수 : 10  

2. 데이터 insert 순서
DAO : Data Access Object  
EmployeeDAO : 직원 정보 접근 오브젝트  

```
-> EmployeeDAO.java

public void insertEmployee(EmployeeVO vo) {
		System.out.println("===> MyBatis 기반으로 직원 등록 기능 처리");
		mybatis.insert("EmployeeDAO.insertEmployee", vo);
		mybatis.commit();
	}

-> s_emp-mapping.xml
<!-- 직원 등록 SQL -->
	<insert id="insertEmployee" parameterType="employee">
		insert into s_emp(id, name,start_date, title, dept_name, salary)
		values((select nvl(max(id),0) + 1 from s_emp),
		#{name},
		#{startDate},
		#{title},
		#{deptName},
		#{salary})
	</insert>
```
EmployeeDAO : 가 s_emp-mapping.xml의 namespace로 들어감
insertEmployee :   


3. 데이터 select 순서

```
-> EmployeeDAO.java

public List<EmployeeVO> getEmployeeList() {
		System.out.println("===> MyBatis 기반으로 직원 목록 조회 기능 처리");
		return mybatis.selectList("EmployeeDAO.getEmployeeList");
	}

-> s_emp-mapping.xml
<!-- 직원 목록 검색 SQL -->
	<select id="getEmployeeList" resultMap="employeeResult">
		select id, name, start_date, title, dept_name, salary
		from s_emp
		order by name
	</select>
	
<!-- ResultMap 선언  -->
<resultMap type="employee" id="employeeResult">
	<result property="id" column="ID"/>
	<result property="name" column="NAME"/>
	<result property="startDate" column="START_DATE"/>
	<result property="title" column="TITLE"/>
	<result property="deptName" column="DEPT_NAME"/>
	<result property="salary" column="SALARY"/>
</resultMap>

-> sql-map-config.xml
<!-- 매퍼 XML에서 사용할  Alias 선언 -->
<typeAliases>
	<typeAlias alias="employee" type="com.rubypaper.persistence.mybatis.EmployeeVO" />
</typeAliases>

```

EmployeeDAO에서 mybatis.selectList("EmployeeDAO.getEmployeeList")로 xml에 있는 코드를 실행을 요청한다  
xml에서 id로 요청한 코드를 찾고 sql문을 실행한 다음 employeeResult로 결과를 저장한다  
resultmap에 저장되어 있던 id로 employeeResult를 찾고, employee(EmployeeVO)에 저장을 한다  
** employee(EmployeeVO)는 sql-map-config.xml에 저장되어 있는 alias로 이름이 설정되어 있음  
	



