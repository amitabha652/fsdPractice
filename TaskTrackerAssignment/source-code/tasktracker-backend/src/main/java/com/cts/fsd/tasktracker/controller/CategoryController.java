package com.cts.fsd.tasktracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fsd.tasktracker.entity.Category;
import com.cts.fsd.tasktracker.mapper.ObjectMapper;
import com.cts.fsd.tasktracker.pojo.CategoryPOJO;
import com.cts.fsd.tasktracker.repo.CategoryRepository;
import com.cts.fsd.tasktracker.service.CategoryService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CategoryService categoryService;
	
	
	@RequestMapping(value = "/create/dump", method = RequestMethod.GET)
	public ResponseEntity<String> createCategoryDump() {
		
		String categoriesJsonString = "[{\"categoryId\":1,\"categoryName\":\"Sprint\"},{\"categoryId\":2,\"categoryName\":\"Boxing\"},{\"categoryId\":3,\"categoryName\":\"Cardio\"},{\"categoryId\":4,\"categoryName\":\"SlowWalk\"},{\"categoryId\":5,\"categoryName\":\"FastWalk\"},{\"categoryId\":6,\"categoryName\":\"Jogging\"}]";
		Gson gson = new Gson();
		List<CategoryPOJO> categoriesPOJO = gson.fromJson(categoriesJsonString, new TypeToken<List<CategoryPOJO>>(){}.getType());
		
		System.out.println("categories coming in request = " + categoriesPOJO.toString());
		
		List<Category> categoriesEntityList = new ArrayList<Category>();
		
		if( categoriesPOJO != null && !categoriesPOJO.isEmpty() ) {
			for( CategoryPOJO categoryPOJO : categoriesPOJO ) {
				Category category = objectMapper.mapCategoryPojoToEntity(categoryPOJO);
				categoriesEntityList.add(category);
			}
		}
		
		List<Category> dbResponse = categoryService.createCategory(categoriesEntityList);
		
		return new ResponseEntity<String>("Categories Saved to Database..." + dbResponse , HttpStatus.OK);
	}

	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<CategoryPOJO>> listWorkouts() {
		System.out.println("getting all the categories from database...");
		List<Category> categoriesFromDB = categoryService.getAllCategories();
		List<CategoryPOJO> categoriesList = new ArrayList<CategoryPOJO>();
		
		if(categoriesFromDB != null) {
			for(Category category : categoriesFromDB) {
				CategoryPOJO categoryPOJO = objectMapper.mapCategoryEntityToPojo(category);
				categoriesList.add(categoryPOJO);
			}
		}
		
		return new ResponseEntity<List<CategoryPOJO>>(categoriesList , HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> createCategory(@RequestBody List<CategoryPOJO> categoriesPOJO) {
		System.out.println("categories coming in request = " + categoriesPOJO.toString());
		
		List<Category> categoriesEntityList = new ArrayList<Category>();
		
		if( categoriesPOJO != null && !categoriesPOJO.isEmpty() ) {
			for( CategoryPOJO categoryPOJO : categoriesPOJO ) {
				Category category = objectMapper.mapCategoryPojoToEntity(categoryPOJO);
				categoriesEntityList.add(category);
			}
		}
		
		List<Category> dbResponse = categoryService.createCategory(categoriesEntityList);
		
		return new ResponseEntity<String>("Categories Saved to Database..." + dbResponse , HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> updateCategory( @PathVariable(value = "id") int categoryId ,
			@RequestBody CategoryPOJO newCategoryPOJO) {
		System.out.println("Category to be updated which is coming in request = " + newCategoryPOJO.toString());
		
		Category dbResponse = null;
		
		if(categoryId == newCategoryPOJO.getCategoryId()) {
			dbResponse = categoryService.editCategoryById(categoryId , newCategoryPOJO);
		}
		
		if(dbResponse != null) {
			return new ResponseEntity<String>("Category[category id = "+categoryId+"] Updated in Database..." + dbResponse , HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Category[category id = "+categoryId+"] NOT Updated in Database as it does not exist..."  + dbResponse , HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> updateCategory( @PathVariable(value = "id") int categoryId ) {

		boolean dbResponse = categoryService.removeCategoryById(categoryId);
		
		if(dbResponse) {
			return new ResponseEntity<String>("Category[category id = "+categoryId+"] Deleted from database..." , HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Category[category id = "+categoryId+"] Not Deleted from database as it does not exist..." , HttpStatus.OK);
		}
	}
}
