//paste your code here 
package main;

import java.util.*;
class Master extends Thread{
    String Q[];
    Master()
    {
        Q=new String[]{"How many days are there in a week?\na)7\nb)8\nc)10","Which planet is known as red planet?\na)Mars\nb)Mercury\nc)Earth","Which is the largest ocean in the world?\na)Arctic ocean\nb)Pacific ocean\nc)Atlantic ocean",
            "Which is the largest country in the world?\na)India\nb)Phillipines\nc)Russia","Who designed Java?\na)James Gosling\nb)Dennis Ritchie\nc)Guido Van Rossum",
                "What's the extension for a compiled Java file?\na).class\nb).java\nc).xml\nd).html"};

    }
 public void run(){
       
      
        
        for(int i=0;i<Q.length;i++){
            
            
            System.out.println(i+1+")"+Q[i]);
            
            try{
                 sleep(6000);
                 
            }
            catch(InterruptedException e){
           
        }
            
    }
}
}

class Student extends Thread{
    int score=0;
    String A[];
    Student()
    {
       A=new String[]{"a","a","b","c","a","a"};
    }
   
   
   
   
   public void run(){
        
        
        String answer;
       for(int i=0;i<A.length;i++){
           Scanner sc = new Scanner(System.in);

           answer=sc.nextLine();
          
           if(A[i].equals(answer)){
               System.out.println("Correct answer");
               score++;
               
           }
           else 
           {
               System.out.println("Wrong answer");
           }

   }}
    public int result(){
            return score;
     
}
}


public class Main {

    public static void main(String[] args)  {
         Master m=new Master();
         m.start();
         Student s=new Student();
         s.start();
         try{ m.join();}catch(InterruptedException e){System.out.println(e);}
         try{ s.join();}catch(InterruptedException e){System.out.println(e);}
         
         
        
       
        
        
        System.out.println("Your Score:"+s.result());
         
        
               
        }
    
}

