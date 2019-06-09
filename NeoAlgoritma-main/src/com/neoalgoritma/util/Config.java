package com.neoalgoritma.util;


import java.io.File;
import java.io.FileReader;
import java.util.Properties;
 
public class Config
{
   Properties configFile;
   public Config()
   {
	   try {
		   FileReader reader = new FileReader("config.cfg");
		   FileReader reader2 = new FileReader("")
		   configFile = new Properties(); 
		   configFile.load(reader); 
	   }catch(Exception e){
		   System.out.println("Error reading config file");
		   System.out.println(e.getMessage());
		   e.printStackTrace();
		   System.out.println("*************************");
		   
	   }
   }
 
   public String getProperty(String key)
   {
	   System.out.println("getting key from config file ---- key :" + key);
	   String value = this.configFile.getProperty(key);
	   return value.trim();
   }
   
}