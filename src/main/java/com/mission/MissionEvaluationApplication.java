package com.mission;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mission.dao.EmployeeDao;
import com.mission.dao.RoleDao;
import com.mission.domain.Employee;
import com.mission.domain.Role;
import com.mission.domain.Role.ERole;

@SpringBootApplication
public class MissionEvaluationApplication implements CommandLineRunner {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(MissionEvaluationApplication.class, args);
	
	}

	

	@Override
	public void run(String... args) throws Exception {
		List<Role>roles = roleDao.findAll();
		try {
			
		
		if(roles.isEmpty()) {
			List<Role> role = Arrays.asList(new Role(ERole.HR), new Role(ERole.EMPLOYEE), new Role(ERole.ADVISOR));
			roleDao.saveAll(role);
			Employee employee = new Employee("1199080073404141", "Rukundo", "Alfred", "alfred@gmail.com", 
					new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01"), "0788000000", true, null,"admin", encoder.encode("admin123"));
			Optional<Role>r = roleDao.findAll().stream().filter(i->i.getName().equals(ERole.HR)).findAny();
			employee.setRole(r.get());
			employeeDao.save(employee);
			System.out.println(encoder.encode("123456"));
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
