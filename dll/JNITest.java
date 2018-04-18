public class JNITest 
{
   static
	{    		 System.load("/home/ccoew/TE/mynativelib.so"); 	  }

   public native void greet();
   public static void main(String[] args)
 {   		 JNITest test=new JNITest();
     		 test.greet();   }
}
