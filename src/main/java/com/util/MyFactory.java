package com.util;

import java.io.FileInputStream;
import java.util.Properties;

public class MyFactory 
{
	public static Object getObject(String name)
	{
		FileInputStream fis = null;
		try {
			//archivo de propiedades que define las implementaciones
			fis = new FileInputStream("src//main//java//MiFactory.properties");
			Properties props = new Properties();
			props.load(fis);
			
			String sclazz = props.getProperty(name);
			System.out.println(sclazz);
			return Class.forName(sclazz).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			try {
				if(fis!=null)fis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
	}
}
