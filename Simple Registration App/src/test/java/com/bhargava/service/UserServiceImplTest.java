package com.bhargava.service;

import com.bhargava.dao.UserDAO;
import com.bhargava.model.UserDetails;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Before;
import static org.junit.Assert.*;

public class UserServiceImplTest {
	
	@InjectMocks
	private static UserServiceImpl mockedUserServiceImpl;
	
	@Mock
    private UserDAO userDAO;
	
	private static UserDetails userDetails1;
	private static UserDetails userDetails2;

	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		mockedUserServiceImpl = mock(UserServiceImpl.class);
		
		userDetails1 = new UserDetails();
		userDetails1.setId(0);
		userDetails1.setUserName("zzz");
		userDetails1.setPassword("zzz");
		
		userDetails2 = new UserDetails();
		userDetails2.setId(1);
		userDetails2.setUserName("YYyyy");
		userDetails2.setPassword("YYyyy123");
		
		when(mockedUserServiceImpl.saveUserDetails(userDetails1)).thenReturn(false);
		when(mockedUserServiceImpl.saveUserDetails(userDetails2)).thenReturn(true);
	}

	@Test
	public void testSaveUserDetails() throws Exception {
		UserDetails unsavable = new UserDetails();
		unsavable.setId(0);
		unsavable.setUserName("zzz");
		unsavable.setPassword("zzz");
		
		UserDetails savable = new UserDetails();
		savable.setId(1);
		savable.setUserName("YYyyy");
		savable.setPassword("YYyyy123");
		
		assertEquals(false, mockedUserServiceImpl.saveUserDetails(unsavable));
		assertEquals(true, mockedUserServiceImpl.saveUserDetails(savable));

	}

}
