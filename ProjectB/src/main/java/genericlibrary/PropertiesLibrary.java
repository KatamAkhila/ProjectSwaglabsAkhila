package genericlibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLibrary implements FrameWorkConstant {

	static FileInputStream fis;
	
	static FileOutputStream fos;
		
	static Properties  property;
	

public static String readData(String Key) 
{
	//convert The external File to java readable file
	try {
		fis = new FileInputStream(propertypath);
	}
	catch (FileNotFoundException e) {
	e.printStackTrace();
	}
	//create An Object For Properties class
	Properties property = new Properties ();
	
	//Load the Properties
	try {
		property.load(fis);
	}catch(IOException e) {
	e.printStackTrace();	
	}
	//Read and Fetch The Data
	return property.getProperty(Key);
}
public static void writeData(String newkey,String newvalue) {
	//convert external file into java readable
	try {
		fis = new FileInputStream(propertypath);
	}catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	//Create An Object For Properties Class
	Properties property=new Properties();
	//Load The Properties
	try {
		property.load(fis);
	}catch (IOException e) {
		e.printStackTrace();
	}
	//put The New Data Inside properties file
	property.put(newkey,newvalue);
	//convert java readable file to external file
	try {
		fos = new FileOutputStream(propertypath);
	}catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	//store The Data
	try {
	property.store(fos,"Updated New Key And Value...");
	}catch(IOException e) {
	e.printStackTrace();	
	}	
	System.out.println("writeDataSuccessful");	
}	
}
