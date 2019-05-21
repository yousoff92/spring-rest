package com.yousoff.rest.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yousoff.rest.service.SpringRestService;
import com.yousoff.rest.vm.CreateVM;
import com.yousoff.rest.vm.ListUserVM;
import com.yousoff.rest.vm.SingleUserVM;
import com.yousoff.rest.vm.UserVM;

/**
 * 
 * For testing REST with Spring.
 * 
 * Ref : 
 * 
 * <ol>
 * <li><b>Baeldung</b> https://www.baeldung.com/rest-template</li>
 * <li><b>Spring Doc</b> https://spring.io/guides/gs/consuming-rest/</li>
 * <li><b>Test REST website </b> https://reqres.in/</li>
 * <li><b>Convert JSON to POJO </b> http://www.jsonschema2pojo.org/</li>
 * </ol>
 * 
 * @author yousoff
 *
 */
public class MainApplication {

	public static String REQRES_URL = "https://reqres.in";

	public static void main(String[] args) {
		
		// Test GET with hardcoded QueryParam
		testListUsers("/api/users?page=2");
		
		// Test GET with QueryParam
		Map<String, String> q1 = new HashMap<String, String>();
		q1.put("page", "2");
		testListUsersWithQuery("/api/users", q1);
		
		// Test GET 404 error
		testSingleUser("/api/users/23");
		
		// Test POST
		CreateVM createVM = new CreateVM(null, "Name1", "Job1", null);
		testCreateUser("/api/users", createVM);
		
	}

	private static void testCreateUser(String url, CreateVM createVM) {
		System.out.println("MainApplication.testCreateUser() -- Start");
		SpringRestService service = new SpringRestService();
		CreateVM response = service.sendPostRequest(REQRES_URL + url, null, createVM, CreateVM.class);
		System.out.println(response);
		
		System.out.println("MainApplication.testCreateUser() -- End");
		
	}

	private static void testSingleUser(String url) {
		System.out.println("MainApplication.testSingleUser() -- Start");
		SpringRestService service = new SpringRestService();
		SingleUserVM s = service.sendGetRequest(REQRES_URL + url, null, null, null, SingleUserVM.class);
		System.out.println(s);
		
		System.out.println("MainApplication.testSingleUser() -- End");
		
	}

	private static void testListUsersWithQuery(String url, Map<String, String> q1) {
		System.out.println("MainApplication.testListUsersWithQuery() -- Start");
		
		SpringRestService service = new SpringRestService();

		ListUserVM vm = service.sendGetRequest(REQRES_URL + url, null, null, q1, ListUserVM.class);
		List<UserVM> users = vm.getUsers();

		for (UserVM userVM : users) {
			System.out.println(userVM);
		}
		System.out.println("MainApplication.testListUsersWithQuery() -- End");
	}

	private static void testListUsers(String url) {
		System.out.println("MainApplication.testListUsers() -- Start");
		
		SpringRestService service = new SpringRestService();

		ListUserVM vm = service.sendGetRequest(REQRES_URL + url, null, null, null, ListUserVM.class);
		List<UserVM> users = vm.getUsers();

		for (UserVM userVM : users) {
			System.out.println(userVM);
		}
		
		System.out.println("MainApplication.testListUsers() -- End");
	}

}
