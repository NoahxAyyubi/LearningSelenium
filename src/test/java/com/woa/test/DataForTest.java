package com.woa.test;

import org.testng.annotations.DataProvider;

public class DataForTest {

    // comment
    @DataProvider
    public Object[][] getSearchTestData() {
        return new Object[][]{{"java books"}, {"selenium books"}};
    }
    @DataProvider
    public Object[][] getCategories(){
        return new  Object[][]{{"Crafts"},{"Art"}};
    }
}
//DataProvider
//public Object [][] get data (){
//return new Object [][]{{""},{}};
//    @DataProvider
//    public Object [][] getSearchTestDatas(){
//        return new Object[][]{{"java books"},{"selenium books"}};
//    }
//    @DataProvider
//    public Object [][] getSearchTestDatas(){
//        return new Object[][]{{"java books"},{"selenium books"}};
//    }
//
//    @DataProvider
//    public Object [][] getSearchTestDatas(){
//        return new Object[][]{{"java books"},{"selenium books"}};
//    [][]
//
