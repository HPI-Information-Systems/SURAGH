import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permutation_Class { 

	
	Main_Class main_class_object_for_permutation = new Main_Class();
	Abstraction_Primitives abstraction_primitives_object_for_permutation = new Abstraction_Primitives();
    static List<Integer> list_permutation_data = new ArrayList<>();
    boolean flag_halt_permuation = false;
    static List<Dependency_Class> dependencies;
    static {
    	dependencies  = new ArrayList<Dependency_Class>();
        
        dependencies.add(new Dependency_Class(1, Arrays.asList(2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)));
        dependencies.add(new Dependency_Class(2, Arrays.asList(3)));
        dependencies.add(new Dependency_Class(3, Arrays.asList(15)));
        dependencies.add(new Dependency_Class(4, Arrays.asList(5)));
        dependencies.add(new Dependency_Class(5, Arrays.asList(15)));
        dependencies.add(new Dependency_Class(6, Arrays.asList(7,8)));
        dependencies.add(new Dependency_Class(7, Arrays.asList(15)));
        dependencies.add(new Dependency_Class(8, Arrays.asList(7)));
        dependencies.add(new Dependency_Class(9, Arrays.asList(10)));
        dependencies.add(new Dependency_Class(10, Arrays.asList(15)));
        dependencies.add(new Dependency_Class(11, Arrays.asList(16)));
        dependencies.add(new Dependency_Class(12, Arrays.asList(16)));
        dependencies.add(new Dependency_Class(13, Arrays.asList(8,16)));
        dependencies.add(new Dependency_Class(14, Arrays.asList(16)));
        dependencies.add(new Dependency_Class(15, Arrays.asList(16)));   
    	
    	     
    }
    
    public Permutation_Class (int n)
    {
         for( int i = 1; i <= n; i++)
         {
         	  list_permutation_data.add(i);
         }
        	 
    }
    
    public static boolean dependent_permutation_check(List<Integer> permutation_combintaion)
    {
    	for(Dependency_Class d: dependencies)
    	{
    		d.reset();
    	}
         
        for(int i:permutation_combintaion)
        {
        	for(Dependency_Class d: dependencies)
        	{
        		if(!d.add(i))
        			return false;
        	}
        	
        }
        return true;    
    }
    
  
    public void permute_method_individual_rows(List<Integer> list_permutation_data, int left, int right) 
    { 
        if (left == right) 
        { 
        	
        	if(dependent_permutation_check(list_permutation_data))
    		{
        		
        		//main_class_object_for_permutation.abstraction_class_call_method_individual_rows(list_permutation_data);
        		abstraction_primitives_object_for_permutation.set_hashMap_abstraction_premitive();
        		
    		}
        	
        }      
        else { 
            for (int i = left; i <= right; i++) {
            	
            		   list_permutation_data = swap(list_permutation_data, left, i); 
                       permute_method_individual_rows(list_permutation_data, left + 1, right); 
                       list_permutation_data = swap(list_permutation_data, left, i);
           }   
        }
    } 
   
    public void permute_method_group_rows(List<Integer> list_permutation_data, int left, int right) 
    { 
    	if (left == right) 
        {
    		//main_class_object_for_permutation.abstraction_class_call_method_group_rows(list_permutation_data);
    		
        }  
        else { 
            for (int i = left; i <= right; i++) {
            	
                list_permutation_data = swap(list_permutation_data, left, i); 
                permute_method_group_rows(list_permutation_data, left + 1, right); 
                list_permutation_data = swap(list_permutation_data, left, i); 
            }        
        }
    } 
    
    
    public List<Integer> swap(List<Integer> list_permutation_data, int i, int j) 
    { 
    	Collections.swap(list_permutation_data, i, j);
		return list_permutation_data;
    } 
    

} 