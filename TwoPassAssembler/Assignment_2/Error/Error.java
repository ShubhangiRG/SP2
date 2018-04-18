package error;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Error {

	public static void main(String[] args) {
		int flag=0,flg=0;
		try 
		{
            File file = new File("Error.txt");
            File file1 = new File("Mnemonic");
            
            FileReader fr = new FileReader(file);
            FileReader fr1 = new FileReader(file1);

            BufferedReader br = new BufferedReader(fr);
            BufferedReader br1 = new BufferedReader(fr1);
            
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer1 = new StringBuffer();
            String line,line1;
            String str=new String();
            String str1=new String();
            String str2=new String();
            String str3=new String();
            ArrayList symb=new ArrayList();
            ArrayList sym=new ArrayList();
            ArrayList token=new ArrayList();
            String mnem[]=new String[1025];
			try 
			{
                while ((line = br.readLine()) != null)
                {
                        stringBuffer.append(line);
                        stringBuffer.append("\n");
                }
                while ((line1 = br1.readLine()) != null)
                {
                        stringBuffer1.append(line1);
                        stringBuffer1.append("\n");
                }
                fr.close();
                fr.close();
                
                StringTokenizer et = new StringTokenizer(stringBuffer.toString(),"	\n,");
                StringTokenizer mt = new StringTokenizer(stringBuffer1.toString(),"\n	");
                while(et.hasMoreTokens())
                	token.add(et.nextToken());
                
        		int i=0;
                while(mt.hasMoreTokens())
                	mnem[i++]=mt.nextToken();
                int msize=i;
                

 //----------Mnemonic as a label --------------
                for(i=0;i<token.size();i++)
        		{
        			str=(String) token.get(i);
        			str1=str.substring(0,str.length()-1);
        			for(int j=0;j<msize;j++)
        			{
        				if(str1.equals(mnem[j]))
        				{
        					System.out.println("Mnemonic "+str1+" used as a Label!!");
        				}
        			}
        		}
       
 //-----------Symbol defined twice--------------
                
                for(i=0;i<token.size();i++)
                {
                	str=(String) token.get(i);
                	if(str.endsWith(":"))
                	{
                		for(int j=0;j<symb.size();j++)
                		{
                			if(str.equals(symb.get(j)))
                			{
                				flag=1;
                				break;
                			}
                		}
                		if(flag==0)
                			symb.add(str);
                		else
                		{
                			System.out.println("Symbol "+str.substring(0,str.length()-1) +" defined twice!!");
                			flag=0;
                		}
                	}
                }
                
 //-----------Symbol not defined-------------------
                
                for(i=2;i<token.size();i++)
                {
                	str=(String) token.get(i);
                	if(!str.startsWith("=") && !str.endsWith(":") && !str.equals("AREG") && !str.equals("BREG") && !str.equals("CREG"))
                	{
            			flag=0;
                		for(int j=0;j<msize;j++)
                		{
                			if(str.equals(mnem[j]))
                			{
                				flag=1;
                				break;
                			}
                		}            
                		if(flag==0)
                		{
                    		for(int j=0;j<sym.size();j++)
                    		{
                    			if(str.equals(sym.get(j)))
                    			{
                    				flag=1;
                    				break;
                    			}
                    		}
                    		if(flag==0 && !str.equals("DIV"))
                    			sym.add(str);                        		
                    	}               				
                	}
                }
                
                for(i=0;i<sym.size();i++)
                {
                	flag=0;
                	str=(String) sym.get(i);
                	for(int j=0;j<token.size();j++)
                	{
                		str1=(String) token.get(j);
                		if(str.equals(str1.substring(0,str1.length()-1)))
                		{
                			flag=1;
                			break;
                		}
                	}
                	if(flag==0)
                	{
            			System.out.println("Symbol "+str +" is not defined!!");
                	}
                }

 //-----------Incomplete instruction---------------
                for(i=2;i<token.size();i++)
                {
                	flag=0;
                	str=(String) token.get(i);
            		for(int j=0;j<msize;j++)
            		{
            			if(str.equals(mnem[j]))
            			{
                    		str1=(String) token.get(i+1);
                    		if(str1.endsWith(":"))
                    		{
                    			flag=1;
                    			break;
                    		}
                    		else if(str1.equals("AREG") || str1.equals("BREG") || str1.equals("CREG"))
                    		{
                    			str2=(String) token.get(i+2);
                        		if(str2.endsWith(":"))
                        		{
                        			flag=1;
                        			break;
                        		}
                        		else
                        		{
                            		for(int k=0;k<msize;k++)
                            		{
                            			if(str2.equals(mnem[k]))
                            			{
                            				flag=1;
                            				break;
                            			}
                            		} 	
                        		}
                    		}
                    		else
                    		{
                        		for(int k=0;k<msize;k++)
                        		{
                        			if(str1.equals(mnem[k]))
                        			{
                        				flag=1;
                        				break;
                        			}
                        		} 	
                    		}
            			}
            		}
            		if(flag==1)
            			break;

                }
                	System.out.println("Instruction "+str +" is incomplete!!");
                

                
 //-----------Mnemonic not defined-----------------
                

                for(i=1;i<token.size();i++)
                {
                	str=(String) token.get(i);
                	flg=0;
            		if(str.startsWith("="))
            		{
            			str3=(String) token.get(i+1);
            			flag=1;
            			if(str3.endsWith(":"))
            			{
            				str3=(String) token.get(i+2);
            			}
            		}
            		else if(str.endsWith(":"))
            		{
            			str3=(String) token.get(i+1);	
            		}
            		else if(str.equals("200"))
            		{
            			str3=(String) token.get(i+1);
            		}
            		else
            		{
                    	for(int j=0;j<sym.size();j++)
                    	{
                    		if(str.equals(sym.get(j)))
                    		{
                    			str3=(String) token.get(i+1);
                    			if(str3.endsWith(":"))
                    			{
                    				str3=(String) token.get(i+2);
                    				break;
                    			}
                    		}
                    	}          			
            		}
            		
            		for(int j=0;j<msize;j++)
            		{
            			if(str3.equals(mnem[j]))
            			{
            				flg=1;
            				break;
            			}
            		}
            		if(flg==0)
            		{
            			break;
            		}
                }
                System.out.println("Instruction "+str3 +" is not defined!!");
         
                
                
 //-----------Missing end of file------------------
                
                for(i=0;i<token.size();i++)
                {
                	str=(String) token.get(i);
                	if(str.equals("END"))
                	{
                		flg=1;
                	}
                }
                if(flg==0)
                {
                	System.out.println("END of file is Missing!!");
                }
                
 //-------------------------------------------------
        		
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
				
	}

}