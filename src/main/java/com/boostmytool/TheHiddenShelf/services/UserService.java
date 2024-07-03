package com.boostmytool.TheHiddenShelf.services;

import com.boostmytool.TheHiddenShelf.models.User;
import com.boostmytool.TheHiddenShelf.models.UserDto;

public interface UserService {
	
	User save(UserDto userDto);

}
