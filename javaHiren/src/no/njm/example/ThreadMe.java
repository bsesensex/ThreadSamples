package no.njm.example;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import ch.qos.logback.classic.net.SyslogAppender;

public class ThreadMe {
	
	public static void main(String args[]) {		
		   String communique = "The process is launching";
		   Runnable runnable = () -> {
		        try {
		          IntStream.range(1,4).forEach(x->System.out.println(x));
		          
		          String s1="hiren";
		          String s2="hiren";
		          System.out.println(s1.hashCode()==s2.hashCode());
		          String s3="hiren"+" ";
		          System.out.println(s1.hashCode()+ " "+s2.hashCode()+" "+s3.hashCode());
		          System.out.println(s1==s2.trim());
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    };

		    Executor tasExecutor = Executors.newFixedThreadPool(5);

		    try{
		    	tasExecutor.execute(runnable);
		    } catch (Exception ex)
		    {
		    	communique = "The process is failed";
		    }
		
	}


}
