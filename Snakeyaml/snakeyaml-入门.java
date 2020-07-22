--------------------------------
Snakeyaml						|
--------------------------------
	# Maven
		<dependency>
			<groupId>org.yaml</groupId>
			<artifactId>snakeyaml</artifactId>
			<version>1.23</version>
		</dependency>
	
	# ��վ
		https://bitbucket.org/asomov/snakeyaml/wiki/Home
	
	# ����
		Yaml yaml = new Yaml();
		InputStream inputStream = Main.class.getResourceAsStream("/application.yml");
		Object load = yaml.load(inputStream);
		System.out.println(load);							//{name=KevinBlandy}
		System.out.println(load.getClass().getName());		//java.util.LinkedHashMap
	
	# Map �ṹ����
		- ����:java.util.LinkedHashMap
		- ����:java.util.ArrayList
	
--------------------------------
List							|
--------------------------------
	- Kevin
	- Litch
	- Rocco


	Yaml yaml = new Yaml();
	List<String> list = yaml.load(Main.class.getResourceAsStream("/application.yml"));
	System.out.println(list);						//[Kevin, Litch, Rocco]
	System.out.println(list.getClass().getName());	//java.util.ArrayList
		
	
--------------------------------
Map								|
--------------------------------
	name: KevinBlandy
	age: 23
	gender: boy
		
	Yaml yaml = new Yaml();
	Map<String,String> map = yaml.load(Main.class.getResourceAsStream("/application.yml"));
	System.out.println(map);						//{name=KevinBlandy, age=23, gender=boy}
	System.out.println(map.getClass().getName());	//java.util.LinkedHashMap

--------------------------------
���ӵĽ���						|
--------------------------------
user:
  name: KevinBlandy
  age: 23
  skills:
    - Java
    - Python
    - C
    - Javascript


	Yaml yaml = new Yaml();
	Map<String,Object> map = yaml.load(Main.class.getResourceAsStream("/application.yml"));
	System.out.println(map);						//{user={name=KevinBlandy, age=23, skills=[Java, Python, C, Javascript]}}
	System.out.println(map.getClass().getName());	//java.util.LinkedHashMap
	
	Map<String,Object> user = (Map<String, Object>) map.get("user");
	String name = (String) user.get("name");
	System.out.println(name);									//KevinBlandy

	List<String> skills = (List<String>) user.get("skills");
	System.out.println(skills);									//[Java, Python, C, Javascript]

--------------------------------
�� yml ����������Ⱦ				|
--------------------------------
	Yaml yaml = new Yaml();
	String user = yaml.loadAs(Main.class.getResourceAsStream("/application.yml"),User.class);


--------------------------------
�Ѷ�����ȾΪ yml				|
--------------------------------
	Yaml yaml = new Yaml();
	Map<String,Object> map = new HashMap<>();
	map.put("name", "KevinBlandy");
	map.put("age", "23");
	map.put("gender", "��");
	yaml.dump(map, new PrintWriter(System.out));	//{gender: ��, name: KevinBlandy, age: '23'}
	
























