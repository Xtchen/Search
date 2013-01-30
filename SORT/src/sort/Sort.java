/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author xiaotongchen
 */
class Number{
    private int num, count;
    
    Number(int num, int count){
        this.num=num;
        this.count=count;
    }
    
    public int getNum() {
        return num;
    }

    public int getCount() {
        return count;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
}
public class Sort {
    public static void main(String[] args){
    	int input[]={1,2,7,3,8,2,3,2,6,-1,6,9,1};//the sample of input
    	System.out.println("The input array is:");
    	for(int num:input){
    		System.out.print(num+" ");
    	}
    	System.out.println();
    	System.out.println("After sorted:");
    	int output[]=sort(input);//the sort method contains the display part
    }
    //use hashtable twice to sort the array in time: O(n), where n is the length of the input array 
    public static int[] sort(int[] input){
        int len=input.length;
        int new_len=len;// the length of new array
        
        //first time to use hashtable
        //scan the input array put every element into the hashtable
        //Number.num is the key Number.count is the value
        Hashtable tb=new Hashtable();
        try{
        	for(int num:input){
                if(tb.containsKey(num)){//it appeared, count++
                    int temp=(Integer)tb.get(num);
                    tb.remove(num);
                    tb.put(num, temp+1);
                    new_len--;//find one duplicate length of new array need to -1
                }
                else{//first time to appear, count=1
                    tb.put(num, 1);
                }
            }
        }catch(Exception e){
        	System.out.println("Error in first time hashtable");
        }
        //hashtable tb is finished, need to take the data out 
        Set set=tb.entrySet();
        Iterator it=set.iterator();
        int num_temp,count_temp;
        
        //second time to use hashtable
        //move the data from hashtable tb to hashtable htb
        //the appear time of this number is the key
        //the arraylist that contains the numbers with the same appear time is the value
        Hashtable htb=new Hashtable();
        try{
        	while(it.hasNext()){
                Map.Entry entry=(Map.Entry) it.next();
                num_temp=Integer.parseInt(entry.getKey().toString());
                count_temp=Integer.parseInt(entry.getValue().toString());
                Number num=new Number(num_temp,count_temp);
                
                if(htb.containsKey(num.getCount())){
                	//exits other number that appears the same times
                    ArrayList<Integer> temp=(ArrayList<Integer>)htb.get(num.getCount());
                    htb.remove(num.getCount());
                    temp.add(num.getNum());//add this number into the arraylist
                    htb.put(num.getCount(), temp);
                }
                else{
                	//currently, it's the only number that appears these times
                	ArrayList<Integer> temp=new ArrayList<Integer>();
                	temp.add(num.getNum());
                	htb.put(num.getCount(), temp);
                }
            }
        }catch(Exception e){
        	System.out.println("Error in second time hashtable");
        }
        
        
        int output[]=new int[new_len];//new output
        int j=0;//the counter of the output
        try{
	        for(int i=len;i>0;i--){
	        	if(htb.containsKey(i)){
	        		ArrayList<Integer> temp=(ArrayList<Integer>) htb.get(i);
	        		for(Integer n:temp){
	        			output[j]=n.intValue();//assign the output
	        			j++;
	        		}
	        	}
	        }
        }catch(Exception e){
        	System.out.println("Error assignment");
        }
        //display output
        for(int num:output){
        	System.out.print(num+" ");
        }
		return output;
    }
}
