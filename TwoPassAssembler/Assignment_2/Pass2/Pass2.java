package pass2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Pass2 {

	public static void main(String[] args) {
		   try 
			{
               File file = new File("IntermediateCode");
               File file1 = new File("LitTab");
               File file2 = new File("SymTab");
               File file3 = new File("MachineCode");
               
               FileReader fr = new FileReader(file);
               FileReader fr1 = new FileReader(file1);
               FileReader fr2 = new FileReader(file2);
               
               FileWriter fw = new FileWriter(file3);
               
               BufferedReader br = new BufferedReader(fr);
               BufferedReader br1 = new BufferedReader(fr1);
               BufferedReader br2 = new BufferedReader(fr2);
               
               BufferedWriter bw = new BufferedWriter(fw);
               
               StringBuffer stringBuffer = new StringBuffer();
               StringBuffer stringBuffer1 = new StringBuffer();
               StringBuffer stringBuffer2 = new StringBuffer();

               String line,str,str1;
               
               String token[]=new String[1024];
               String literal[]=new String[1024];
               String symbol[]=new String[1024];
               
               int code,lit,sym;

               while ((line = br.readLine()) != null)
               {
                       stringBuffer.append(line);
                       stringBuffer.append("\n");
               }
               StringTokenizer it = new StringTokenizer(stringBuffer.toString(),"	 ,()\n");
               code=it.countTokens();
               for(int i=0;i<code;i++)
               {
            	   token[i]=it.nextToken();
               }
               
               
               while ((line = br1.readLine()) != null)
               {
                       stringBuffer1.append(line);
                       stringBuffer1.append("\n");
               }
               StringTokenizer lt = new StringTokenizer(stringBuffer1.toString(),"	\n");
               lit=lt.countTokens();
               for(int i=0;i<lit;i++)
               {
            	   literal[i]=lt.nextToken();
               }
               
               while ((line = br2.readLine()) != null)
               {
                       stringBuffer2.append(line);
                       stringBuffer2.append("\n");
               }
               StringTokenizer st = new StringTokenizer(stringBuffer2.toString(),"	\n");
               sym=st.countTokens();
               for(int i=0;i<sym;i++)
               {
            	   symbol[i]=st.nextToken();
               }

               int i=0,flag=0;
               while(i!=code)
               {   
				   str=token[i++];
				   if(str.equals("AD"))
				   {
					   str=token[i++];
					   if(str.equals("05") || str.equals("02"))
					   {
						   bw.write("\n");
						   flag=1;
					   }
					   else
					   {
						   i++;
						   bw.write("\n");
					   }
				   }
				   else if(flag==1)
				   {		   
					   if(token[i].equals("IS"))
					   {
						   i--;
						   flag=0;
					   }
					   else
					   {
						   bw.write(str);
						   str=token[i++];	
						   bw.write("	"+str+"\n");
					   }

				   }
				   else
				   {
					   bw.write(str);
					   str=token[i++];
					   if(str.equals("IS"))
					   {
						   str=token[i++];
						   bw.write("	"+str);
						   if(str.equals("00"))
						   {
							   bw.write("\n");
						   }
						   else
						   {
							   str=token[i++];
							   if(str.equals("AREG"))
							   {
							   		bw.write("	1");	   
							   }
							   else if(str.equals("BREG"))
							   {
							   		bw.write("	2");	   
							   }
							   else if(str.equals("CREG"))
							   {
							   		bw.write("	3");	   
							   }
						
							   
							   str=token[i++];
							   if(str.equals("L"))
							   {
								   str=token[i++];
								  for(int j=0;j<lit;j++)
								  {
										 str1=literal[j];
										 if(str.equals(str1))
										 {
										   	 bw.write("	"+literal[j+2]+"\n");	   
										 }
								  }
							   }
							   else if(str.startsWith("="))
							   {
								   	 bw.write("	"+str+"\n");	   
							   }
							   else
							   {
								   for(int j=0;j<sym;j++)
								   {
										 str1=symbol[j];
										 if(str.equals(str1))
										 {
										   	 bw.write("	"+symbol[j+1]+"\n");	   
										 }	   
								   }
							   }							   
						   }

					   }
					   else if(str.equals("DL"))
					   {
						   i=i+2;
						   	 bw.write("\n");	   
					   }

				   }

					   		   
               }
               bw.close();
               fr.close();
               fr1.close();
               fr2.close();
		} 
		catch (IOException e) 
		{
	                        e.printStackTrace();
	      }
	}

}
