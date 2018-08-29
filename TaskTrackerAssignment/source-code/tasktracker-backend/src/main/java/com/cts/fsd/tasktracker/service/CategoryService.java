package com.cts.fsd.tasktracker.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fsd.tasktracker.entity.Category;
import com.cts.fsd.tasktracker.exception.ResourceNotFoundException;
import com.cts.fsd.tasktracker.pojo.CategoryPOJO;
import com.cts.fsd.tasktracker.repo.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	public List<Category> createCategory(List<Category> categoriesEntityList){
		return categoryRepository.saveAll(categoriesEntityList);
	}
	
	public List<Category> getAllCategories(){
		
		return categoryRepository.findAll();
		
	}
	
	public Category getCategoryById(int categoryId){
		
		Category categoryFromDB = null;
		
		try {
			categoryFromDB = categoryRepository.findById(Long.valueOf(categoryId)).get();
			System.out.println("getCategoryById successfully returned Categpry from DB :: " + categoryFromDB.toString());
		} catch (NoSuchElementException e) {
			categoryFromDB = null;
			System.out.println("getCategoryById NOT successfull...\nNoSuchElementException encountered... ResourceNotFoundException thrown" + e);
			throw new ResourceNotFoundException("Category" , "categoryId" , categoryId);
		} catch (Exception e ) {
			categoryFromDB = null;
			System.out.println("Exception encountered..." + e);
//			throw new ResourceNotFoundException("Category" , "categoryId" , categoryId);
		}
		return categoryFromDB;
//		return categoryRepository.findById(Long.valueOf(categoryId)).orElseThrow(() -> new ResourceNotFoundException("Category" , "categoryId" , categoryId));
	}

	public Category editCategoryById(int categoryId , CategoryPOJO newCategoryPOJO){
		String editResponse = "";
		Category categoryFromDB = null;
		try {
			categoryFromDB =  getCategoryById(categoryId);
			System.out.println("Updating categoryFromDB = " + categoryFromDB.toString());
			categoryFromDB.setCategoryName(newCategoryPOJO.getCategoryName());
			
			categoryFromDB =  categoryRepository.save(categoryFromDB);
			editResponse = "Category ID("+categoryId+") updated, " + categoryFromDB.toString();
		} catch(ResourceNotFoundException e ) {
			System.out.println("ResourceNotFoundException encountered..." + e);
			editResponse = "Things are not updated as record does not exist... ";
			categoryFromDB = null;
		} catch(Exception e ) {
			System.out.println("Exception encountered..." + e);
			editResponse = "Things are not updated due to Exception... ";
			categoryFromDB = null;
		}
		System.out.println("After Update :: " + editResponse);
		return categoryFromDB;
	}
	
	
	public boolean removeCategoryById(int categoryId){
		String deleteResponse = "";
		Category categoryFromDB = null;
		boolean returnResponse = false;
		System.out.println("Before Delete Category By Id("+categoryId+")");
		try {
			categoryFromDB =  getCategoryById(categoryId);
			System.out.println("Deleting categoryFromDB = " + categoryFromDB.toString());
			categoryRepository.deleteCategoryById(Long.valueOf(categoryFromDB.getCategoryId()));
			deleteResponse = "Category ID("+categoryId+") Deleted, Record No More exists,";
			returnResponse = true;
		} catch (ResourceNotFoundException e ) {
			System.out.println("ResourceNotFoundException encountered..." + e);
			deleteResponse = "Things are not deleted as record does not exist... ";
			categoryFromDB = null;
			returnResponse = false;
		} catch (Exception e ) {
			System.out.println("Exception encountered..." + e);
			deleteResponse = "Things are not deleted due to Exception... ";
			categoryFromDB = null;
			returnResponse = false;
		}
		
		System.out.println("After Delete :: " + deleteResponse);
		return returnResponse;
	}
}
