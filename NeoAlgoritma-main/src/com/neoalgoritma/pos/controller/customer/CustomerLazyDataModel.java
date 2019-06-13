package com.neoalgoritma.pos.controller.customer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.neoalgoritma.pos.dao.CustomerDAO;
import com.neoalgoritma.pos.model.Customer;

public class CustomerLazyDataModel extends LazyDataModel<Customer> {

	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO;
	private List<Customer> datasource;
	private int userId; 
	private boolean admin;
	private int accountId;
	
	
	public CustomerLazyDataModel(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
		this.setRowCount((int) customerDAO.getCollectionCount());
		System.out.println("row count" + getRowCount());
	//			.getCount("customer",
		//			 " COUNT(*) AS rowcount ",-1, 1," userId = " + userId,"","","",null,false));
		//this.userId = userId;
	}
	
	

  


	@Override
	public Customer getRowData(String rowKey) {
		// TODO Auto-generated method stub
		System.out.println("getRowData String: " + rowKey);
		return customerDAO.findOneById(rowKey);
	}




	
	@Override
	public List<Customer> load(int first, int pageSize, String sortField,
	                            SortOrder sortOrder, Map<String, Object> filters) {
			Document query = new Document();
			Document sort = new Document();
			/*System.out.println("sortField:" + sortField);
			System.out.println("sortOrder:" + sortOrder);
			if(sortField != null && sortField.equals("id"))
				sortField = "_id";
			*/
			
			if (sortField != null && sortOrder != null) {
				if(sortOrder.toString().equals("ASCENDING"))
				sort.put(sortField, 1);
				if(sortOrder.toString().equals("DESCENDING"))
					sort.put(sortField, -1);
				System.out.println("sort query:" + sort);	
			}
			
			datasource = customerDAO.findDocument(query, sort, pageSize, first);
			//this.setRowCount(datasource.size());
		
	  	  	String dataFilterProperty = "";
  	  	  	Object dataFilterValue = null;
		//System.out.println("loading....");
		//System.out.println("first: " + first);
		//System.out.println("pageSize: " + pageSize);
		//System.out.println("sortField: " + sortField);
		//System.out.println("sortOrder: " + sortOrder);
		//System.out.println("filters: " + filters.toString());
         
        
        //filter
		  if (filters != null) {
			
			  for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                  try {
                	  dataFilterProperty = it.next();
                      dataFilterValue = filters.get(dataFilterProperty);
                      System.out.println("dataFilterProperty: " + dataFilterProperty);
                      System.out.println("dataFilterValue: " + dataFilterValue);
                      query.append("$or", Arrays.asList(
                              new Document()
                                      .append("company", Pattern.compile(".*" + dataFilterValue +".*", Pattern.CASE_INSENSITIVE)),
                              new Document()
                                      .append("firstname", Pattern.compile(".*" + dataFilterValue +".*", Pattern.CASE_INSENSITIVE))
                          )
                      );
                      	datasource = customerDAO.findDocument(query,sort, pageSize, first);
      					this.setRowCount(datasource.size());
            		}
            		
                  catch (Exception e) {
					System.out.println("Error with filter --  CustomerLazyDataModel.java");
					e.printStackTrace();
				}  
                    	
               
			  }
		  }

        //sort
        //if(sortField != null) {
          //  Collections.sort(datasource, new CustomerLazySorter(sortField, sortOrder));
            
        //}

       
       /* 
        //rowCount
        int dataSize = getRowCount();
        //this.setRowCount(dataSize);
        
      //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }//
        else {
            return data;
        }
      */
        return datasource;
	}
	
	@Override
	public Object getRowKey(Customer object) {
		return (object.getId());
	}
	

	
	
	
	
}
