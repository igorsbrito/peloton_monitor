package br.ufc.pelotonmonitor.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.ufc.pelotonmonitor.dao.DatabaseDao;
import br.ufc.pelotonmonitor.model.TableDefault;

@Controller
public class DataBaseDefaultController {
	
	@RequestMapping(value="/query/{ip}/{port}/{query}", method=RequestMethod.GET, produces="application/json") 
	public @ResponseBody String  query(@PathVariable String ip, @PathVariable String port, @PathVariable String query){
		
		try {
			Connection connection = DatabaseDao.connectionDatabaseDefault(ip,port);
			//Statement statement = connection.createStatement();
			
			PreparedStatement ps= connection.prepareStatement(query);  
			
			ResultSet rs=ps.executeQuery();  
			ResultSetMetaData rsmd=rs.getMetaData();  
			
			int numeroAttr = rsmd.getColumnCount();
			
			List<String> listAttrs = new ArrayList<String>();
			
			List<List<String>> listAttrVals = new ArrayList<List<String>>();
			
			for(int i  = 1; i <= numeroAttr; i ++){
				listAttrs.add(rsmd.getColumnName(i));
				System.out.println(rsmd.getColumnTypeName(i));
			}
			
			while(rs.next()){
				List<String> aux = new ArrayList<String>();
				
				for(int i  = 1; i <= numeroAttr; i ++){
					
					if("int4" == rsmd.getColumnTypeName(i)){
						String val = String.valueOf(rs.getInt(rsmd.getColumnName(i)));
						
						aux.add(val);
						
					}else if("text" == rsmd.getColumnTypeName(i)){
						String val = rs.getString(rsmd.getCatalogName(i));
						
						aux.add(val);
					}
				}
				
				listAttrVals.add(aux);
				
			}
			
			TableDefault tableDefault = new TableDefault();
			tableDefault.setAttrs(listAttrs);
			tableDefault.setAttrsVal(listAttrVals);
			
			
			return new Gson().toJson(tableDefault);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}
	

}