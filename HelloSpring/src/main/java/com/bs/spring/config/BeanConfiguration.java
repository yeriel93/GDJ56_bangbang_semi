package com.bs.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bs.spring.model.vo.Animal;
import com.bs.spring.model.vo.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

//beanConfigurationŬ������ �����ϱ�
@Configuration
public class BeanConfiguration {
	
	//�����ϴ� Springbean�� �޼ҵ�� ����
	//��ü�� ��ȯ�ϴ� �޼ҵ带 ����
	@Bean
	public Person getDongmin() {
		Person p = new Person();
		p.setName("�̵���");
		p.setAge(28);
		return p;
	}
	
	@Bean
	@Qualifier("song")
	public Animal cow() {
		return new Animal("���۾���",5,"��",100.5);
	} 
	
	@Bean
	@Autowired
	public Person ujun(@Qualifier("song") Animal a) {
		Person p = new Person();
		p.setName("������");
		p.setAge(31);
		p.setMyAnimal(a);
		return p; 
	}
	
	//websocket ����
    @Bean
    public ObjectMapper mapper() {
       return new ObjectMapper();
    }
}
